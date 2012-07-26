package org.pnml.tools.epnk.applications.hlpng.runtimeStates;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
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

public class RuntimeStateManager
{
	private final FlatAccess flatAccess;
	private final RuntimeValueFactory runtimeValueFactory;
	private final TransitionManager transitionManager;
	private final EvaluationManager evaluationManager;
	
	private final IRuntimeStateContainer stateContainer;
	
	public RuntimeStateManager(final FlatAccess flatAccess, 
			final RuntimeValueFactory runtimeValueFactory,
			final TransitionManager transitionManager,
			final EvaluationManager evaluationManager)
	{
		this.flatAccess = flatAccess;
		this.runtimeValueFactory = runtimeValueFactory;
		this.transitionManager = transitionManager;
		this.evaluationManager = evaluationManager;
		
		this.stateContainer = new RuntimeStateList();
	}
	
    public void updateState(IRuntimeState state)
    {
    	state.getModes().clear();
    	
    	Map<IDWrapper, List<FiringMode>> firingModes = computeFiringModes(state.getValues(),
    			flatAccess.getTransitions(), transitionManager);
        
        for(IDWrapper transition : firingModes.keySet())
        {
        	state.addFiringModes((Transition)transition.getId(), firingModes.get(transition));
        }
    }
	
    public IRuntimeState createNextState(IRuntimeState prevState, FiringMode firingMode)
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
    
    public IRuntimeState createInitialState(
            List<org.pnml.tools.epnk.pnmlcoremodel.Transition> transitions)
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
                	msValue = (IMSValue)evaluationManager.evaluate(term, null);
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
    
    public IRuntimeState getCurrentState()
    {
    	return this.stateContainer.getCurrent();
    }
    
    public boolean addState(IRuntimeState state)
    {
    	return this.stateContainer.add(state);
    }
    
    public void setCurrentState(IRuntimeState state)
    {
    	this.stateContainer.setCurrent(state);
    }
    
	public IRuntimeStateContainer getStateContainer()
    {
    	return stateContainer;
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
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : flatAccess.getOut(firingMode.getTransition()))
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
