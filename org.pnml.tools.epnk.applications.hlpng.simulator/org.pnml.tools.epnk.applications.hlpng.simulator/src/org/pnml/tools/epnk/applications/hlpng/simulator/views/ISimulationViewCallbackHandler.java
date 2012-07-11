package org.pnml.tools.epnk.applications.hlpng.simulator.views;

public interface ISimulationViewCallbackHandler
{
	public void clearFromView();
	public void itemSelected(Object data);
	public void speedChanged(int speed);
}