package org.pnml.tools.epnk.applications.hlpng.runtime.operations;

import java.util.ArrayList;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersFactory;

public class AbstractValueMath
{
    public static String toString(MSValue set)
    {
    	StringBuffer buffer = new StringBuffer();
    	
    	for(AbstractValue value : set.getValues().keySet())
    	{
    		Integer n = calcMultiplicity(set, value);
    		
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
	
	public static List<AbstractValue> getMultiplicity(MSValue msSet, AbstractValue value)
	{
		if(msSet.getValues().containsKey(value))
        {
        	return msSet.getValues().get(value);
        }
        return null;
	}
	
	public static Integer calcMultiplicity(MSValue msSet, AbstractValue value)
	{
		if(msSet.getValues().containsKey(value))
        {
			List<AbstractValue> keys = msSet.getValues().get(value);
			int count = 0;
			for(AbstractValue key : keys)
			{
				if(key instanceof NumberValue)
				{
					count += ((NumberValue)key).getN();
				}
				else
				{
					return null;
				}
			}
        	return count;
        }
        return null;
	}
	
    public static MSValue add(MSValue msSet, AbstractValue value, List<AbstractValue> multiplicity)
    {
    	MSValue mainSet = lightCopy(msSet);
    	
    	if(mainSet.getValues().containsKey(value))
        {
    		List<AbstractValue> newMultiplicity = new ArrayList<AbstractValue>();
    		for(AbstractValue copyValue : mainSet.getValues().get(value))
			{
    			newMultiplicity.add(copyValue);
			}
    		
    		newMultiplicity.addAll(multiplicity);
    		
    		mainSet.getValues().put(value, newMultiplicity);
        }
    	else
    	{
    		mainSet.getValues().put(value, multiplicity);
    	}
    	
    	return mainSet;
    }
    
    public static MSValue add(MSValue msSet, AbstractValue value, AbstractValue multiplicity)
    {
    	List<AbstractValue> newMultiplicity = new ArrayList<AbstractValue>();
		newMultiplicity.add(multiplicity);

    	return add(msSet, value, newMultiplicity);
    }
    
    public static MSValue add(MSValue msSet, AbstractValue value, int multiplicity)
    {
    	PosValue newMultiplicity = new PosValue();
    	newMultiplicity.setN(multiplicity);
    	newMultiplicity.setSort(IntegersFactory.eINSTANCE.createPositive());
    	
    	return add(msSet, value, newMultiplicity);
    }
    
    public static MSValue subtract(MSValue msSet, AbstractValue value, int multiplicity)
    {
    	MSValue newMsSet = add(msSet, value, multiplicity * -1);
    	
    	Integer count = calcMultiplicity(newMsSet, value);
    	
    	if(count != null && count == 0)
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
