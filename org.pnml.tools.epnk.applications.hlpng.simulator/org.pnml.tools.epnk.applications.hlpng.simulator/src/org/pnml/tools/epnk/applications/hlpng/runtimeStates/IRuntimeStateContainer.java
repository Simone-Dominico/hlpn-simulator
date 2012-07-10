package org.pnml.tools.epnk.applications.hlpng.runtimeStates;

public interface IRuntimeStateContainer extends Iterable<IRuntimeState>
{
	public boolean add(IRuntimeState state);
	public IRuntimeState next();
	public IRuntimeState previous();
	public IRuntimeState getCurrent();
	public void setCurrent(IRuntimeState current);
}
