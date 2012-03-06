package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

import runtime.AbstractValue;
import runtime.PlaceMarking;
import transitionruntime.FiringData;
import transitionruntime.FiringMode;
import transitionruntime.MSTerm;
import transitionruntime.TransitionMarking;
import transitionruntime.TransitionruntimeFactory;

public class TransitionManager
{
	private ArcInscriptionManager arcInscriptionManager = null;
	
	public TransitionManager(ArcInscriptionManager arcInscriptionManager)
	{
		this.arcInscriptionManager = arcInscriptionManager;
	}
	
	public TransitionMarking checkTransition(Transition transition, Map<String, 
			PlaceMarking> runtimeValues)
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
