package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;

public class FiringMode
{
	// place ID <=> difference between runtime value and actual inscription value
	private Map<String, MSValue> values = new HashMap<String, MSValue>();
	// variable name <=> variable assignment
	private Map<String, AbstractValue> params = null;

	public Map<String, AbstractValue> getParams()
    {
    	return params;
    }
	public void setParams(Map<String, AbstractValue> params)
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
		for(String key : params.keySet())
		{
			buffer.append(key + "=" + params.get(key) + ";");
		}

	    return buffer.toString().replaceAll("\\s*;$", "") + "]";
    }
}
