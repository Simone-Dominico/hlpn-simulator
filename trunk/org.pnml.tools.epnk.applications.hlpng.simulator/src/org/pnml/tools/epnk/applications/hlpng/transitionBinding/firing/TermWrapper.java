package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class TermWrapper implements ITermWrapper
{
	protected Term rootTerm = null;
	
	final protected List<ITermWrapper> subterms = new ArrayList<ITermWrapper>();
	
	@Override
	public Term getRootTerm()
    {
    	return rootTerm;
    }
	public void setRootTerm(Term rootTerm)
    {
    	this.rootTerm = rootTerm;
    }
	public String getName()
    {
    	return rootTerm.toString();
    }
	@Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result
	            + ((rootTerm == null) ? 0 : rootTerm.hashCode());
	    return result;
    }
	@Override
    public boolean equals(Object obj)
    {
	    if(this == obj)
	    {
		    return true;
	    }
	    if(obj == null)
	    {
		    return false;
	    }
	    if(!(obj instanceof TermWrapper))
	    {
		    return false;
	    }
	    TermWrapper other = (TermWrapper) obj;
	    if(rootTerm == null)
	    {
		    if(other.rootTerm != null)
		    {
			    return false;
		    }
	    }
	    else if(!rootTerm.equals(other.rootTerm))
	    {
		    return false;
	    }
	    return true;
    }
	@Override
	public List<ITermWrapper> getSubterms()
    {
    	return subterms;
    }
	@Override
    public String toString()
    {
	    return Arrays.deepToString(subterms.toArray());
    }
}
