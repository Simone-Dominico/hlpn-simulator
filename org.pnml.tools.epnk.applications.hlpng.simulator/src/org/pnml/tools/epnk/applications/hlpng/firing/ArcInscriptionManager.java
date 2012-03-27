package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.firing.resolvers.ResolutionManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.applications.hlpng.runtime.operators.AbstractReversibleOperation;
import org.pnml.tools.epnk.applications.hlpng.runtime.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.operators.UnknownVariableException;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.MultiSetOperator;

public class ArcInscriptionManager
{
	// < transition ID, < place ID, outgoing arc inscription matcher > >
	private Map<String, Map<String, ArcInscriptionHandler>> patternMatcherMap = null;
	
	public ArcInscriptionManager(FlatAccess flatAccess)
	{
		this.patternMatcherMap = new HashMap<String, Map<String,ArcInscriptionHandler>>();
		
		ResolutionManager comparatorManager = ResolutionManager.getInstance();
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : flatAccess.getTransitions())
		{
			Map<String, ArcInscriptionHandler> map = new HashMap<String, ArcInscriptionHandler>();
			for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : transition.getIn())
			{
				Arc hlArc = (Arc) arc;
				if(hlArc.getHlinscription() != null && 
						hlArc.getHlinscription().getStructure() != null &&
						hlArc.getHlinscription().getStructure() instanceof MultiSetOperator)
				{
					MultiSetOperator term = (MultiSetOperator)hlArc.getHlinscription().getStructure();
					map.put(arc.getSource().getId(), new ArcInscriptionHandler(term, comparatorManager));
				}
			}
			this.patternMatcherMap.put(transition.getId(), map);
		}
	}
	
	
	public List<FiringMode> assignments(String transitionId, Map<String, PlaceMarking> runtimeValues) throws DependencyException, UnknownVariableException
	{
		Map<String, ArcInscriptionHandler> incomingArcs = this.patternMatcherMap.get(transitionId);
		
		// each inscription variable matches
		List<List<List<Map<String, VariableEvaluation>>>> allInscriptionMatches =
				new ArrayList<List<List<Map<String,VariableEvaluation>>>>();
		for(String placeId : incomingArcs.keySet())
		{
			ArcInscriptionHandler matcher = incomingArcs.get(placeId);
			MSValue msValue = runtimeValues.get(placeId).getMsValue();
			
			// each inscription term compared to all multiset terms
			allInscriptionMatches.add(matcher.match(msValue));
		}
		
		// narrowing
		Map<String, VariableEvaluation> globalMap = narrowing(allInscriptionMatches);
		
		// there is only 1 variable
		if(globalMap.keySet().size() == 1)
		{
			List<Map<String, AbstractValue>> varSets = new ArrayList<Map<String,AbstractValue>>();
			for(String key : globalMap.keySet())
			{
				VariableEvaluation value = globalMap.get(key);
				
				for(AbstractValue av : value.getValues())
				{
					Map<String, AbstractValue> map = new HashMap<String, AbstractValue>();
					map.put(key, av);
					varSets.add(map);
				}
			}
			return eval(varSets, incomingArcs, runtimeValues);
		}
		
		// resolving undefined variables
		List<VariableEvaluation> unfinished = new ArrayList<VariableEvaluation>();
		for(String key : globalMap.keySet())
		{
			VariableEvaluation ve = globalMap.get(key);
			if(ve.getVariable() instanceof AbstractUndefinedVariable)
			{
				unfinished.add(ve);
			}
		}
		for(VariableEvaluation ve : unfinished)
		{
			globalMap.remove(ve.getVariable().getName());
		}
		
		do
		{		
			List<VariableEvaluation> tmp = new ArrayList<VariableEvaluation>();
			for(VariableEvaluation ve : unfinished)
			{
				AbstractReversibleOperation op = ((AbstractReversibleOperation)ve.getVariable());
				
				boolean stop = false;
				for(AbstractValue result : ve.getValues())
				{
					if(!stop && !EvaluationManager.getInstance().resolve(result, op, globalMap))
					{
						stop = true;
						tmp.add(ve);
					}
				}
			}

			unfinished = tmp;
		}
		while(unfinished.size() > 0);
		
		// computing Cartesian product of variable assignments
		List<List<Pair<VariableEvaluation, AbstractValue>>> pairList = pairVariablesToAssignments(globalMap);
		CartesianProduct<Pair<VariableEvaluation, AbstractValue>> cartesianProd = 
				new CartesianProduct<Pair<VariableEvaluation, AbstractValue>>();
		List<List<Pair<VariableEvaluation, AbstractValue>>> product =
				cartesianProd.product(pairList);
		
		List<Map<String, AbstractValue>> varSets = new ArrayList<Map<String,AbstractValue>>();
		for(List<Pair<VariableEvaluation, AbstractValue>> list : product)
		{
			varSets.add(pairToMap(list));
		}
		return eval(varSets, incomingArcs, runtimeValues);
	}
	
	/*
	 * Evaluate each arc inscription with the given parameter set
	 */
	private static List<FiringMode> eval(List<Map<String, AbstractValue>> varSets,
			Map<String, ArcInscriptionHandler> incomingArcs,
			Map<String, PlaceMarking> runtimeValues) throws UnknownVariableException
	{
		List<FiringMode> assignemnts = new ArrayList<FiringMode>();
		for(Map<String, AbstractValue> params : varSets)
		{
			if(checkParams(params))
			{
				FiringMode assignment = new FiringMode();
				assignment.setParams(params);
				
				boolean matched = true;
				for(String placeId : incomingArcs.keySet())
				{
					if(matched)
					{
						MSValue runtimeValue = runtimeValues.get(placeId).getMsValue();
						
						// it may be not possible to initialize some of the variables
						MSValue inscriptionValue = null;
	                    try
	                    {
		                    inscriptionValue = (MSValue)EvaluationManager.getInstance()
		                    		.evaluate(incomingArcs.get(placeId).getMultiSetOperator(), params);
		                    
		                    if(ConsistencyManager.check(inscriptionValue) && 
		                    		AbstractValueMath.lessEqual(inscriptionValue, runtimeValue))
		                    {
		                    	assignment.getValues().put(placeId, 
										AbstractValueMath.subtract(runtimeValue, inscriptionValue));	
		                    }
		                    else
		                    {
		                    	matched = false;
		                    }
	                    }
	                    catch(Exception e)
	                    {
	                    	matched = false;
	                    }
					}
				}
				if(matched)
				{
					assignemnts.add(assignment);
				}	
			}
		}
		return assignemnts;
	}
	
	private static boolean checkParams(Map<String, AbstractValue> params)
	{
		for(String key : params.keySet())
		{
			if(!ConsistencyManager.check(params.get(key)))
			{
				return false;
			}
		}
		
		return true;
	}
	
	private static Map<String, VariableEvaluation> narrowing(
			List<List<List<Map<String, VariableEvaluation>>>> mainList)
	{
		Map<String, VariableEvaluation> globalMap = new HashMap<String, VariableEvaluation>();
		
		// for each arc
		for(List<List<Map<String, VariableEvaluation>>> list : mainList)
		{
			Map<String, VariableEvaluation> map = new HashMap<String, VariableEvaluation>();
			for(List<Map<String, VariableEvaluation>> assignments : list)
			{
				for(Map<String, VariableEvaluation> assignment : assignments)
				{
					for(String name : assignment.keySet())
					{
						if(map.containsKey(name))
						{
							map.get(name).getValues().addAll(assignment.get(name).getValues());
						}
						else
						{
							map.put(name, assignment.get(name));
						}
					}	
				}
			}
			
			for(String name : map.keySet())
			{
				VariableEvaluation ve = new VariableEvaluation();
				ve.setVariable(map.get(name).getVariable());
				ve.setVariableName(map.get(name).getVariableName());
				// intersection
				if(globalMap.containsKey(name))
				{
					Set<AbstractValue> intersection = 
							new HashSet<AbstractValue>(map.get(name).getValues());
					intersection.retainAll(globalMap.get(name).getValues());
					ve.setValues(intersection);
				}
				else
				{
					ve.getValues().addAll(map.get(name).getValues());
				}
				globalMap.put(name, ve);
			}
		}
		return globalMap;
	}
	
	private static Map<String, AbstractValue> pairToMap(List<Pair<VariableEvaluation, AbstractValue>> list)
	{
		Map<String, AbstractValue> map = new HashMap<String, AbstractValue>();
		
		for(Pair<VariableEvaluation, AbstractValue> p : list)
		{
			map.put(p.getKey().getVariableName(), p.getValue());
		}
		return map;
	}
	
	private static List<List<Pair<VariableEvaluation, AbstractValue>>> pairVariablesToAssignments(Map<String, VariableEvaluation> globalMap)
	{
		List<List<Pair<VariableEvaluation, AbstractValue>>> evaluations = 
				new ArrayList<List<Pair<VariableEvaluation, AbstractValue>>>();
		
		for(String name : globalMap.keySet())
		{
			List<Pair<VariableEvaluation, AbstractValue>> evaluation = new ArrayList<Pair<VariableEvaluation, AbstractValue>>();
			
			VariableEvaluation ve = globalMap.get(name);
			List<AbstractValue> values = new ArrayList<AbstractValue>(ve.getValues());
			for(AbstractValue entry : values)
			{
				evaluation.add(new Pair<VariableEvaluation, AbstractValue>(ve, entry));
			}
			evaluations.add(evaluation);
		}
		return evaluations;
	}
}