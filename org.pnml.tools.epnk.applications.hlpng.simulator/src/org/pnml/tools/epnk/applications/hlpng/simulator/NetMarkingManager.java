package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NetMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.TransitionMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.DependencyException;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TransitionManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;

public class NetMarkingManager
{
	private PetriNet petrinet = null;
	private FlatAccess flatAccess = null;
	private EvaluationManager evalManager = null;
	private TransitionManager transitionManager = null;
	
	public NetMarkingManager(PetriNet petrinet, FlatAccess flatAccess, 
			EvaluationManager evalManager, ComparisonManager comparatorManager,
			ReversibleOperationManager reversibleOperationManager)
	{
		this.petrinet = petrinet;
		this.flatAccess = flatAccess;
		this.evalManager = evalManager;
		this.transitionManager = new TransitionManager(flatAccess, comparatorManager,
				evalManager, reversibleOperationManager);
	}
	
	public NetMarking createNetMarking()
	{
		NetMarking netMarking = new NetMarking();
		netMarking.setNet(petrinet);
		
		// creates a runtime marking for each place
		for(org.pnml.tools.epnk.pnmlcoremodel.Place place : flatAccess.getPlaces())
		{
			Place hlPlace = (Place)place;
			
			MSValue msValue = null;
			
			if(hlPlace.getHlinitialMarking() != null &&
					hlPlace.getHlinitialMarking().getStructure() != null)
			{
				Term term = hlPlace.getHlinitialMarking().getStructure();
				
                try
                {
                	msValue = (MSValue)evalManager.evaluate(term, null);
                }
                catch(UnknownVariableException e)
                {
	                e.printStackTrace();
                }
			}
			
			if(msValue == null)
			{
				msValue = new MSValue();
                msValue.setSort(TermsFactory.eINSTANCE.createMultiSetSort());
			}
			
			PlaceMarking marking  = new PlaceMarking();
			marking.setPlace(hlPlace);
			marking.setMsValue(msValue);
			marking.setObject(hlPlace);
			
			netMarking.getMarkings().add(marking);
			netMarking.getObjectAnnotations().add(marking);
		}
		
		// puts place markings into a map for a better performance
		Map<String, PlaceMarking> runtimeValues = new HashMap<String, PlaceMarking>();
	    for(AbstractMarking marking : netMarking.getMarkings())
    	{
	    	if(marking instanceof PlaceMarking)
	    	{
	    		PlaceMarking pMarking = (PlaceMarking) marking;
	    		runtimeValues.put(pMarking.getPlace().getId(), pMarking);
	    	}
    	}
	    
	    // creates a marking for enabled transitions
	    List<Pair<Transition, List<FiringMode>>> enabledTransitions =
				checkTransitions(flatAccess.getTransitions(), runtimeValues, this.transitionManager);
		for(TransitionMarking marking : getTransitionMarkings(enabledTransitions))
		{
			netMarking.getMarkings().add(marking);
			netMarking.getObjectAnnotations().add(marking);
		}

		return netMarking;
	}
	
	public NetMarking createNetMarking(NetMarking prevMarking, FiringMode firingMode)
	{
		// puts place markings into a map
		Map<String, PlaceMarking> oldRuntimeValues = new HashMap<String, PlaceMarking>();
		for(AbstractMarking marking : prevMarking.getMarkings())
    	{
	    	if(marking instanceof PlaceMarking)
	    	{
	    		PlaceMarking pMarking = (PlaceMarking) marking;
	    		oldRuntimeValues.put(pMarking.getPlace().getId(), pMarking);
	    	}
    	}

		// updates incoming places' marking
		Map<String, PlaceMarking> newRuntimeValues = new HashMap<String, PlaceMarking>();
		for(String placeId : firingMode.getValues().keySet())
		{
			PlaceMarking oldMarking = oldRuntimeValues.get(placeId);
			oldRuntimeValues.remove(placeId);

			PlaceMarking newMarking = new PlaceMarking();
			newMarking.setObject(oldMarking.getObject());
			newMarking.setPlace(oldMarking.getPlace());
			
			newMarking.setMsValue(firingMode.getValues().get(placeId));
			
			newRuntimeValues.put(placeId, newMarking);
		}
		
		// updates outgoing places' marking
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : firingMode.getTransition().getOut())
		{
			Place place = (Place)arc.getTarget();
			String placeId = place.getId();
			
			PlaceMarking currentMarking = null;
			
			// handles cyclic behavior when the same place is a source and a destination
			if(oldRuntimeValues.containsKey(placeId))
			{
				currentMarking = oldRuntimeValues.get(placeId);
				oldRuntimeValues.remove(placeId);
			}
			else
			{
				currentMarking = newRuntimeValues.get(placeId);
				newRuntimeValues.remove(placeId);
			}

			PlaceMarking newMarking = new PlaceMarking();
			newMarking.setObject(currentMarking.getObject());
			newMarking.setPlace(currentMarking.getPlace());
			
			Arc hlArc = (Arc)arc;
			// one of the outgoing arc has no inscription
			if(hlArc.getHlinscription() != null && hlArc.getHlinscription().getStructure() != null)
			{
				try
                {
	                AbstractValue inscriptionValue = evalManager.
	                		evaluateAdapt(hlArc.getHlinscription().getStructure(), firingMode.getParams());
	                
	                MSValue newMsValue = AbstractValueMath.append((MSValue)inscriptionValue,
	                		currentMarking.getMsValue());
	                
	                newMarking.setMsValue(newMsValue);
                }
                catch(UnknownVariableException e)
                {
	                e.printStackTrace();
                }	
			}
			else
			{
				newMarking.setMsValue(AbstractValueMath.lightCopy(currentMarking.getMsValue()));
			}
			newRuntimeValues.put(placeId, newMarking);
		}
		
