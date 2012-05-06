package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AbstractReversibleOperation;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class TransitionManager
{
	// < transition ID, < place ID, outgoing arc inscription matcher > >
	private Map<String, Map<String, ArcInscriptionHandler>> patternMatcherMap = null;
	
	private EvaluationManager evaluationManager = null;
	private ReversibleOperationManager reversibleOperationManager = null;
		
	public TransitionManager(FlatAccess flatAccess, ComparisonManager comparatorManager,
			EvaluationManager evaluationManager, ReversibleOperationManager reversibleOperationManager)
	{
		this.evaluationManager = evaluationManager;
		this.reversibleOperationManager = reversibleOperationManager;
		
		this.patternMatcherMap = new HashMap<String, Map<String,ArcInscriptionHandler>>();
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : flatAccess.getTransitions())
		{
			Map<String, ArcInscriptionHandler> map = new HashMap<String, ArcInscriptionHandler>();
			for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : transition.getIn())
			{
				Arc hlArc = (Arc) arc;
				if(hlArc.getHlinscription() != null && 
						hlArc.getHlinscription().getStructure() != null)
				{
					Operator term = (Operator)hlArc.getHlinscription().getStructure();
					map.put(arc.getSource().getId(), new ArcInscriptionHandler(term, comparatorManager));
				}
			}
			this.patternMatcherMap.put(transition.getId(), map);
		}
	}
	
	public List<FiringMode> checkTransition(Transition transition, Map<String, 
			PlaceMarking> runtimeValues) throws DependencyException, UnknownVariableException
	{		
		List<FiringMode> assignments = assignments(transition, runtimeValues, 
				patternMatcherMap.get(transition.getId()), evaluationManager,
				reversibleOperationManager);
		
		return assignments;
	}
	
	private static List<FiringMode> assignments(Transition transition, Map<String, 
			PlaceMarking> runtimeValues, Map<String, ArcInscriptionHandler> incomingArcs,
			EvaluationManager evaluationManager, ReversibleOperationManager reversibleOperationManager) 
					throws DependencyException, UnknownVariableException
	{
		// each inscription variable matches
		List<List<List<Map<String, TermAssignment>>>> allInscriptionMatches =
				new ArrayList<List<List<Map<String,TermAssignment>>>>();
		for(String placeId : incomingArcs.keySet())
		{
			MSValue msValue = runtimeValues.get(placeId).getMsValue();
			
			// each inscription term compared to all multiset terms
			ArcInscriptionHandler matcher = incomingArcs.get(placeId);
			allInscriptionMatches.add(matcher.match(msValue));
		}
		
		// narrowing
		Map<String, TermAssignment> globalMap = narrowing(allInscriptionMatches);
		
		// resolving undefined variables
		List<TermAssignment> unresolved = new ArrayList<TermAssignment>();
		for(String key : globalMap.keySet())
		{
			TermAssignment ve = globalMap.get(key);
			if(ve.getVariable() instanceof AbstractUndefinedVariable)
			{
				unresolved.add(ve);
			}
		}
		for(TermAssignment ve : unresolved)
		{
			globalMap.remove(ve.getVariable().getName());
		}
		do
		{		
			List<TermAssignment> repeat = new ArrayList<TermAssignment>();
			for(TermAssignment ve : unresolved)
			{
				AbstractReversibleOperation op = ((AbstractReversibleOperation)ve.getVariable());
				
				if(!reversibleOperationManager.resolveAll(ve.getValues(), op, globalMap))
				{
					repeat.add(ve);
				}
			}
			unresolved = repeat;
		}
		while(unresolved.size() > 0);
		
		// filtering non consistent assignments
		globalMap = checkParams(globalMap);
		
		// there is only 1 variable
		if(globalMap.keySet().size() == 1)
		{
			List<Map<Variable, AbstractValue>> varSets = new ArrayList<Map<Variable,AbstractValue>>();
			for(String key : globalMap.keySet())
			{
				TermAssignment value = globalMap.get(key);
				
				for(AbstractValue av : value.getValues())
				{
					Map<Variable, AbstractValue> map = new HashMap<Variable, AbstractValue>();
					map.put((Variable)value.getVariable().getRootTerm(), av);
					varSets.add(map);
				}
			}
			return eval(varSets, incomingArcs, runtimeValues, transition, evaluationManager);
		}
		
		// computing Cartesian product of variable assignments
		List<List<Pair<TermAssignment, AbstractValue>>> pairList = pairVariablesToAssignments(globalMap);
		CartesianProduct<Pair<TermAssignment, AbstractValue>> cartesianProd = 
				new CartesianProduct<Pair<TermAssignment, AbstractValue>>();
		List<List<Pair<TermAssignment, AbstractValue>>> product =
				cartesianProd.product(pairList);
		
		List<Map<Variable, AbstractValue>> varSets = new ArrayList<Map<Variable, AbstractValue>>();
		for(List<Pair<TermAssignment, AbstractValue>> list : product)
		{
			varSets.add(pairToMap(list));
		}
		
		// evaluate each arc inscription with the given parameter set
		return eval(varSets, incomingArcs, runtimeValues, transition, evaluationManager);
	}
	
	private static Map<String, TermAssignment> checkParams(Map<String, TermAssignment> globalMap)
	{
		Map<String, TermAssignment> filtered = new HashMap<String, TermAssignment>();
		for(String key : globalMap.keySet())
		{
			TermAssignment oldVe = globalMap.get(key);
			RuntimeVariable rv = (RuntimeVariable)oldVe.getVariable();
			
			Set<AbstractValue> newValues = new HashSet<AbstractValue>();
			for(AbstractValue value : oldVe.getValues())
			{
				if(ConsistencyManager.check(value, rv.getVariable().getSort()))
				{
					newValues.add(value);
				}
			}
			
			if(newValues.size() > 0)
			{
				TermAssignment newVe = new TermAssignment();
				newVe.setVariable(rv);
				newVe.setValues(newValues);
				
				filtered.put(key, newVe);
			}
		}
		return filtered;
	}
	
	/*
	 * Evaluate each arc inscription with the given parameter set
	 */
	private static List<FiringMode> eval(List<Map<Variable, AbstractValue>> varSets,
			Map<String, ArcInscriptionHandler> incomingArcs,
			Map<String, PlaceMarking> runtimeValues, Transition transition,
			EvaluationManager evaluationManager) throws UnknownVariableException
	{
		List<FiringMode> assignemnts = new ArrayList<FiringMode>();
		for(Map<Variable, AbstractValue> params : varSets)
		{
			boolean conditionSatisfied = true;
			
			if(transition.getCondition() != null && 
					transition.getCondition().getStructure() != null)
			{
				try
                {
	                AbstractValue conditionValue = 
	                		evaluationManager.evaluateAdapt(
	                				transition.getCondition().getStructure(), params);
	                conditionSatisfied = ((BooleanValue)conditionValue).getValue();
                }
                catch(Exception e)
                {
                	conditionSatisfied = false;
                }
			}
			
			if(conditionSatisfied)
			{
				FiringMode assignment = new FiringMode();
				assignment.setParams(params);
				assignment.setTransition(transition);
				
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
		                    inscriptionValue = (MSValue)evaluationManager
		                    		.evaluateAdapt(incomingArcs.get(placeId).getOperator(), params);
		                    
		                    if(ConsistencyManager.check(inscriptionValue, null) && 
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
	
	private static Map<String, TermAssignment> narrowing(
			List<List<List<Map<String, TermAssignment>>>> mainList)
	{
		Map<String, TermAssignment> globalMap = new HashMap<String, TermAssignment>();
		
		// for each arc
		for(List<List<Map<String, TermAssignment>>> list : mainList)
		{
			Map<String, TermAssignment> map = new HashMap<String, TermAssignment>();
			for(List<Map<String, TermAssignment>> assignments : list)
			{
				for(Map<String, TermAssignment> assignment : assignments)
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
				TermAssignment ve = new TermAssignment();
				ve.setVariable(map.get(name).getVariable());
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
	
	private static Map<Variable, AbstractValue> pairToMap(List<Pair<TermAssignment, AbstractValue>> list)
	{
		Map<Variable, AbstractValue> map = new HashMap<Variable, AbstractValue>();
		
		for(Pair<TermAssignment, AbstractValue> p : list)
		{
			map.put((Variable)p.getKey().getVariable().getRootTerm(), p.getValue());
		}
		return map;
	}
	
	private static List<List<Pair<TermAssignment, AbstractValue>>> pairVariablesToAssignments(Map<String, TermAssignment> globalMap)
	{
		List<List<Pair<TermAssignment, AbstractValue>>> evaluations = 
				new ArrayList<List<Pair<TermAssignment, AbstractValue>>>();
		
		for(String name : globalMap.keySet())
		{
			List<Pair<TermAssignment, AbstractValue>> evaluation = new ArrayList<Pair<TermAssignment, AbstractValue>>();
			
			TermAssignment ve = globalMap.get(name);
			List<AbstractValue> values = new ArrayList<AbstractValue>(ve.getValues());
			for(AbstractValue entry : values)
			{
				evaluation.add(new Pair<TermAssignment, AbstractValue>(ve, entry));
			}
			evaluations.add(evaluation);
		}
		return evaluations;
	}
}
