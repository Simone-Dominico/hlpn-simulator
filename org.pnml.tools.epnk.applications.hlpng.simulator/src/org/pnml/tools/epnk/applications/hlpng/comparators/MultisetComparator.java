package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeVariable;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;

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

    	for(AbstractValue k1 : v1.getValues().keySet())
    	{    		
    		for(AbstractValue k2 : v2.getValues().keySet())
    		{
    			Integer n1 = AbstractValueMath.calcMultiplicity(v1, k1);
    			Integer n2 = AbstractValueMath.calcMultiplicity(v2, k2);
    			
        		if(!n1.equals(n2) ||
        				!manager.getComparator(k1.getClass()).compare(manager, 
        				k1, k2, assignments))
        		{
        			return false;
        		}	
    		}
    	}
    	return true;
    }

}
