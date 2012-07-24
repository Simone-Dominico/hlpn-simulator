package org.pnml.tools.epnk.applications.hlpng.presentation.marking;

import java.util.ArrayList;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

public class TransitionMarking extends AbstractMarking
{
	private Transition transition = null;
	private List<FiringMode> modes = new ArrayList<FiringMode>();
	private boolean fired = false;
	
	public Transition getTransition()
    {
    	return transition;
    }
	public void setTransition(Transition transition)
    {
    	this.transition = transition;
    }
	public List<FiringMode> getModes()
    {
    	return modes;
    }
	public void setModes(List<FiringMode> modes)
    {
    	this.modes = modes;
    }
	public boolean isFired()
    {
    	return fired;
    }
	public void setFired(boolean fired)
    {
    	this.fired = fired;
    }
}
