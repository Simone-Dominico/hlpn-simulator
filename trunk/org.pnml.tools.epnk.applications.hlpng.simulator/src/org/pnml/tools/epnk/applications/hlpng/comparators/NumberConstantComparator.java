package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeVariable;

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
	    		((NumberValue)refValue).getN() == ((NumberValue)testValue).getN())
	    {
	    	return true;
	    }
	    return false;
    }
}
