package org.pnml.tools.epnk.applications.hlpng.runtimeStates;

import java.util.ArrayList;
import java.util.List;

public class RuntimeStateList implements IRuntimeStateContainer
{
	private List<IRuntimeState> stateList = new ArrayList<IRuntimeState>();
	private IRuntimeState current = null;
	
	@Override
    public void add(IRuntimeState state)
    {
	    this.stateList.add(state);
	    this.current = state;
    }

	@Override
    public IRuntimeState next()
    {
		int index = this.stateList.indexOf(current) + 1;
	    if(index >= 0 && index < stateList.size())
	    {
	    	current = stateList.get(index);
	    	return getCurrent();
	    }
	    return null;
    }

	@Override
    public IRuntimeState previous()
    {
		int index = this.stateList.indexOf(current) - 1;
		if(index >= 0 && index < stateList.size())
	    {
	    	current = stateList.get(index);
	    	return getCurrent();
	    }
	    return null;
    }

	@Override
	public IRuntimeState getCurrent()
    {
		return current;
    }

	@Override
	public void setCurrent(IRuntimeState current)
    {
		this.current = current;
    }
}
