package org.pnml.tools.epnk.applications.hlpng.runtime;

import java.util.HashMap;
import java.util.Map;

public class MSValue extends AbstractValue
{
	Map<AbstractValue, Integer> values = new HashMap<AbstractValue, Integer>();

	public Map<AbstractValue, Integer> getValues()
    {
    	return values;
    }

	public void setValues(Map<AbstractValue, Integer> values)
    {
    	this.values = values;
    }
	
	@Override
    public String toString()
    {
    	StringBuffer buffer = new StringBuffer();
    	
    	for(AbstractValue value : values.keySet())
    	{
    		buffer.append(values.get(value));
    		buffer.append("`");
    		
    		if(value instanceof MSValue)
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
	    if(!(obj instanceof MSValue))
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
