package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.pnml.tools.epnk.applications.hlpng.firing.ArcInscriptionManager;
import org.pnml.tools.epnk.applications.hlpng.firing.TransitionManager;
import org.pnml.tools.epnk.applications.hlpng.operations.TermManager;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

import runtime.AbstractMarking;
import runtime.AbstractValue;
import runtime.MSValue;
import runtime.NetMarking;
import runtime.PlaceMarking;
import runtime.RuntimeFactory;
import transitionruntime.FiringData;
import transitionruntime.FiringMode;
import transitionruntime.TransitionMarking;

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
		NetMarking netMarking = RuntimeFactory.eINSTANCE.createNetMarking();
		netMarking.setNet(petrinet);
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Place place : flatAccess.getPlaces())
		{
			Place hlPlace = (Place)place;
			if(hlPlace.getHlinitialMarking() != null &&
					hlPlace.getHlinitialMarking().getStructure() != null)
			{
				Term term = hlPlace.getHlinitialMarking().getStructure();
				AbstractValue value = operatorManager.getHandler(term.getClass()).handle(term);
				
				PlaceMarking marking  = RuntimeFactory.eINSTANCE.createPlaceMarking();
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
		NetMarking netMarking = RuntimeFactory.eINSTANCE.createNetMarking();
		netMarking.setNet(petrinet);
		
		Collection<AbstractMarking> markings = EcoreUtil.copyAll(prevMarking.getMarkings());
		
		Map<String, PlaceMarking> oldRuntimeValues = new HashMap<String, PlaceMarking>();
		for(AbstractMarking marking : markings)
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
				
				MSValue newMsValue = EcoreUtil.copy(oldMarking.getMsValue());
				AbstractValue value = firingData.getMsTerm().getValue();
				int multiplicity = firingData.getMsTerm().getMultiplicity();
				
				int n = newMsValue.getMultiplicity(value) - multiplicity;
				if(n > 0)
				{
					newMsValue.getValues().put(value, n);
				}
				else
				{
					newMsValue.getValues().remove(value);
				}
				
				PlaceMarking newMarking = RuntimeFactory.eINSTANCE.createPlaceMarking();
				newMarking.setObject(oldMarking.getObject());
				newMarking.setPlace(oldMarking.getPlace());
				newMarking.setMsValue(newMsValue);
				newMarking.setDirty(true);
				
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
