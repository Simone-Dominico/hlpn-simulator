package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

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
		Map<String, ArcInscriptionHandler> incomingArcs = patternMatcherMap.get(transition.getId());
		// each inscription variable matches
		List<List<Map<TermWrapper, TermAssignment>>> allInscriptionMatches =
				new ArrayList<List<Map<TermWrapper,TermAssignment>>>();
		for(String placeId : incomingArcs.keySet())
		{
			MSValue msValue = runtimeValues.get(placeId).getMsValue();
			
			// each inscription term compared to all multiset terms
			ArcInscriptionHandler matcher = incomingArcs.get(placeId);
			allInscriptionMatches.add(matcher.match(msValue));
		}
		
		// narrowing
		Map<TermWrapper, TermAssignment> globalMap = narrowing(allInscriptionMatches);
		
		// resolving undefined variables
		VariableResolver resolver = new VariableResolver(globalMap, reversibleOperationManager);
		globalMap = resolver.solve();
		
		// filtering non consistent assignments
		globalMap = ConsistencyManager.checkParams(globalMap);
		
		// there is only 1 variable
		if(globalMap.keySet().size() == 1)
		{
			List<Map<TermWrapper, AbstractValue>> varSets = new ArrayList<Map<TermWrapper,AbstractValue>>();
			for(TermWrapper key : globalMap.keySet())
			{
				TermAssignment value = globalMap.get(key);
				
				for(AbstractValue av : value.getValues())
				{
					Map<TermWrapper, AbstractValue> map = new HashMap<TermWrapper, AbstractValue>();
					map.put(key, av);
					varSets.add(map);
				}
			}
			return ConsistencyManager.checkSolution(varSets, incomingArcs, runtimeValues, transition, evaluationManager);
		}
		
		// computing Cartesian product of variable assignments
		List<List<Pair<TermAssignment, AbstractValue>>> pairList = pairVariablesToAssignments(globalMap);
		CartesianProduct<Pair<TermAssignment, AbstractValue>> cartesianProd = 
				new CartesianProduct<Pair<TermAssignment, AbstractValue>>();
		List<List<Pair<TermAssignment, AbstractValue>>> product =
				cartesianProd.product(pairList);
		
		List<Map<TermWrapper, AbstractValue>> varSets = new ArrayList<Map<TermWrapper, AbstractValue>>();
		for(List<Pair<TermAssignment, AbstractValue>> list : product)
		{
			varSets.add(pairToMap(list));
		}
		
		// evaluate each arc inscription with the given parameter set
		return ConsistencyManager.checkSolution(varSets, incomingArcs, runtimeValues, transition, evaluationManager);
	}
	
	private static Map<TermWrapper, TermAssignment> narrowing(
			List<List<Map<TermWrapper, TermAssignment>>> mainList)
	{
		Map<TermWrapper, TermAssignment> globalMap = new HashMap<TermWrapper, TermAssignment>();
		
		// for each arc
		for(List<Map<TermWrapper, TermAssignment>> list : mainList)
		{
			Map<TermWrapper, TermAssignment> map = new HashMap<TermWrapper, TermAssignment>();
			for(Map<TermWrapper, TermAssignment> assignment : list)
			{
				for(TermWrapper wrapper : assignment.keySet())
				{
					if(map.containsKey(wrapper))
					{
						map.get(wrapper).getValues().addAll(assignment.get(wrapper).getValues());
					}
					else
					{
						map.put(wrapper, assignment.get(wrapper));
					}
				}	
			}
			
			for(TermWrapper wrapper : map.keySet())
			{
				TermAssignment ve = new TermAssignment();
				ve.setTermWrapper(map.get(wrapper).getTermWrapper());
				// intersection
				if(globalMap.containsKey(wrapper))
				{
					Set<AbstractValue> intersection = 
							new HashSet<AbstractValue>(map.get(wrapper).getValues());
					intersection.retainAll(globalMap.get(wrapper).getValues());
					ve.setValues(intersection);
				}
				else
				{
					ve.getValues().addAll(map.get(wrapper).getValues());
				}
				globalMap.put(wrapper, ve);
			}
		}
		return globalMap;
	}
	
	private static Map<TermWrapper, AbstractValue> pairToMap(List<Pair<TermAssignment, AbstractValue>> list)
	{
		Map<TermWrapper, AbstractValue> map = new HashMap<TermWrapper, AbstractValue>();
		
		for(Pair<TermAssignment, AbstractValue> p : list)
		{
			map.put(p.getKey().getTermWrapper(), p.getValue());
		}
		return map;
	}
	
	private static List<List<Pair<TermAssignment, AbstractValue>>> pairVariablesToAssignments(Map<TermWrapper, TermAssignment> globalMap)
	{
		List<List<Pair<TermAssignment, AbstractValue>>> evaluations = 
				new ArrayList<List<Pair<TermAssignment, AbstractValue>>>();
		
		for(TermWrapper wrapper : globalMap.keySet())
		{
			List<Pair<TermAssignment, AbstractValue>> evaluation = new ArrayList<Pair<TermAssignment, AbstractValue>>();
			
			TermAssignment ve = globalMap.get(wrapper);
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
