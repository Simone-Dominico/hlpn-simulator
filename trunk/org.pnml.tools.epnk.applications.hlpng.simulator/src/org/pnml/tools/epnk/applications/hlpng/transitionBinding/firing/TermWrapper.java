package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;


public class TermWrapper extends AbstractValue
{
	protected Term rootTerm = null;

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
}
