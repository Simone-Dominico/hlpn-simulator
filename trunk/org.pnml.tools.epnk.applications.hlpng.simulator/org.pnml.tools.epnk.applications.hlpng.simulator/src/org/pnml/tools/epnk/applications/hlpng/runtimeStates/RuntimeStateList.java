package org.pnml.tools.epnk.applications.hlpng.runtimeStates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuntimeStateList implements IRuntimeStateContainer
{
	private List<IRuntimeState> stateList = new ArrayList<IRuntimeState>();
	private IRuntimeState current = null;
	
	@Override
    public boolean add(IRuntimeState state)
    {
		boolean needToClean = false;
		
		// the current state does not match the last one in the list
	    if(stateList.contains(this.current) &&
	    		!this.current.equals(stateList.get(stateList.size() - 1)))
	    {
	    	int index = stateList.indexOf(this.current);
	    	int size = stateList.size();
	    	
	    	for(int i = index + 1; i < size; size--)
	    	{
	    		stateList.remove(i);
	    	}
	    	
	    	needToClean = true;
	    }
	    	
	    this.stateList.add(state);
	    this.current = state;
	    
	    return needToClean;
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

	@Override
    public Iterator<IRuntimeState> iterator()
    {
	    return stateList.iterator();
    }
}
