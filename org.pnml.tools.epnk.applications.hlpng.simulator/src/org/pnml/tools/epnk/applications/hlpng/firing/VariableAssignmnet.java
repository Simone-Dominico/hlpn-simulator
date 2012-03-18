package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeVariable;

public class VariableAssignmnet
{
	private AbstractValue parentValue = null;
	private Map<RuntimeVariable, AbstractValue> assignments = null;

	public AbstractValue getParentValue()
    {
    	return parentValue;
    }
	public void setParentValue(AbstractValue parentValue)
    {
    	this.parentValue = parentValue;
    }
	public Map<RuntimeVariable, AbstractValue> getAssignments()
    {
    	return assignments;
    }
	public void setAssignments(Map<RuntimeVariable, AbstractValue> assignments)
    {
    	this.assignments = assignments;
    }
}
