package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

import runtime.PlaceMarking;
import transitionruntime.FiringData;
import transitionruntime.FiringMode;
import transitionruntime.MSTerm;
import transitionruntime.TransitionMarking;
import transitionruntime.TransitionruntimeFactory;
import runtime.RuntimeVariable;

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
		// for each term in an inscription find all possible assignments
		List<List<InscriptionMatch>> entries = new ArrayList<List<InscriptionMatch>>();

		for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : transition.getIn())
		{
			Place place = (Place) arc.getSource();
			PlaceMarking placeMarking = runtimeValues.get(place.getId());
			
			List<InscriptionMatch> matches = arcInscriptionManager
					.matchesInscription((Arc)arc, placeMarking, placeMarking.isDirty());
			
			if(matches != null)
			{
				entries.add(matches);
			}
		}
		
		if(entries.size() > 0 && entries.size() == transition.getIn().size())
		{	
			List<List<Pair<InscriptionMatch, VariableAssignmnet>>> product = null;
			
			if(entries.size() > 1)
			{
				List<List<Pair<InscriptionMatch, VariableAssignmnet>>> subsets =
						new ArrayList<List<Pair<InscriptionMatch,VariableAssignmnet>>>();
				for(List<InscriptionMatch> entry : entries)
				{
					List<Pair<InscriptionMatch, VariableAssignmnet>> matchPairs =
							new ArrayList<Pair<InscriptionMatch,VariableAssignmnet>>();
					for(InscriptionMatch match : entry)
					{
						for(VariableAssignmnet assignmnet : match.getAssignmnets())
						{
							Pair<InscriptionMatch, VariableAssignmnet> pair = 
									new Pair<InscriptionMatch, VariableAssignmnet>(match, assignmnet);
							matchPairs.add(pair);
						}
					}
					subsets.add(matchPairs);
				}
				
				CartesianProduct<Pair<InscriptionMatch, VariableAssignmnet>> cartesianProduct = 
						new CartesianProduct<Pair<InscriptionMatch, VariableAssignmnet>>();
				
				product = cartesianProduct.product(subsets);
			}
			
			TransitionMarking marking = TransitionruntimeFactory.eINSTANCE.createTransitionMarking();

			if(product != null)
			{
				for(List<Pair<InscriptionMatch, VariableAssignmnet>> subset : product)
				{
					marking.getModes().add(createFiringMode(subset, runtimeValues));
				}
			}
			else
			{
				for(InscriptionMatch match : entries.get(0))
				{
					List<FiringMode> list = createFiringMode(match, runtimeValues);
					for(FiringMode mode : list)
					{
						marking.getModes().add(mode);
					}
				}
			}
			return marking;
		}
		
		return null;
	}
	
	private static FiringMode createFiringMode(
			List<Pair<InscriptionMatch, VariableAssignmnet>> matches,
			Map<String, PlaceMarking> runtimeValues)
	{
		FiringMode mode = TransitionruntimeFactory.eINSTANCE.createFiringMode();
		
		for(Pair<InscriptionMatch, VariableAssignmnet> matchPair : matches)
		{
			InscriptionMatch match = matchPair.getKey();
			VariableAssignmnet assignmnet = matchPair.getValue();
			
			MSTerm msTerm = TransitionruntimeFactory.eINSTANCE.createMSTerm();
			msTerm.setMultiplicity(match.getMultiplicity());
			msTerm.setPlaceId(match.getPlaceId());
			msTerm.setValue(assignmnet.getParentValue());
			
			for(RuntimeVariable variable : assignmnet.getAssignments().keySet())
			{
				FiringData data = TransitionruntimeFactory.eINSTANCE.createFiringData();
				data.setPlaceMarking(runtimeValues.get(match.getPlaceId()));
				data.setMsTerm(msTerm);
				data.setVariable(variable);
				data.setVarValue(assignmnet.getAssignments().get(variable));

				mode.getValues().add(data);	
			}
		}
		
		return mode;
	}
	
	private static List<FiringMode> createFiringMode(InscriptionMatch match,
			Map<String, PlaceMarking> runtimeValues)
	{
		List<FiringMode> list = new ArrayList<FiringMode>();
		
		for(VariableAssignmnet assignmnet : match.getAssignmnets())
		{	
			FiringMode mode = TransitionruntimeFactory.eINSTANCE.createFiringMode();
			
			MSTerm msTerm = TransitionruntimeFactory.eINSTANCE.createMSTerm();
			msTerm.setMultiplicity(match.getMultiplicity());
			msTerm.setPlaceId(match.getPlaceId());
			msTerm.setValue(assignmnet.getParentValue());
			
			for(RuntimeVariable variable : assignmnet.getAssignments().keySet())
			{
				FiringData data = TransitionruntimeFactory.eINSTANCE.createFiringData();
				data.setPlaceMarking(runtimeValues.get(match.getPlaceId()));
				data.setMsTerm(msTerm);
				data.setVariable(variable);
				data.setVarValue(assignmnet.getAssignments().get(variable));

				mode.getValues().add(data);	
			}
			
			list.add(mode);
		}
		
		return list;
	}
}
