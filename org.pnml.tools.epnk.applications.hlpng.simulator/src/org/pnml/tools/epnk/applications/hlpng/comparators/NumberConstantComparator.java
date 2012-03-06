package org.pnml.tools.epnk.applications.hlpng.comparators;

import numberRuntime.NumberValue;

import runtime.AbstractValue;

public class NumberConstantComparator implements IComparator
{
	@Override
    public boolean equals(ComparatorManager manager,
            AbstractValue value1, AbstractValue value2)
    {
	    if(value1 instanceof NumberValue && value2 instanceof NumberValue &&
	    		value1.getSort().equals(value2.getSort()) && 
	    		((NumberValue)value1).getN() == ((NumberValue)value2).getN())
	    {
	    	return true;
	    }
	    return false;
    }
}
