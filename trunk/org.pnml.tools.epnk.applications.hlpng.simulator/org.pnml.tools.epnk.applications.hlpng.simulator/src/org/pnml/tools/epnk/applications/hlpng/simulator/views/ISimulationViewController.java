package org.pnml.tools.epnk.applications.hlpng.simulator.views;

import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeStateContainer;

public interface ISimulationViewController
{
	public void resetRecords(final IRuntimeStateContainer runtimeStates);
	public void record(final IRuntimeState runtimeState);
	public void clear();
	public void setCurrent();
}