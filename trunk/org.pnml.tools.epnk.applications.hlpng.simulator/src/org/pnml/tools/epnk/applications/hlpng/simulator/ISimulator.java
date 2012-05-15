package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.List;

import org.pnml.tools.epnk.applications.IApplicationWithPresentation;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public interface ISimulator extends IApplicationWithPresentation
{
	public void auto();
	public void stop();
	public void next();
	public void init();
	public void reset();
	public void previous();
	public List<FiringMode> fire(FiringMode mode);
	public List<FiringMode> updateTransitionMarking();
}
