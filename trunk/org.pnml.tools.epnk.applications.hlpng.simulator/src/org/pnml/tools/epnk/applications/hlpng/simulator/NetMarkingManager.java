package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.pnml.tools.epnk.applications.hlpng.operations.OperatorManager;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

import runtime.AbstractValue;
import runtime.MSValue;
import runtime.NetMarking;
import runtime.PlaceMarking;
import runtime.RuntimeFactory;
import transitionruntime.FiringMode;
import transitionruntime.TransitionMarking;
import transitionruntime.TransitionruntimeFactory;

public class NetMarkingManager
{
	private PetriNet petrinet = null;
	private FlatAccess flatAccess = null;
	private OperatorManager operatorManager = null;
	private ArcInscriptionManager arcInscriptionManager = null;
	
	public NetMarkingManager(PetriNet petrinet, FlatAccess flatAccess,
			OperatorManager operatorManager, ArcInscriptionManager arcInscriptionManager)
	{
		this.petrinet = petrinet;
		this.flatAccess = flatAccess;
		this.operatorManager = operatorManager;
		this.arcInscriptionManager = arcInscriptionManager;
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
				AbstractValue value = operatorManager.getOperator(term.getClass()).handle(term);
				
				PlaceMarking marking  = RuntimeFactory.eINSTANCE.createPlaceMarking();
				marking.setPlace(hlPlace);
				marking.setMsValue((MSValue)value);
				marking.setObject(hlPlace);
				
				netMarking.getMarkings().add(marking);
				netMarking.getObjectAnnotations().add(marking);
			}
		}
		
		Map<String, PlaceMarking> runtimeValues = new HashMap<String, PlaceMarking>();
	    for(PlaceMarking marking : netMarking.getMarkings())
    	{
    		runtimeValues.put(marking.getPlace().getId(), marking);
    	}
	    
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : flatAccess.getTransitions())
		{
			Transition hlTransition = (Transition)transition;

			TransitionMarking marking = checkTransition(hlTransition, 
					runtimeValues, this.arcInscriptionManager);
			if(marking != null)
			{
				marking.setTransition(hlTransition);
				marking.setObject(hlTransition);
				
				netMarking.getTransitionMarkings().add(marking);
				netMarking.getObjectAnnotations().add(marking);				
			}
		}
		
		return netMarking;
	}
	
	public NetMarking createNetMarking(NetMarking prevMarking, FiringMode firingMode)
	{
		NetMarking netMarking = RuntimeFactory.eINSTANCE.createNetMarking();
		netMarking.setNet(petrinet);
		
		Collection<PlaceMarking> markings = EcoreUtil.copyAll(prevMarking.getMarkings());
		
		Map<String, PlaceMarking> oldRuntimeValues = new HashMap<String, PlaceMarking>();
		for(PlaceMarking marking : markings)
		{
			oldRuntimeValues.put(marking.getPlace().getId(), marking);
		}
		// replace "dirty" places. Copy on demand
		{
			for(PlaceMarking oldMarking : firingMode.getValues().keySet())
			{
				oldRuntimeValues.remove(oldMarking.getPlace().getId());
				
				MSValue newMsValue = EcoreUtil.copy(oldMarking.getMsValue());
				AbstractValue value = firingMode.getValues().get(oldMarking);
				int n = newMsValue.getMultiplicity(value) - 1;
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
	    for(PlaceMarking marking : netMarking.getMarkings())
    	{
    		runtimeValues.put(marking.getPlace().getId(), marking);
    	}
	    
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : flatAccess.getTransitions())
		{
			Transition hlTransition = (Transition)transition;

			TransitionMarking marking = checkTransition(hlTransition, 
					runtimeValues, this.arcInscriptionManager);
			if(marking != null)
			{
				marking.setTransition(hlTransition);
				marking.setObject(hlTransition);
				
				netMarking.getTransitionMarkings().add(marking);
				netMarking.getObjectAnnotations().add(marking);				
			}
		}
		
		return netMarking;
	}

	private static TransitionMarking checkTransition(Transition transition, Map<String, 
			PlaceMarking> runtimeValues, ArcInscriptionManager arcInscriptionManager)
	{
		TransitionMarking marking = TransitionruntimeFactory.eINSTANCE.createTransitionMarking();
		
		Map<String, List<List<AbstractValue>>> modes = new HashMap<String, List<List<AbstractValue>>>();
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : transition.getIn())
		{
			Place place = (Place) arc.getSource();
			PlaceMarking placeMarking = runtimeValues.get(place.getId());
			
			List<List<AbstractValue>> matches = arcInscriptionManager
					.matchesInscription((Arc)arc, placeMarking, placeMarking.isDirty());
			
			if(matches != null)
			{
				modes.put(place.getId(), matches);
			}
		}

		if(modes.size() == transition.getIn().size())
		{
			FiringMode mode = TransitionruntimeFactory.eINSTANCE.createFiringMode();
			
			for(String placeId : modes.keySet())
			{			
				for(List<AbstractValue> listOfValues : modes.get(placeId))
				{
					for(AbstractValue value : listOfValues)
					{
						mode.getValues().put(runtimeValues.get(placeId), value);
					}
				}
			}
			
			if(mode.getValues().size() > 0)
			{
				marking.getModes().add(mode);
				return marking;
			}	
		}
		
		return null;
	}
}
