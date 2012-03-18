package org.pnml.tools.epnk.applications.hlpng.runtime;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class RuntimeVariable extends AbstractValue
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
	
}