		// creates a new net marking - applying a layered approach
		NetMarking netMarking = new NetMarking();
		netMarking.setNet(petrinet);
		
		// add new markings into the net
		for(String key : newRuntimeValues.keySet())
		{
			netMarking.getMarkings().add(newRuntimeValues.get(key));
			netMarking.getObjectAnnotations().add(newRuntimeValues.get(key));
		}
		
		// copy the rest of the place markings
		for(PlaceMarking marking : oldRuntimeValues.values())
		{
			netMarking.getMarkings().add(marking);
			netMarking.getObjectAnnotations().add(marking);
		}
		
		// create a hash map for efficiency
		Map<String, PlaceMarking> runtimeValues = new HashMap<String, PlaceMarking>();
		for(AbstractMarking marking : netMarking.getMarkings())
    	{
	    	if(marking instanceof PlaceMarking)
	    	{
	    		PlaceMarking pMarking = (PlaceMarking) marking;
	    		runtimeValues.put(pMarking.getPlace().getId(), pMarking);
	    	}
    	}
	    
		// creates a marking for enabled transitions
		List<Pair<Transition, List<FiringMode>>> enabledTransitions =
				checkTransitions(flatAccess.getTransitions(), runtimeValues, this.transitionManager);
		for(TransitionMarking marking : getTransitionMarkings(enabledTransitions))
		{
			netMarking.getMarkings().add(marking);
			netMarking.getObjectAnnotations().add(marking);
		}
		
		return netMarking;
	}
	
	private static List<TransitionMarking> getTransitionMarkings(
			List<Pair<Transition, List<FiringMode>>> enabledTransitions)
	{
		List<TransitionMarking> transitionMarkings = new ArrayList<TransitionMarking>();
		
		for(Pair<Transition, List<FiringMode>> pair : enabledTransitions)
		{
			if(pair.getValue() != null && pair.getValue().size() > 0)
        	{
				TransitionMarking marking = new TransitionMarking();
	    		marking.getModes().addAll(pair.getValue());	
	    		
	    		marking.setTransition(pair.getKey());
				marking.setObject(pair.getKey());
				
				transitionMarkings.add(marking);
        	}
		}
		
		return transitionMarkings;
	}
	
	private static List<Pair<Transition, List<FiringMode>>> checkTransitions(
			List<org.pnml.tools.epnk.pnmlcoremodel.Transition> transitions,
			Map<String, PlaceMarking> runtimeValues, TransitionManager transitionManager)
	{
		List<Pair<Transition, List<FiringMode>>> enabledTransitions = 
				new ArrayList<Pair<Transition,List<FiringMode>>>();
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : transitions)
		{
			Transition hlTransition = (Transition)transition;
			List<FiringMode> assignments = null;
            try
            {
            	assignments = transitionManager.checkTransition(hlTransition, runtimeValues);
            }
            catch(DependencyException e)
            {
	            e.printStackTrace();
            }
            catch(UnknownVariableException e)
            {
	            e.printStackTrace();
            }
            finally
            {
            	Pair<Transition, List<FiringMode>> pair = new Pair<Transition, List<FiringMode>>();
            	pair.setKey(hlTransition);
            	pair.setValue(assignments);
            	
            	enabledTransitions.add(pair);
            }
		}
		
		return enabledTransitions;
	}
}
