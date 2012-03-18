package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeVariable;

public class VariableEvaluation
{
	private MSValue multiset = null;
	
	private RuntimeVariable variable = null;
	
	// an actual assignment, the a set of multiset terms where the assignments belong to
	private Map<AbstractValue, Set<AbstractValue>> assignmnets = 
			new HashMap<AbstractValue, Set<AbstractValue>>();
	
	public RuntimeVariable getVariable()
    {
    	return variable;
    }
	public void setVariable(RuntimeVariable variable)
    {
    	this.variable = variable;
    }
	
	public MSValue getMultiset()
    {
    	return multiset;
    }
	public void setMultiset(MSValue multiset)
    {
    	this.multiset = multiset;
    }
	
	public void registerAssignment(AbstractValue key)
	{
		registerAssignment(key, new HashSet<AbstractValue>());
	}
	public void registerAssignment(Map<AbstractValue, Set<AbstractValue>> values)
	{
		for(AbstractValue key : values.keySet())
		{
			registerAssignment(key, values.get(key));
		}
	}
	public void registerAssignment(AbstractValue key, Set<AbstractValue> value)
	{
		if(assignmnets.containsKey(key))
		{
			Set<AbstractValue> values = assignmnets.get(key);
			values.addAll(value);
		}
		else
		{
			assignmnets.put(key, value);
		}
	}
	public Map<AbstractValue, Set<AbstractValue>> getAssignmnets()
    {
    	return assignmnets;
    }
}
