package org.pnml.tools.epnk.applications.hlpng.simulator;

import org.pnml.tools.epnk.applications.IApplicationWithPresentation;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public interface ISimulator extends IApplicationWithPresentation
{
	public void auto();
	public void stop();
	public void next();
	public void init();
	public void reset();
	public void previous();
	public void show(IRuntimeState state);
	public void fire(FiringMode mode, boolean updateAnnotations);
	public void updateTransitionBinding(IRuntimeState state);
	public boolean isAutoModeEnabled();
	public void setActive();
	public void setSimulationPause(long simulationPause);
}
