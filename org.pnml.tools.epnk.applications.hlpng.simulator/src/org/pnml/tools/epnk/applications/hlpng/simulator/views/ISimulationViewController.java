package org.pnml.tools.epnk.applications.hlpng.simulator.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeStateContainer;
import org.pnml.tools.epnk.applications.hlpng.simulator.ISimulator;

public interface ISimulationViewController extends ISelectionChangedListener
{
	public void resetRecords(final IRuntimeStateContainer runtimeStates);
	public void record(final IRuntimeState runtimeState);
	public void clear();
	public void setCurrent();
	public Action[] getActions();
	public void setSimulator(ISimulator simulator);
	public void shutDown();
}