package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.pnml.tools.epnk.applications.hlpng.operations.TermManager;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
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
import transitionruntime.FiringData;
import transitionruntime.FiringMode;
import transitionruntime.MSTerm;
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
		List<Pair<String, List<Pair<AbstractValue, Integer>>>> entries = 
				new ArrayList<Pair<String, List<Pair<AbstractValue, Integer>>>>();
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : transition.getIn())
		{
			Place place = (Place) arc.getSource();
			PlaceMarking placeMarking = runtimeValues.get(place.getId());
			
			List<Pair<AbstractValue, Integer>> matches = arcInscriptionManager
					.matchesInscription((Arc)arc, placeMarking, placeMarking.isDirty());
			
			if(matches != null)
			{
				entries.add(new Pair<String, List<Pair<AbstractValue, Integer>>>(place.getId(),
						matches));
			}
		}

		if(entries.size() == transition.getIn().size())
		{
			return cartesianProduct(entries, runtimeValues);
		}
		
		return null;
	}
	
	private static TransitionMarking cartesianProduct(
			List<Pair<String, List<Pair<AbstractValue, Integer>>>> modes,
			Map<String, PlaceMarking> runtimeValues)
	{
		TransitionMarking marking = TransitionruntimeFactory.eINSTANCE.createTransitionMarking();
		
		if(modes.size() == 1)
		{
			for(Pair<String, List<Pair<AbstractValue, Integer>>> pair : modes)
			{			
				String placeId = pair.getKey();
				
				for(Pair<AbstractValue, Integer> value : pair.getValue())
				{
					MSTerm msTerm = TransitionruntimeFactory.eINSTANCE.createMSTerm();
					msTerm.setMultiplicity(value.getValue());
					msTerm.setPlaceId(placeId);
					msTerm.setValue(value.getKey());

					FiringData data = TransitionruntimeFactory.eINSTANCE.createFiringData();
					data.setPlaceMarking(runtimeValues.get(placeId));
					data.setMsTerm(msTerm);
					
					FiringMode mode = TransitionruntimeFactory.eINSTANCE.createFiringMode();
					mode.getValues().add(data);
					marking.getModes().add(mode);
				}
			}
		}
		else if(modes.size() > 1)
		{
			List<List<MSTerm>> partialModes = cartesianProduct(modes.get(0), modes.get(1));
			
			for(int i = 2; i < modes.size(); i++)
			{
				partialModes = cartesianProduct(partialModes, modes.get(i));
			}
			
			for(List<MSTerm> partialMode : partialModes)
			{
				FiringMode mode = TransitionruntimeFactory.eINSTANCE.createFiringMode();
				
				for(MSTerm term : partialMode)
				{
					MSTerm msTerm = TransitionruntimeFactory.eINSTANCE.createMSTerm();
					msTerm.setMultiplicity(term.getMultiplicity());
					msTerm.setPlaceId(term.getPlaceId());
					msTerm.setValue(term.getValue());
					
					FiringData data = TransitionruntimeFactory.eINSTANCE.createFiringData();
					data.setPlaceMarking(runtimeValues.get(term.getPlaceId()));
					data.setMsTerm(msTerm);
		
					mode.getValues().add(data);
				}
				
				if(mode.getValues().size() > 0)
				{
					marking.getModes().add(mode);
				}
			}
		}
		return marking;
	}
	
	private static List<List<MSTerm>> cartesianProduct(
			Pair<String, List<Pair<AbstractValue, Integer>>> p1,
			Pair<String, List<Pair<AbstractValue, Integer>>> p2)
	{
		List<List<MSTerm>> result = new ArrayList<List<MSTerm>>();
		
		for(Pair<AbstractValue, Integer> a1 : p1.getValue())
		{
			for(Pair<AbstractValue, Integer> a2 : p2.getValue())
			{
				List<MSTerm> l = new ArrayList<MSTerm>();
				
				{
					MSTerm term = TransitionruntimeFactory.eINSTANCE.createMSTerm();
					term.setMultiplicity(a1.getValue());
					term.setPlaceId(p1.getKey());
					term.setValue(a1.getKey());
					
					l.add(term);
				}
				{
					MSTerm term = TransitionruntimeFactory.eINSTANCE.createMSTerm();
					term.setMultiplicity(a2.getValue());
					term.setPlaceId(p2.getKey());
					term.setValue(a2.getKey());
					
					l.add(term);
				}
				
				result.add(l);
			}
		}
		return result;
	}
	
	private static List<List<MSTerm>> cartesianProduct(
			List<List<MSTerm>> oldResult, 
			Pair<String, List<Pair<AbstractValue, Integer>>> p)
	{
		List<List<MSTerm>> result = new ArrayList<List<MSTerm>>();
		
		for(List<MSTerm> subset : oldResult)
		{
			List<MSTerm> l = new ArrayList<MSTerm>();
			for(MSTerm p1 : subset)
			{
				l.add(p1);
				for(Pair<AbstractValue, Integer> a : p.getValue())
				{
					MSTerm term = TransitionruntimeFactory.eINSTANCE.createMSTerm();
					term.setMultiplicity(a.getValue());
					term.setPlaceId(p.getKey());
					term.setValue(a.getKey());
					
					l.add(term);
				}
			}
			result.add(l);
		}
		return result;
	}
}
