package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

public class FiringMode
{
	// place ID <=> difference between runtime value and actual inscription value
	private Map<String, MSValue> values = new HashMap<String, MSValue>();
	// variable name <=> variable assignment
	private Map<TermWrapper, AbstractValue> params = null;
	
	private Transition transition = null;

	public Map<TermWrapper, AbstractValue> getParams()
    {
    	return params;
    }
	public void setParams(Map<TermWrapper, AbstractValue> params)
    {
    	this.params = params;
    }
	public Map<String, MSValue> getValues()
    {
    	return values;
    }
	public void setValues(Map<String, MSValue> values)
    {
    	this.values = values;
    }

	@Override
    public String toString()
    {
		if(params == null)
		{
			return null;
		}
		StringBuffer buffer = new StringBuffer("[");
		for(TermWrapper key : params.keySet())
		{
			buffer.append(key.getName() + "=" + params.get(key) + ";");
		}

	    return buffer.toString().replaceAll("\\s*;$", "") + "]";
    }
	public Transition getTransition()
    {
    	return transition;
    }
	public void setTransition(Transition transition)
    {
    	this.transition = transition;
    }
}
