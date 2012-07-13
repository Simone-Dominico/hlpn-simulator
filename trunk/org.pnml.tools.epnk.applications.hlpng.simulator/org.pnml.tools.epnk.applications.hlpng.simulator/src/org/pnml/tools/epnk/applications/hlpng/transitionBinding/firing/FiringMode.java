package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
		
		List<TermWrapper> keys = new ArrayList<TermWrapper>(params.keySet());
		Collections.sort(keys, new Comparator<TermWrapper>()
		{
			@Override
			public int compare(TermWrapper o1, TermWrapper o2) 
			{
		        return o1.getName().compareTo(o2.getName());
		    }
		});
		
		StringBuffer buffer = new StringBuffer("[");
		for(TermWrapper key : keys)
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
