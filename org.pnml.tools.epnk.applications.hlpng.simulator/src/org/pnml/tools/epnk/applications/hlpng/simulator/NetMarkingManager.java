package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.ArcInscriptionManager;
import org.pnml.tools.epnk.applications.hlpng.firing.DependencyException;
import org.pnml.tools.epnk.applications.hlpng.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.firing.TransitionManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NetMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.TransitionMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.operators.UnknownVariableException;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class NetMarkingManager
{
	private PetriNet petrinet = null;
	private FlatAccess flatAccess = null;
	private EvaluationManager operatorManager = null;
	private TransitionManager transitionManager = null;
	
	public NetMarkingManager(PetriNet petrinet, FlatAccess flatAccess,
			ArcInscriptionManager arcInscriptionManager)
	{
		this.petrinet = petrinet;
		this.flatAccess = flatAccess;
		this.operatorManager = EvaluationManager.getInstance();
		this.transitionManager = new TransitionManager(arcInscriptionManager);
	}
	
	public NetMarking createNetMarking()
	{
		NetMarking netMarking = new NetMarking();
		netMarking.setNet(petrinet);
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Place place : flatAccess.getPlaces())
		{
			Place hlPlace = (Place)place;
			if(hlPlace.getHlinitialMarking() != null &&
					hlPlace.getHlinitialMarking().getStructure() != null)
			{
				Term term = hlPlace.getHlinitialMarking().getStructure();
				AbstractValue value = null;
                try
                {
	                value = operatorManager.evaluate(term, null);
                }
                catch(UnknownVariableException e)
                {
	                e.printStackTrace();
                }
				
				PlaceMarking marking  = new PlaceMarking();
				marking.setPlace(hlPlace);
				marking.setMsValue((MSValue)value);
				marking.setObject(hlPlace);
				
				netMarking.getMarkings().add(marking);
				netMarking.getObjectAnnotations().add(marking);
			}
		}
		
		Map<String, PlaceMarking> runtimeValues = new HashMap<String, PlaceMarking>();
	    for(AbstractMarking marking : netMarking.getMarkings())
    	{
	    	if(marking instanceof PlaceMarking)
	    	{
	    		PlaceMarking pMarking = (PlaceMarking) marking;
	    		runtimeValues.put(pMarking.getPlace().getId(), pMarking);
	    	}
    	}
	    
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : flatAccess.getTransitions())
		{
			Transition hlTransition = (Transition)transition;

			TransitionMarking marking = null;
            try
            {
	            marking = this.transitionManager.checkTransition(
	            		hlTransition, runtimeValues);
            }
            catch(DependencyException e)
            {
	            e.printStackTrace();
            }
            catch(UnknownVariableException e)
            {
	            e.printStackTrace();
            }
			
			if(marking != null && marking.getModes().size() > 0)
			{
				marking.setTransition(hlTransition);
				marking.setObject(hlTransition);
				
				netMarking.getMarkings().add(marking);
				netMarking.getObjectAnnotations().add(marking);				
			}
		}
		
		return netMarking;
	}
	
	public NetMarking createNetMarking(NetMarking prevMarking, FiringMode firingMode)
	{
		NetMarking netMarking = new NetMarking();
		netMarking.setNet(petrinet);

		Map<String, PlaceMarking> oldRuntimeValues = new HashMap<String, PlaceMarking>();
		for(AbstractMarking marking : prevMarking.getMarkings())//markings)
    	{
	    	if(marking instanceof PlaceMarking)
	    	{
	    		PlaceMarking pMarking = (PlaceMarking) marking;
	    		oldRuntimeValues.put(pMarking.getPlace().getId(), pMarking);
	    	}
    	}

		// replace "dirty" places. Copy on demand
		{
			for(String placeId : firingMode.getValues().keySet())
			{
				PlaceMarking oldMarking = oldRuntimeValues.get(placeId);
				oldRuntimeValues.remove(placeId);

				PlaceMarking newMarking = new PlaceMarking();
				newMarking.setObject(oldMarking.getObject());
				newMarking.setPlace(oldMarking.getPlace());
				newMarking.setMsValue(firingMode.getValues().get(placeId));
				
				netMarking.getMarkings().add(newMarking);
				netMarking.getObjectAnnotations().add(newMarking);
			}
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
	    
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : flatAccess.getTransitions())
		{
			Transition hlTransition = (Transition)transition;

			TransitionMarking marking = null;
            try
            {
	            marking = this.transitionManager.checkTransition(
	            		hlTransition, runtimeValues);
            }
            catch(DependencyException e)
            {
	            e.printStackTrace();
            }
            catch(UnknownVariableException e)
            {
	            e.printStackTrace();
            }
			if(marking != null && marking.getModes().size() > 0)
			{
				marking.setTransition(hlTransition);
				marking.setObject(hlTransition);
				
				netMarking.getMarkings().add(marking);
				netMarking.getObjectAnnotations().add(marking);				
			}
		}
		
		return netMarking;
	}
}
