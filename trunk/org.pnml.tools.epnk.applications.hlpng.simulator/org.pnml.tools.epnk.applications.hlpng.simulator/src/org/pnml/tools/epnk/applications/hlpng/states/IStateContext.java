package org.pnml.tools.epnk.applications.hlpng.states;


public interface IStateContext
{
	public void setState(IState state);
	public IState getState();
	public void request();
}
