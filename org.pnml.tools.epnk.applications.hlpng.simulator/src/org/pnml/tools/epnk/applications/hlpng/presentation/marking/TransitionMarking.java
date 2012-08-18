package org.pnml.tools.epnk.applications.hlpng.presentation.marking;

import java.util.ArrayList;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public class TransitionMarking extends AbstractMarking
{
	private List<FiringMode> modes = new ArrayList<FiringMode>();
	private boolean fired = false;
	
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
