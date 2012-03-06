package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map.Entry;

import runtime.AbstractValue;
import runtime.MSValue;

public class MultisetComparator implements IComparator
{
	@Override
    public boolean equals(ComparatorManager manager,
            AbstractValue value1, AbstractValue value2)
    {
		if(!(value1 instanceof MSValue || value2 instanceof MSValue) ||
	    		!value1.getSort().equals(value2.getSort()))
	    {
	    	return false;
	    }
	    
		MSValue v1 = (MSValue)value1;
		MSValue v2 = (MSValue)value2;

    	for(int i = 0; i < v1.getValues().size(); i++)
    	{
    		Entry<AbstractValue, Integer> e1 = v1.getValues().get(i);
    		
    		for(int j = 0; j < v2.getValues().size(); j++)
    		{
    			Entry<AbstractValue, Integer> e2 = v2.getValues().get(j);

        		if(!manager.getComparator(e1.getKey().getClass()).equals(manager, 
        				e1.getKey(), e2.getKey()))
        		{
        			return false;
        		}	
    		}
    	}
    	return true;
    }

}
