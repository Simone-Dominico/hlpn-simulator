package org.pnml.tools.epnk.applications.hlpng.simulator;

import org.pnml.tools.epnk.applications.hlpng.runtime.NetMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.TransitionMarking;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IDWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
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

    public NetMarking createNetMarking(IRuntimeState state)
    {       
        NetMarking netMarking = new NetMarking();
        netMarking.setNet(petrinet);
        
        // creates a marking for place runtime marking
        for(IDWrapper wrapper : state.getPlaces())
        {
            PlaceMarking marking  = new PlaceMarking();
            marking.setPlace((Place)wrapper.getId());
            marking.setMsValue(state.getValue(wrapper));
            marking.setObject((Place)wrapper.getId());
            
            netMarking.getMarkings().add(marking);
            netMarking.getObjectAnnotations().add(marking);
        }
        
        // creates a marking for enabled transitions
        for(IDWrapper wrapper : state.getTransitions())
        {

            TransitionMarking marking = new TransitionMarking();
            marking.getModes().addAll(state.getFiringModes(wrapper)); 
           
        	Transition transition = (Transition)wrapper.getId();
        	// marks as fired
        	if(state.getFiringMode() != null && 
        			state.getFiringMode().getTransition().getId().equals(transition.getId()))
        	{
        		marking.setFired(true);
        	}
            
            marking.setTransition(transition);
            marking.setObject(transition);
            
            netMarking.getMarkings().add(marking);
            netMarking.getObjectAnnotations().add(marking);
        }

        return netMarking;
    }
}
