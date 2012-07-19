package org.pnml.tools.epnk.applications.hlpng.runtimeStates;

import java.util.Iterator;

public class RuntimeStateList implements IRuntimeStateContainer, 
		Iterator<IRuntimeState>
{
	private RuntimeState root = null;
	private RuntimeState iterCurr = null;
	
	private RuntimeState current = null;
	
	@Override
    public boolean add(IRuntimeState state)
    {
		RuntimeState rs = (RuntimeState)state;
		
		if(root == null)
		{
			root = rs;
			current = root;
			return false;
		}
		
		boolean neadToClean = false;
		
		// the current state is not the last one in the list
		if(current.getNext() != null)
		{
			neadToClean = true;
		}
		
		current.setNext(rs);
		rs.setPrevious(current);
		current = rs;
		
		return neadToClean;
    }
	
	@Override
    public IRuntimeState relativeNext()
    {
		if(current.getNext() != null)
		{
			current = current.getNext();
			return current;
		}
		
		return null;
    }

	@Override
    public IRuntimeState relativePrevious()
    {
		if(current.getPrevious() != null)
		{
			current = current.getPrevious();
			return current;
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
		RuntimeState rs = (RuntimeState)current;
		if(rs.getPrevious() == null && rs.getNext() == null)
		{
			throw new RuntimeException("The state currently is not in the state list." +
					" First you need to add() it!");
		}
		
		this.current = (RuntimeState)current;
    }

	/*
	 *  iterator/iterable methods(non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
    public Iterator<IRuntimeState> iterator()
    {
		iterCurr = root;
		
	    return this;
    }

	@Override
    public boolean hasNext()
    {
		if(iterCurr.getNext() != null)
		{
			return true;
		}
		
	    return false;
    }

	@Override
    public IRuntimeState next()
    {
	    RuntimeState s = iterCurr;
	    
	    iterCurr = iterCurr.getNext();
	    
	    return s;
    }

	@Override
    public void remove()
    {
	    throw new UnsupportedOperationException();
    }
}
