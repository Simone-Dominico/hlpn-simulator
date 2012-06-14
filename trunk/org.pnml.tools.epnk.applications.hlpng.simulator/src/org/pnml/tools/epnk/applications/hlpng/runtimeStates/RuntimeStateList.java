package org.pnml.tools.epnk.applications.hlpng.runtimeStates;

import java.util.ArrayList;
import java.util.List;

public class RuntimeStateList implements IRuntimeStateContainer
{
	private List<IRuntimeState> stateList = new ArrayList<IRuntimeState>();
	private int currentIndex = 0;
	
	@Override
    public void add(IRuntimeState state)
    {
	    this.stateList.add(state);
    }

	@Override
    public IRuntimeState next()
    {
	    if(currentIndex + 1 < stateList.size())
	    {
	    	currentIndex++;
	    	return stateList.get(currentIndex);
	    }
	    return null;
    }

	@Override
    public IRuntimeState previous()
    {
		if(currentIndex - 1 >= 0)
	    {
	    	currentIndex--;
	    	return stateList.get(currentIndex);
	    }
	    return null;
    }

	@Override
	public IRuntimeState getCurrent()
    {
		if(currentIndex < stateList.size())
	    {
	    	return stateList.get(currentIndex);
	    }
		return null;
    }

	@Override
	public void setCurrent(IRuntimeState current)
    {
		int index = stateList.indexOf(current);
		if(index >= 0 && index < stateList.size())
		{
			currentIndex = index;
		}
    }
}
