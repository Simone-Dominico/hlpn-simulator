package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.HashSet;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;

public class VariableEvaluation
{
	private AbstractVariable variable = null;
	private Set<AbstractValue> values = null;
	
	public AbstractVariable getVariable()
    {
    	return variable;
    }
	public void setVariable(AbstractVariable variable)
    {
    	this.variable = variable;
    }
	public Set<AbstractValue> getValues()
    {
		if(values == null)
		{
			values = new HashSet<AbstractValue>();
		}
    	return values;
    }
	public void setValues(Set<AbstractValue> values)
    {
    	this.values = values;
    }
	public String getVariableName()
    {
    	return variable.getName();
    }

	@Override
    public String toString()
    {
		StringBuffer buffer = new StringBuffer("[");
		for(AbstractValue value : values)
		{
			buffer.append(value + ";");
		}
	    return buffer.toString().replaceAll(";$", "") + "]";
    }
}
