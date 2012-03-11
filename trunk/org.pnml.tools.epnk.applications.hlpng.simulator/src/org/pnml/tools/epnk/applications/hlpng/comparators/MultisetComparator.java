package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;
import java.util.Map.Entry;

import runtime.AbstractValue;
import runtime.MSValue;
import runtime.RuntimeVariable;

public class MultisetComparator implements IComparator
{
	@Override
    public boolean compare(ComparatorManager manager,
            AbstractValue refValue, AbstractValue testValue,
			Map<RuntimeVariable, AbstractValue> assignments)
    {
		if(!(refValue instanceof MSValue || testValue instanceof MSValue) ||
	    		!(refValue.getSort().isSuperSortOf(testValue.getSort()) ||
	    				refValue.getSort().equals(testValue.getSort())))
	    {
	    	return false;
	    }
	    
		MSValue v1 = (MSValue)refValue;
		MSValue v2 = (MSValue)testValue;

    	for(Entry<AbstractValue, Integer> e1 : v1.getValues())
    	{    		
    		for(Entry<AbstractValue, Integer> e2 : v2.getValues())
    		{
        		if(e1.getValue() != e2.getValue() ||
        				!manager.getComparator(e1.getKey().getClass()).compare(manager, 
        				e1.getKey(), e2.getKey(), assignments))
        		{
        			return false;
        		}	
    		}
    	}
    	return true;
    }

}
