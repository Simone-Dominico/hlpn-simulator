package org.pnml.tools.epnk.applications.hlpng.comparators;

import productRuntime.ProductValue;

import runtime.AbstractValue;

public class ProductComparator implements IComparator
{
	@Override
    public boolean compare(ComparatorManager manager,
            AbstractValue value1, AbstractValue value2)
    {
	    if(!(value1 instanceof ProductValue || value2 instanceof ProductValue) ||
	    		!value1.getSort().equals(value2.getSort()))
	    {
	    	return false;
	    }
	    
    	ProductValue v1 = (ProductValue)value1;
    	ProductValue v2 = (ProductValue)value2;
    	
    	for(int i = 0; i < v1.getComponents().size(); i++)
    	{
    		AbstractValue c1 = v1.getComponents().get(i);
    		AbstractValue c2 = v2.getComponents().get(i);
    		
    		if(!manager.getComparator(c1.getClass()).compare(manager, c1, c2))
    		{
    			return false;
    		}
    	}
    	return true;
    }

}
