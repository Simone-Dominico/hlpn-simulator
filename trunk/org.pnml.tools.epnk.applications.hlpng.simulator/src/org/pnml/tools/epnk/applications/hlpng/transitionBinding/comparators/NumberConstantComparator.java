package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class NumberConstantComparator implements IComparable
{
	@Override
	public boolean compare(Term refValue, AbstractValue testValue,
            Map<String, TermAssignment> assignments)
    {
	    if(refValue instanceof NumberConstant && testValue instanceof NumberValue &&
	    		(refValue.getSort().equals(testValue.getSort()) ||
	    				refValue.getSort().isSuperSortOf(testValue.getSort())) && 
	    		((NumberConstant)refValue).getValue() == ((NumberValue)testValue).getN())
	    {
	    	return true;
	    }
	    return false;
    }
}
