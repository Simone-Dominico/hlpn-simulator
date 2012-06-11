package org.pnml.tools.epnk.applications.hlpng.runtime.operations;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ListValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersFactory;

public class AbstractValueMath
{
	public static ListValue concat(ListValue l1, ListValue l2)
	{
		ListValue listValue = new ListValue();
		
		listValue.getElements().addAll(l1.getElements());
		listValue.getElements().addAll(l2.getElements());
		listValue.setSort(l1.getSort());
		
		return listValue;
	}
	
	public static ListValue append(ListValue l, AbstractValue v)
	{
		ListValue listValue = new ListValue();
		
		listValue.getElements().addAll(l.getElements());
		listValue.getElements().add(v);
		listValue.setSort(l.getSort());
		
		return listValue;
	}
	
	public static NumberValue length(ListValue l)
	{
		NumberValue nv = new NumberValue();
		nv.setN(l.getElements().size());
		nv.setSort(IntegersFactory.eINSTANCE.createNatural());
		
		return nv;
	}
	
	public static ListValue sublist(ListValue l, int start, int length)
	{
		ListValue listValue = new ListValue();
		
		listValue.setSort(l.getSort());
		listValue.getElements().addAll(l.getElements().subList(start, 
				start + length));
		
		return listValue;
	}
	
	public static AbstractValue at(ListValue l, int index)
	{
		return l.getElements().get(index);
	}
	
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
    
    public static MSValue subtract(MSValue msSet1, MSValue msSet2)
    {
    	MSValue newMsSet = lightCopy(msSet1);
    	for(AbstractValue key : msSet2.getValues().keySet())
    	{
    		Integer value = msSet2.getValues().get(key);
    		newMsSet = subtract(newMsSet, key, value);
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
    
    // msSet1 <= msSet2
    public static boolean lessEqual(MSValue msSet1, MSValue msSet2)
    {    	
        for(AbstractValue key : msSet1.getValues().keySet())
        {
        	Integer value = msSet1.getValues().get(key);
        	
        	if(!msSet2.getValues().containsKey(key) || value > msSet2.getValues().get(key))
        	{
        		return false;
        	}
        }
        
        return true;
    }
}
