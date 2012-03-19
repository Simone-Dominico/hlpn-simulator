package org.pnml.tools.epnk.applications.hlpng.runtime.operations;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;

public class AbstractValueMath
{
    public static String toString(MSValue set)
    {
    	StringBuffer buffer = new StringBuffer();
    	
    	for(AbstractValue value : set.getValues().keySet())
    	{
    		Integer n = getMultiplicity(set, value);
    		
    		if(n != null)
    		{
    			buffer.append(n);
        		
    		}
    		else
    		{
    			buffer.append(set.getValues().get(value));
    		}
    		
    		buffer.append("`");
    		
    		if(value instanceof MSValue)
    		{
    			buffer.append("(" + toString((MSValue)value) + ")");
    		}
    		else
    		{
    			buffer.append(value.toString());    			
    		}

    		buffer.append("++");
    	}
    	
	    return buffer.toString().replaceAll("(.*)\\s*\\+\\+\\s*$", "$1");
    }
	
	public static Integer getMultiplicity(MSValue msSet, AbstractValue value)
	{
		return msSet.getValues().get(value);
	}
	
    public static MSValue add(MSValue msSet, AbstractValue value, Integer multiplicity)
    {
    	MSValue mainSet = lightCopy(msSet);
    	
    	if(mainSet.getValues().containsKey(value))
        {
    		Integer newMultiplicity = mainSet.getValues().get(value) + multiplicity;
    		mainSet.getValues().put(value, newMultiplicity);
        }
    	else
    	{
    		mainSet.getValues().put(value, multiplicity);
    	}
    	
    	return mainSet;
    }
    
    public static MSValue subtract(MSValue msSet, AbstractValue value, int multiplicity)
    {
    	MSValue newMsSet = add(msSet, value, multiplicity * -1);
    	
    	Integer count = getMultiplicity(newMsSet, value);
    	
    	if(count == null || count == 0)
    	{
    		newMsSet.getValues().remove(value);
    	}
    	
    	return newMsSet;
    }
    
    public static MSValue append(MSValue msSet1, MSValue msSet2)
    {    	
    	MSValue msSet = new MSValue();
    	msSet.setSort(msSet1.getSort());
    	
        for(AbstractValue key : msSet1.getValues().keySet())
        {
        	msSet = AbstractValueMath.add(msSet, key, msSet1.getValues().get(key));
        }
        
        for(AbstractValue key : msSet2.getValues().keySet())
        {
        	msSet = AbstractValueMath.add(msSet, key, msSet2.getValues().get(key));
        }
        
        return msSet;
    }
    
    public static MSValue lightCopy(MSValue initial)
    {
    	MSValue msSet = new MSValue();
    	msSet.setSort(initial.getSort());

    	msSet.getValues().putAll(initial.getValues());
    	
    	return msSet;
    }
}
