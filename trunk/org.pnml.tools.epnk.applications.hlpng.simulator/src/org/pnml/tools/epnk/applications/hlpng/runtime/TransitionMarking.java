package org.pnml.tools.epnk.applications.hlpng.runtime;

import java.util.ArrayList;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.firing.FiringMode;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

public class TransitionMarking extends AbstractMarking
{
	private Transition transition = null;
	private List<FiringMode> modes = new ArrayList<FiringMode>();
	
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
}
