package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.HashSet;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;

public class TermAssignment
{
	private TermWrapper termWrapper = null;
	private Set<IValue> values = null;
	
	public TermWrapper getTermWrapper()
    {
    	return termWrapper;
    }
	public void setTermWrapper(TermWrapper variable)
    {
    	this.termWrapper = variable;
    }
	public Set<IValue> getValues()
    {
		if(values == null)
		{
			values = new HashSet<IValue>();
		}
    	return values;
    }
	public void setValues(Set<IValue> values)
    {
    	this.values = values;
    }
	@Override
    public String toString()
    {
		StringBuffer buffer = new StringBuffer("[");
		for(IValue value : values)
		{
			buffer.append(value + ";");
		}
	    return buffer.toString().replaceAll(";$", "") + "]";
    }
}
