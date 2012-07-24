package org.pnml.tools.epnk.applications.hlpng.runtime;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;

public class MSValue extends AbstractValue implements IMSValue
{
	private Map<ITermWrapper, Integer> values = new HashMap<ITermWrapper, Integer>();
	
	MSValue(){}

	@Override
    public Collection<Entry<ITermWrapper, Integer>> entrySet()
    {
	    return values.entrySet();
    }

	@Override
    public int size()
    {
	    return values.size();
    }
	
	@Override
    public boolean contains(ITermWrapper value)
    {
	    return values.containsKey(value);
    }
	
	@Override
    public Integer get(ITermWrapper value)
    {
	    return values.get(value);
    }
	
	@Override
    public void put(ITermWrapper value, Integer multiplicity)
    {
	    values.put(value, multiplicity);
    }
	
	@Override
    public void remove(ITermWrapper value)
    {
	    values.remove(value);
    }
	
	@Override
    public void putAll(Collection<Entry<ITermWrapper, Integer>> entrySet)
    {
	    for(Entry<ITermWrapper, Integer> entry : entrySet)
	    {
	    	values.put(entry.getKey(), entry.getValue());
	    }
    }
	
	@Override
    public void clear()
    {
	    values.clear();
    }
	
	@Override
    public String toString()
    {
    	StringBuffer buffer = new StringBuffer();
    	
    	for(Entry<ITermWrapper, Integer> entry : entrySet())
    	{
    		buffer.append(entry.getValue());
    		buffer.append("`");
    		
    		ITermWrapper value = entry.getKey();
    		if(value instanceof IMSValue)
    		{
    			buffer.append("(" + value.toString() + ")");
    		}
    		else
    		{
    			buffer.append(value.toString());    			
    		}

    		buffer.append("++\n");
    	}
    	
	    return buffer.toString().replaceAll("(.*)\\s*\\+\\+\\s*$", "$1");
    }

	@Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((values == null) ? 0 : values.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj)
    {
	    if(this == obj)
	    {
		    return true;
	    }
	    if(obj == null)
	    {
		    return false;
	    }
	    if(!(obj instanceof IMSValue))
	    {
		    return false;
	    }
	    MSValue other = (MSValue) obj;
	    if(values == null)
	    {
		    if(other.values != null)
		    {
			    return false;
		    }
	    }
	    else if(!values.equals(other.values))
	    {
		    return false;
	    }
	    return true;
    }
}
