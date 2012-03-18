package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.ArrayList;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeVariable;

public class VariableEvaluation
{
	private RuntimeVariable variable = null;
	private List<AbstractValue> values = new ArrayList<AbstractValue>();
	
	public RuntimeVariable getVariable()
    {
    	return variable;
    }
	public void setVariable(RuntimeVariable variable)
    {
    	this.variable = variable;
    }
	public List<AbstractValue> getValues()
    {
    	return values;
    }
}
