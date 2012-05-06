package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;


public class TermWrapper extends AbstractValue
{
	protected Term rootTerm = null;
	protected boolean resolved = true;

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

	public boolean isResolved()
    {
    	return resolved;
    }
	public void setResolved(boolean resolved)
    {
    	this.resolved = resolved;
    }
}
