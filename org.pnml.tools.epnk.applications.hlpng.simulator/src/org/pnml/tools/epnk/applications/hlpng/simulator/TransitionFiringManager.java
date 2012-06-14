package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NetMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.RuntimeState;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.DependencyException;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IDWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TransitionManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PlaceNode;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;

public class TransitionFiringManager
{
	private Map<IDWrapper, Place> placeMap = new HashMap<IDWrapper, Place>();
	private FlatAccess flatAccess = null;
	public TransitionFiringManager(FlatAccess flatAccess)
	{
		this.flatAccess = flatAccess;
		for(org.pnml.tools.epnk.pnmlcoremodel.Place place : flatAccess.getPlaces())
		{
			this.placeMap.put(new IDWrapper(place), (Place)place);
		}
	}
	
	public Pair<List<Pair<Place, MSValue>>, Map<IDWrapper, MSValue>> createNextMarking(EvaluationManager evalManager,
			Map<IDWrapper, MSValue> oldValuesMap, FiringMode firingMode)
	{
		Set<IDWrapper> oldPlaces = new HashSet<IDWrapper>(oldValuesMap.keySet());
		Map<IDWrapper, MSValue> newValuesMap = new HashMap<IDWrapper, MSValue>();
		
		// updating incoming places
		for(IDWrapper placeId : firingMode.getValues().keySet())
		{
			oldPlaces.remove(placeId);
			newValuesMap.put(placeId, 
					AbstractValueMath.subtract(oldValuesMap.get(placeId), 
							firingMode.getValues().get(placeId)));
		}
		
		// updates outgoing places
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : firingMode.getTransition().getOut())
		{
			Place place = (Place)this.flatAccess.resolve((PlaceNode)arc.getTarget());
			if(place != null)
			{
				IDWrapper placeId = new IDWrapper(place);
				
				MSValue currentMarking = null;
				
				// handles cyclic behavior when the same place is a source and a destination
				if(oldPlaces.contains(placeId))
				{
					currentMarking = oldValuesMap.get(placeId);
					oldPlaces.remove(placeId);
				}
				else
				{
					currentMarking = newValuesMap.get(placeId);
					newValuesMap.remove(placeId);
				}

				MSValue newMarking = null;
				
				// one of the outgoing arc has no inscription
				Arc hlArc = (Arc)arc;
				if(hlArc.getHlinscription() != null && hlArc.getHlinscription().getStructure() != null)
				{
					try
	                {
		                AbstractValue inscriptionValue = evalManager.
		                		evaluate(hlArc.getHlinscription().getStructure(), firingMode.getParams());
		                
		                newMarking = AbstractValueMath.append((MSValue)inscriptionValue,
		                		currentMarking);
	                }
	                catch(UnknownVariableException e)
	                {
		                e.printStackTrace();
	                }	
				}
				else
				{
					newMarking = AbstractValueMath.lightCopy(currentMarking);
				}
				newValuesMap.put(placeId, newMarking);	
			}
		}
		
		List<Pair<Place, MSValue>> runtimeValues = new ArrayList<Pair<Place,MSValue>>();
		
		for(IDWrapper key : oldValuesMap.keySet())
		{
			if(!newValuesMap.containsKey(key))
			{
				newValuesMap.put(key, oldValuesMap.get(key));	
			}
		}
		
		for(IDWrapper key : newValuesMap.keySet())
		{
			Pair<Place, MSValue> p = new Pair<Place, MSValue>();
			p.setKey(placeMap.get(key));
			p.setValue(newValuesMap.get(key));
			runtimeValues.add(p);
		}
		
		Pair<List<Pair<Place, MSValue>>, Map<IDWrapper, MSValue>> p = 
				new Pair<List<Pair<Place,MSValue>>, Map<IDWrapper,MSValue>>();
		p.setKey(runtimeValues);
		p.setValue(newValuesMap);
		return p;
	}
	
	public List<Pair<Place, MSValue>> createInitialMarking(EvaluationManager evalManager)
	{
		List<Pair<Place, MSValue>> runtimeValues = new ArrayList<Pair<Place,MSValue>>();
		for(org.pnml.tools.epnk.pnmlcoremodel.Place place : this.flatAccess.getPlaces())
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
			
			Pair<Place, MSValue> pair = new Pair<Place, MSValue>();
			pair.setKey(hlPlace);
			pair.setValue(msValue);
			
			runtimeValues.add(pair);
		}
		return runtimeValues;
	}
	
	public List<Pair<Place, MSValue>> copyPrevPlaceMarking(NetMarking prevMarking)
	{
		List<Pair<Place, MSValue>> runtimeValues = new ArrayList<Pair<Place,MSValue>>();
		
		for(AbstractMarking marking : prevMarking.getMarkings())
		{
			if(marking instanceof PlaceMarking)
			{
				Pair<Place, MSValue> pair = new Pair<Place, MSValue>();
				pair.setKey(((PlaceMarking) marking).getPlace());
				pair.setValue(((PlaceMarking) marking).getMsValue());
				
				runtimeValues.add(pair);
			}
		}

		return runtimeValues;
	}
	
	public List<FiringMode> computeFiringModes(
			List<org.pnml.tools.epnk.pnmlcoremodel.Transition> transitions,
			Map<IDWrapper, MSValue> runtimeValues, TransitionManager transitionManager)
	{
		List<FiringMode> firingModes = new ArrayList<FiringMode>();
		
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
            	if(assignments != null && assignments.size() > 0)
            	{
            		for(FiringMode mode : assignments)
            		{
            			if(mode != null && mode.getParams().size() > 0)
            			{
            				firingModes.add(mode);
            			}
            		}
            	}
            }
		}
		
		return firingModes;
	}
	
	public Map<IDWrapper, MSValue> createRuntimeValueMap(List<Pair<Place, MSValue>> runtimeValuesList)
	{
		// puts place markings into a map for a better performance
		Map<IDWrapper, MSValue> runtimeValues = new HashMap<IDWrapper, MSValue>();
		
		for(Pair<Place, MSValue> pair : runtimeValuesList)
		{
			runtimeValues.put(new IDWrapper(pair.getKey()), pair.getValue());
		}
		
		return runtimeValues;
	}
	
    /* ---------------------------------------------------------------------- */
    public IRuntimeState createInitialState(EvaluationManager evalManager,
            List<org.pnml.tools.epnk.pnmlcoremodel.Transition> transitions,
            TransitionManager transitionManager)
    {
        List<Pair<Place, MSValue>> initMarking = createInitialMarking(evalManager);
        
        RuntimeState runtimeState = new RuntimeState();
        
        for(Pair<Place, MSValue> p : initMarking)
        {
            runtimeState.addValue(p.getKey(), p.getValue());
        }
        
        for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : transitions)
        {
            Transition hlTransition = (Transition)transition;
            List<FiringMode> assignments = null;
            try
            {
                assignments = transitionManager.checkTransition(hlTransition, runtimeState.getValues());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if(assignments != null && assignments.size() > 0)
                {
                    runtimeState.addFiringModes(hlTransition, assignments);
                }
            }
        }
        
        return runtimeState;
    }
}
