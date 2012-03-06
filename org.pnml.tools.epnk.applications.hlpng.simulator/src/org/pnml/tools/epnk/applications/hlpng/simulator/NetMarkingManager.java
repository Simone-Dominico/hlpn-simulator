package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.pnml.tools.epnk.applications.hlpng.operations.TermManager;
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
	private TermManager operatorManager = null;
	private ArcInscriptionManager arcInscriptionManager = null;
	
	public NetMarkingManager(PetriNet petrinet, FlatAccess flatAccess,
			TermManager operatorManager, ArcInscriptionManager arcInscriptionManager)
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
	    for(PlaceMarking marking : netMarking.getMarkings())
    	{
    		runtimeValues.put(marking.getPlace().getId(), marking);
    	}
	    
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : flatAccess.getTransitions())
		{
			Transition hlTransition = (Transition)transition;

			TransitionMarking marking = checkTransition(hlTransition, 
					runtimeValues, this.arcInscriptionManager);
			if(marking != null && marking.getModes().size() > 0)
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
			if(marking != null && marking.getModes().size() > 0)
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
		List<Pair<String, List<AbstractValue>>> entries = 
				new ArrayList<Pair<String,List<AbstractValue>>>();
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : transition.getIn())
		{
			Place place = (Place) arc.getSource();
			PlaceMarking placeMarking = runtimeValues.get(place.getId());
			
			List<AbstractValue> matches = arcInscriptionManager
					.matchesInscription((Arc)arc, placeMarking, placeMarking.isDirty());
			
			if(matches != null)
			{
				entries.add(new Pair<String, List<AbstractValue>>(place.getId(),
						matches));
			}
		}

		if(entries.size() == transition.getIn().size())
		{
			return cartesianProduct(entries, runtimeValues);
		}
		
		return null;
	}
	
	private static TransitionMarking cartesianProduct(List<Pair<String, List<AbstractValue>>> modes,
			Map<String, PlaceMarking> runtimeValues)
	{
		TransitionMarking marking = TransitionruntimeFactory.eINSTANCE.createTransitionMarking();
		
		if(modes.size() == 1)
		{
			for(Pair<String, List<AbstractValue>> pair : modes)
			{			
				String placeId = pair.getKey();
				
				for(AbstractValue value : pair.getValue())
				{
					FiringMode mode = TransitionruntimeFactory.eINSTANCE.createFiringMode();
					mode.getValues().put(runtimeValues.get(placeId), value);
					marking.getModes().add(mode);
				}
			}
		}
		else if(modes.size() > 1)
		{
			List<List<Pair<String, AbstractValue>>> partialModes = cartesianProduct(
					modes.get(0), modes.get(1));
			
			for(int i = 2; i < modes.size(); i++)
			{
				partialModes = cartesianProduct(partialModes, modes.get(i));
			}
			
			for(List<Pair<String, AbstractValue>> partialMode : partialModes)
			{
				FiringMode mode = TransitionruntimeFactory.eINSTANCE.createFiringMode();
				
				for(Pair<String, AbstractValue> pair : partialMode)
				{
					mode.getValues().put(runtimeValues.get(pair.getKey()), pair.getValue());
				}
				
				if(mode.getValues().size() > 0)
				{
					marking.getModes().add(mode);
				}
			}
		}
		return marking;
	}
	
	private static List<List<Pair<String, AbstractValue>>> cartesianProduct(
			Pair<String, List<AbstractValue>> p1,
			Pair<String, List<AbstractValue>> p2)
	{
		List<List<Pair<String, AbstractValue>>> result = new ArrayList<List<Pair<String, AbstractValue>>>();
		
		for(AbstractValue a1 : p1.getValue())
		{
			for(AbstractValue a2 : p2.getValue())
			{
				List<Pair<String, AbstractValue>> l = new ArrayList<Pair<String, AbstractValue>>();
				l.add(new Pair<String, AbstractValue>(p1.getKey(), a1));
				l.add(new Pair<String, AbstractValue>(p2.getKey(), a2));
				
				result.add(l);
			}
		}
		return result;
	}
	
	private static List<List<Pair<String, AbstractValue>>> cartesianProduct(
			List<List<Pair<String, AbstractValue>>> oldResult,
			Pair<String, List<AbstractValue>> p)
	{
		List<List<Pair<String, AbstractValue>>> result = new ArrayList<List<Pair<String, AbstractValue>>>();
		
		for(List<Pair<String, AbstractValue>> subset : oldResult)
		{
			List<Pair<String, AbstractValue>> l = new ArrayList<Pair<String, AbstractValue>>();
			for(Pair<String, AbstractValue> p1 : subset)
			{
				l.add(p1);
				for(AbstractValue a : p.getValue())
				{
					l.add(new Pair<String, AbstractValue>(p.getKey(), a));
				}
			}
			result.add(l);
		}
		return result;
	}
}
