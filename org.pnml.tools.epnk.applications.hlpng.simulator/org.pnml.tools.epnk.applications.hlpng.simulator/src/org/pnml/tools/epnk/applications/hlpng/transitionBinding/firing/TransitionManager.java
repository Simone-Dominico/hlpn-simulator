package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.Place;
import org.pnml.tools.epnk.pnmlcoremodel.PlaceNode;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class TransitionManager
{
	// < transition ID, < place ID, outgoing arc inscription matcher > >
	private Map<IDWrapper, Map<IDWrapper, ArcInscriptionHandler>> patternMatcherMap = null;
	
	private EvaluationManager evaluationManager = null;
	private ReversibleOperationManager reversibleOperationManager = null;
		
	public TransitionManager(FlatAccess flatAccess, ComparisonManager comparatorManager,
			EvaluationManager evaluationManager, ReversibleOperationManager reversibleOperationManager)
	{
		this.evaluationManager = evaluationManager;
		this.reversibleOperationManager = reversibleOperationManager;
		
		this.patternMatcherMap = new HashMap<IDWrapper, Map<IDWrapper,ArcInscriptionHandler>>();
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : flatAccess.getTransitions())
		{
			Map<IDWrapper, ArcInscriptionHandler> map = new HashMap<IDWrapper, ArcInscriptionHandler>();
			for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : transition.getIn())
			{
				Arc hlArc = (Arc) arc;
				if(hlArc.getHlinscription() != null && 
						hlArc.getHlinscription().getStructure() != null)
				{
					Operator term = (Operator)hlArc.getHlinscription().getStructure();
					
					Place place = flatAccess.resolve((PlaceNode)arc.getSource());
					
					if(place != null)
					{
						map.put(new IDWrapper(place), new ArcInscriptionHandler(term, comparatorManager));	
					}
				}
			}
			this.patternMatcherMap.put(new IDWrapper(transition), map);
		}
	}
	
	public List<FiringMode> checkTransition(Transition transition, Map<IDWrapper, 
			IMSValue> runtimeValues) throws DependencyException, UnknownVariableException
	{		
		Map<IDWrapper, ArcInscriptionHandler> incomingArcs = patternMatcherMap.get(new IDWrapper(transition));
		// each inscription variable/term assignments
		Map<TermWrapper, TermAssignment> globalMap = new HashMap<TermWrapper, TermAssignment>();

		for(IDWrapper placeId : incomingArcs.keySet())
		{
			IMSValue msValue = runtimeValues.get(placeId);
			
			// each inscription term compared to all multiset terms
			ArcInscriptionHandler matcher = incomingArcs.get(placeId);
			Map<TermWrapper, TermAssignment> assignments = matcher.match(msValue);
			intersection(globalMap, assignments);
		}

		// resolving undefined variables
		VariableResolver resolver = new VariableResolver(globalMap, reversibleOperationManager);
		globalMap = resolver.solve();
		
		// filtering non consistent assignments
		globalMap = ConsistencyManager.checkParams(globalMap);
		
		// there is only 1 variable
		if(globalMap.keySet().size() == 1)
		{
			List<Map<TermWrapper, IValue>> varSets = new ArrayList<Map<TermWrapper,IValue>>();
			for(TermWrapper key : globalMap.keySet())
			{
				TermAssignment value = globalMap.get(key);
				
				for(IValue av : value.getValues())
				{
					Map<TermWrapper, IValue> map = new HashMap<TermWrapper, IValue>();
					map.put(key, av);
					varSets.add(map);
				}
			}
			return ConsistencyManager.checkSolution(varSets, incomingArcs, runtimeValues, transition, evaluationManager);
		}
		
		// computing Cartesian product of variable assignments
		List<List<Pair<TermAssignment, IValue>>> pairList = pairVariablesToAssignments(globalMap);
		CartesianProduct<Pair<TermAssignment, IValue>> cartesianProd = 
				new CartesianProduct<Pair<TermAssignment, IValue>>();
		List<List<Pair<TermAssignment, IValue>>> product =
				cartesianProd.product(pairList);
		
		List<Map<TermWrapper, IValue>> varSets = new ArrayList<Map<TermWrapper, IValue>>();
		for(List<Pair<TermAssignment, IValue>> list : product)
		{
			varSets.add(pairToMap(list));
		}
		
		// evaluate each arc inscription with the given parameter set
		return ConsistencyManager.checkSolution(varSets, incomingArcs, runtimeValues, transition, evaluationManager);
	}
	
	private static void intersection(Map<TermWrapper, TermAssignment> globalMap,
			Map<TermWrapper, TermAssignment> assignment)
	{
		for(TermWrapper wrapper : assignment.keySet())
		{
			TermAssignment ve = new TermAssignment();
			ve.setTermWrapper(assignment.get(wrapper).getTermWrapper());
			
			if(globalMap.containsKey(wrapper))
			{
				// intersection
				Set<IValue> intersection = 
						new HashSet<IValue>(assignment.get(wrapper).getValues());
				intersection.retainAll(globalMap.get(wrapper).getValues());
				ve.setValues(intersection);
			}
			else
			{
				ve.getValues().addAll(assignment.get(wrapper).getValues());
			}
			globalMap.put(wrapper, ve);
		}
	}
	
	private static Map<TermWrapper, IValue> pairToMap(List<Pair<TermAssignment, IValue>> list)
	{
		Map<TermWrapper, IValue> map = new HashMap<TermWrapper, IValue>();
		
		for(Pair<TermAssignment, IValue> p : list)
		{
			map.put(p.getKey().getTermWrapper(), p.getValue());
		}
		return map;
	}
	
	private static List<List<Pair<TermAssignment, IValue>>> pairVariablesToAssignments(Map<TermWrapper, TermAssignment> globalMap)
	{
		List<List<Pair<TermAssignment, IValue>>> evaluations = 
				new ArrayList<List<Pair<TermAssignment, IValue>>>();
		
		for(TermWrapper wrapper : globalMap.keySet())
		{
			List<Pair<TermAssignment, IValue>> evaluation = new ArrayList<Pair<TermAssignment, IValue>>();
			
			TermAssignment ve = globalMap.get(wrapper);
			List<IValue> values = new ArrayList<IValue>(ve.getValues());
			for(IValue entry : values)
			{
				evaluation.add(new Pair<TermAssignment, IValue>(ve, entry));
			}
			evaluations.add(evaluation);
		}
		return evaluations;
	}
}
