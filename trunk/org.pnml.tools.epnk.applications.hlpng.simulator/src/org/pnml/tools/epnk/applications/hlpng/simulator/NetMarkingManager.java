package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.ArcInscriptionManager;
import org.pnml.tools.epnk.applications.hlpng.firing.TransitionManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.FiringData;
import org.pnml.tools.epnk.applications.hlpng.runtime.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NetMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.TransitionMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.applications.hlpng.runtime.operators.TermManager;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class NetMarkingManager
{
	private PetriNet petrinet = null;
	private FlatAccess flatAccess = null;
	private TermManager operatorManager = null;
	private TransitionManager transitionManager = null;
	
	public NetMarkingManager(PetriNet petrinet, FlatAccess flatAccess,
			TermManager operatorManager, ArcInscriptionManager arcInscriptionManager)
	{
		this.petrinet = petrinet;
		this.flatAccess = flatAccess;
		this.operatorManager = operatorManager;
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
				AbstractValue value = operatorManager.getHandler(term.getClass()).handle(term);
				
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

			TransitionMarking marking = this.transitionManager.checkTransition(
					hlTransition, runtimeValues);
			
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
			for(FiringData firingData : firingMode.getValues())
			{
				PlaceMarking oldMarking = firingData.getPlaceMarking();
				oldRuntimeValues.remove(oldMarking.getPlace().getId());
				
				AbstractValue value = firingData.getMsTerm().getValue();
				int multiplicity = firingData.getMsTerm().getMultiplicity();
				
				MSValue newMsValue = AbstractValueMath
						.subtract(oldMarking.getMsValue(), value, multiplicity);
				
				PlaceMarking newMarking = new PlaceMarking();
				newMarking.setObject(oldMarking.getObject());
				newMarking.setPlace(oldMarking.getPlace());
				newMarking.setMsValue(newMsValue);
				
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

			TransitionMarking marking = this.transitionManager.checkTransition(
					hlTransition, runtimeValues);
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
