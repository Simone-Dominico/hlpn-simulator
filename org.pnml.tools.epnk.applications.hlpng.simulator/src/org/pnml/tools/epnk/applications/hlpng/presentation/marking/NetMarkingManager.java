package org.pnml.tools.epnk.applications.hlpng.presentation.marking;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IDWrapper;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.RefPlace;
import org.pnml.tools.epnk.pnmlcoremodel.RefTransition;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

public class NetMarkingManager
{
	private PetriNet petrinet = null;
	private FlatAccess flatAccess = null;
	
	public NetMarkingManager(PetriNet petrinet, FlatAccess flatAccess)
	{
		this.petrinet = petrinet;
		this.flatAccess = flatAccess;
	}

    public NetMarking createNetMarking(IRuntimeState state)
    {       
        NetMarking netMarking = new NetMarking();
        netMarking.setNet(petrinet);
        
        // creates a marking for place runtime marking
        for(IDWrapper wrapper : state.getPlaces())
        {
        	// for each respective reference place
            final List<RefPlace> refPlaces = flatAccess.getRefPlaces((Place)wrapper.getId());
            if(refPlaces != null)
            {
            	for(RefPlace rp : refPlaces)
                {
                	PlaceMarking marking  = new PlaceMarking();
                    marking.setMsValue(state.getValue(wrapper));
                    marking.setObject(rp);
                    
                    netMarking.getMarkings().add(marking);
                    netMarking.getObjectAnnotations().add(marking);
                }	
            }
            
            PlaceMarking marking  = new PlaceMarking();
            marking.setMsValue(state.getValue(wrapper));
            marking.setObject((Place)wrapper.getId());
            
            netMarking.getMarkings().add(marking);
            netMarking.getObjectAnnotations().add(marking);
        }
        
        // creates a marking for enabled transitions
        for(IDWrapper wrapper : state.getTransitions())
        {
        	final Transition transition = (Transition)wrapper.getId();
        	final boolean fired = state.getFiringMode() != null && 
        			state.getFiringMode().getTransition().equals(transition);
        	
        	// for each respective reference transition
            final List<RefTransition> refTransitions = flatAccess.getRefTransitions(transition);
            if(refTransitions != null)
            {
            	for(RefTransition rp : refTransitions)
                {
            		TransitionMarking marking = new TransitionMarking();
                    marking.getModes().addAll(state.getFiringModes(wrapper)); 
                    marking.setFired(fired);
                    marking.setObject(rp);
                    
                    netMarking.getMarkings().add(marking);
                    netMarking.getObjectAnnotations().add(marking);
                }	
            }
            
            TransitionMarking marking = new TransitionMarking();
            marking.getModes().addAll(state.getFiringModes(wrapper)); 
            marking.setFired(fired);
            marking.setObject(transition);
            
            netMarking.getMarkings().add(marking);
            netMarking.getObjectAnnotations().add(marking);
        }

        return netMarking;
    }
}
