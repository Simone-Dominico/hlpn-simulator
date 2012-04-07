package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class RuntimeVariable extends AbstractVariable
{
	private Variable variable = null;

	public Variable getVariable()
    {
    	return variable;
    }

	public void setVariable(Variable variable)
    {
    	this.variable = variable;
    }

	@Override
    public String toString()
    {
	    return getVariable().getName();
    }

	@Override
    public String getName()
    {
	    return toString();
    }

	@Override
    public Term getRootTerm()
    {
	    return variable;
    }
	
}
