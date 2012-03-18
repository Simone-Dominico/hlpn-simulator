package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;

public class NumberConstantComparator implements IComparator
{
	@Override
	public boolean compare(ComparatorManager manager,
            AbstractValue refValue, AbstractValue testValue,
            Map<String, VariableEvaluation> assignments)
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
