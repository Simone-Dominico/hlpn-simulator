package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NetMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.TransitionMarking;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IDWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

public class NetMarkingManager
{
	private PetriNet petrinet = null;
	
	public NetMarkingManager(PetriNet petrinet, ComparisonManager comparatorManager,
			ReversibleOperationManager reversibleOperationManager)
	{
		this.petrinet = petrinet;
	}
	
	public NetMarking createNetMarking(List<Pair<Place, MSValue>> runtimeValuesList,
			List<FiringMode> enabledTransitions)
	{		
		NetMarking netMarking = new NetMarking();
		netMarking.setNet(petrinet);
		
		// creates a marking for place runtime marking
		for(Pair<Place, MSValue> pair : runtimeValuesList)
		{
			PlaceMarking marking  = new PlaceMarking();
			marking.setPlace(pair.getKey());
			marking.setMsValue(pair.getValue());
			marking.setObject(pair.getKey());
			
			netMarking.getMarkings().add(marking);
			netMarking.getObjectAnnotations().add(marking);
		}
		
	    // creates a marking for enabled transitions
		for(TransitionMarking marking : getTransitionMarkings(enabledTransitions))
		{
			netMarking.getMarkings().add(marking);
			netMarking.getObjectAnnotations().add(marking);
		}

		return netMarking;
	}
	
	private static List<TransitionMarking> getTransitionMarkings(List<FiringMode> modes)
	{
		Map<Transition, List<FiringMode>> modeMap = new HashMap<Transition, List<FiringMode>>();
		for(FiringMode mode : modes)
		{
			Transition t = (Transition)mode.getTransition();
			if(modeMap.containsKey(t))
			{
				modeMap.get(t).add(mode);
			}
			else
			{
				List<FiringMode> list = new ArrayList<FiringMode>();
				list.add(mode);
				modeMap.put(t, list);
			}
		}
		
		List<TransitionMarking> transitionMarkings = new ArrayList<TransitionMarking>();
		
		for(Transition transition : modeMap.keySet())
		{
			TransitionMarking marking = new TransitionMarking();
    		marking.getModes().addAll(modeMap.get(transition));	
    		
    		marking.setTransition(transition);
			marking.setObject(transition);
			
			transitionMarkings.add(marking);
		}
		
		return transitionMarkings;
	}
	
	/* --------------------------------------------------------------------- */
    public NetMarking createNetMarking(IRuntimeState state)
    {       
        NetMarking netMarking = new NetMarking();
        netMarking.setNet(petrinet);
        
        // creates a marking for place runtime marking
        for(IDWrapper wrapper : state.getPlaces())
        {
            PlaceMarking marking  = new PlaceMarking();
            marking.setPlace((Place)wrapper.getId());
            marking.setMsValue((MSValue)state.getValue(wrapper));
            marking.setObject((Place)wrapper.getId());
            
            netMarking.getMarkings().add(marking);
            netMarking.getObjectAnnotations().add(marking);
        }
        
        // creates a marking for enabled transitions
        for(IDWrapper wrapper : state.getTransitions())
        {
            TransitionMarking marking = new TransitionMarking();
            marking.getModes().addAll(state.getFiringModes(wrapper));   
            
            marking.setTransition((Transition)wrapper.getId());
            marking.setObject((Transition)wrapper.getId());
            
            netMarking.getMarkings().add(marking);
            netMarking.getObjectAnnotations().add(marking);
        }

        return netMarking;
    }
}
