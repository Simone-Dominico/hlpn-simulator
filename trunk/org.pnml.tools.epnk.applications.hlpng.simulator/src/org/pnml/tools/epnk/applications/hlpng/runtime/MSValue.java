package org.pnml.tools.epnk.applications.hlpng.runtime;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MSValue extends AbstractValue implements IMSValue
{
	private Map<IValue, Integer> values = new HashMap<IValue, Integer>();

	@Override
    public Collection<Entry<IValue, Integer>> entrySet()
    {
	    return values.entrySet();
    }

	@Override
    public int size()
    {
	    return values.size();
    }
	
	@Override
    public boolean contains(IValue value)
    {
	    return values.containsKey(value);
    }
	
	@Override
    public Integer get(IValue value)
    {
	    return values.get(value);
    }
	
	@Override
    public void put(IValue value, Integer multiplicity)
    {
	    values.put(value, multiplicity);
    }
	
	@Override
    public void remove(IValue value)
    {
	    values.remove(value);
    }
	
	@Override
    public void putAll(Collection<Entry<IValue, Integer>> entrySet)
    {
	    for(Entry<IValue, Integer> entry : entrySet)
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
    	
    	for(IValue value : values.keySet())
    	{
    		buffer.append(values.get(value));
    		buffer.append("`");
    		
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
