package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.RuntimeState;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IDWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TransitionManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PlaceNode;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;

public class TransitionFiringManager
{
	private FlatAccess flatAccess = null;
	private RuntimeValueFactory runtimeValueFactory = null;
	public TransitionFiringManager(FlatAccess flatAccess, RuntimeValueFactory runtimeValueFactory)
	{
		this.flatAccess = flatAccess;
		this.runtimeValueFactory = runtimeValueFactory;
	}
	
    public void updateState(IRuntimeState state, TransitionManager transitionManager)
    {
    	state.getModes().clear();
    	
    	Map<IDWrapper, List<FiringMode>> firingModes = computeFiringModes(state.getValues(),
    			flatAccess.getTransitions(), transitionManager);
        
        for(IDWrapper transition : firingModes.keySet())
        {
        	state.addFiringModes((Transition)transition.getId(), firingModes.get(transition));
        }
    }
	
    public IRuntimeState createNextState(IRuntimeState prevState, 
    		EvaluationManager evaluationManager, FiringMode firingMode, 
    		TransitionManager transitionManager)
    {
    	Map<IDWrapper, IMSValue> currentValues = prevState.getClonedValues();
    	
    	Map<IDWrapper, IMSValue> result = createNextMarking(evaluationManager, 
    			currentValues, firingMode, flatAccess, runtimeValueFactory);
    	
    	Map<IDWrapper, List<FiringMode>> firingModes = computeFiringModes(result,
    			flatAccess.getTransitions(), transitionManager);
        
    	IRuntimeState runtimeState = new RuntimeState();
    	for(IDWrapper place : result.keySet())
        {
        	runtimeState.addValue((Place)place.getId(), result.get(place));
        }
        for(IDWrapper transition : firingModes.keySet())
        {
        	runtimeState.addFiringModes((Transition)transition.getId(), firingModes.get(transition));
        }
        
        return runtimeState;
    }
    
    public IRuntimeState createInitialState(EvaluationManager evalManager,
            List<org.pnml.tools.epnk.pnmlcoremodel.Transition> transitions,
            TransitionManager transitionManager)
    {        
        RuntimeState runtimeState = new RuntimeState();
        
        for(org.pnml.tools.epnk.pnmlcoremodel.Place place : flatAccess.getPlaces())
		{
			Place hlPlace = (Place)place;
			
			IMSValue msValue = null;
			
			if(hlPlace.getHlinitialMarking() != null &&
					hlPlace.getHlinitialMarking().getStructure() != null)
			{
				Term term = hlPlace.getHlinitialMarking().getStructure();
				
                try
                {
                	msValue = (IMSValue)evalManager.evaluate(term, null);
                }
                catch(UnknownVariableException e)
                {
	                e.printStackTrace();
                }
			}
			
			if(msValue == null)
			{
				msValue = runtimeValueFactory.createMSValue();
                msValue.setSort(TermsFactory.eINSTANCE.createMultiSetSort());
			}
			
			runtimeState.addValue(hlPlace, msValue);
		}
        
        Map<IDWrapper, List<FiringMode>> firingModes = computeFiringModes(runtimeState.getValues(),
    			flatAccess.getTransitions(), transitionManager);
        
        for(IDWrapper transition : firingModes.keySet())
        {
        	runtimeState.addFiringModes((Transition)transition.getId(), firingModes.get(transition));
        }
        return runtimeState;
    }
    
	private static Map<IDWrapper, List<FiringMode>> computeFiringModes(Map<IDWrapper, IMSValue> runtimeValues,
			List<org.pnml.tools.epnk.pnmlcoremodel.Transition> transitions,
			TransitionManager transitionManager)
	{
		Map<IDWrapper, List<FiringMode>> modes = new HashMap<IDWrapper, List<FiringMode>>();
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : transitions)
		{
			Transition hlTransition = (Transition)transition;
			List<FiringMode> assignments = null;
            try
            {
            	assignments = transitionManager.checkTransition(hlTransition, 
            			runtimeValues);
            }
            catch(Exception e)
            {
	            e.printStackTrace();
            }
            finally
            {
            	if(assignments != null && assignments.size() > 0)
            	{
            		modes.put(new IDWrapper(hlTransition), assignments);
            	}
            }
		}
		return modes;
	}
	
	private static Map<IDWrapper, IMSValue> createNextMarking(EvaluationManager evalManager,
			Map<IDWrapper, IMSValue> oldValuesMap, FiringMode firingMode, FlatAccess flatAccess,
			RuntimeValueFactory runtimeValueFactory)
	{
		Set<IDWrapper> oldPlaces = new HashSet<IDWrapper>(oldValuesMap.keySet());
		Map<IDWrapper, IMSValue> newValuesMap = new HashMap<IDWrapper, IMSValue>();
		
		// updating incoming places
		for(IDWrapper placeId : firingMode.getValues().keySet())
		{
			oldPlaces.remove(placeId);
			newValuesMap.put(placeId, 
					AbstractValueMath.subtract(oldValuesMap.get(placeId), 
							firingMode.getValues().get(placeId), runtimeValueFactory));
		}
		
		// updates outgoing places
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : firingMode.getTransition().getOut())
		{
			Place place = (Place)flatAccess.resolve((PlaceNode)arc.getTarget());
			if(place != null)
			{
				IDWrapper placeId = new IDWrapper(place);
				
				IMSValue currentMarking = null;
				
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

				IMSValue newMarking = null;
				
				// one of the outgoing arc has no inscription
				Arc hlArc = (Arc)arc;
				if(hlArc.getHlinscription() != null && hlArc.getHlinscription().getStructure() != null)
				{
					try
	                {
		                IValue inscriptionValue = evalManager.
		                		evaluate(hlArc.getHlinscription().getStructure(), firingMode.getParams());
		                
		                newMarking = AbstractValueMath.append(currentMarking,
		                		(IMSValue)inscriptionValue, runtimeValueFactory);
	                }
	                catch(UnknownVariableException e)
	                {
		                e.printStackTrace();
	                }	
				}
				else
				{
					newMarking = AbstractValueMath.lightCopy(currentMarking, runtimeValueFactory);
				}
				newValuesMap.put(placeId, newMarking);	
			}
		}
		
		for(IDWrapper key : oldValuesMap.keySet())
		{
			if(!newValuesMap.containsKey(key))
			{
				newValuesMap.put(key, oldValuesMap.get(key));	
			}
		}
		
		return newValuesMap;
	}
}
