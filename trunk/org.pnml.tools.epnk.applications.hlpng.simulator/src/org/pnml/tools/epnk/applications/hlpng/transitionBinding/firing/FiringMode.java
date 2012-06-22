package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

public class FiringMode
{
	// place ID <=> actual inscription value
	private Map<IDWrapper, IMSValue> values = new HashMap<IDWrapper, IMSValue>();
	// variable name <=> variable assignment
	private Map<TermWrapper, IValue> params = null;
	
	private Transition transition = null;

	public Map<TermWrapper, IValue> getParams()
    {
    	return params;
    }
	public void setParams(Map<TermWrapper, IValue> params)
    {
    	this.params = params;
    }
	public Map<IDWrapper, IMSValue> getValues()
    {
    	return values;
    }
	public void setValues(Map<IDWrapper, IMSValue> values)
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
