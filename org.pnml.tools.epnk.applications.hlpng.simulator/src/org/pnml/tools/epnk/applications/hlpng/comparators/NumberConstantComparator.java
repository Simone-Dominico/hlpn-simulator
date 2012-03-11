package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;

import numberRuntime.NumberValue;

import runtime.AbstractValue;
import runtime.RuntimeVariable;

public class NumberConstantComparator implements IComparator
{
	@Override
	public boolean compare(ComparatorManager manager,
            AbstractValue refValue, AbstractValue testValue,
			Map<RuntimeVariable, AbstractValue> assignments)
    {
	    if(refValue instanceof NumberValue && testValue instanceof NumberValue &&
	    		(refValue.getSort().equals(testValue.getSort()) ||
	    				refValue.getSort().isSuperSortOf(testValue.getSort())) && 
	    		((NumberValue)refValue).getN() == ((NumberValue)refValue).getN())
	    {
	    	return true;
	    }
	    return false;
    }
}
