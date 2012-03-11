package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;

import productRuntime.ProductValue;

import runtime.AbstractValue;
import runtime.RuntimeVariable;

public class ProductComparator implements IComparator
{
	@Override
	public boolean compare(ComparatorManager manager,
            AbstractValue refValue, AbstractValue testValue,
			Map<RuntimeVariable, AbstractValue> assignments)
    {
	    if(!(refValue instanceof ProductValue || testValue instanceof ProductValue) ||
	    		!(refValue.getSort().equals(testValue.getSort()) ||
	    				refValue.getSort().isSuperSortOf(testValue.getSort())))
	    {
	    	return false;
	    }
	    
    	ProductValue v1 = (ProductValue)refValue;
    	ProductValue v2 = (ProductValue)testValue;
    	
    	for(int i = 0; i < v1.getComponents().size(); i++)
    	{
    		AbstractValue c1 = v1.getComponents().get(i);
    		AbstractValue c2 = v2.getComponents().get(i);
    		
    		if(!manager.getComparator(c1.getClass()).compare(manager, c1, c2, assignments))
    		{
    			return false;
    		}
    	}
    	return true;
    }

}
