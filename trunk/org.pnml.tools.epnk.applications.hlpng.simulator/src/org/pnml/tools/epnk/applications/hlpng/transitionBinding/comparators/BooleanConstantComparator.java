package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.VariableEvaluation;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleanConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class BooleanConstantComparator implements IComparable
{
	@Override
	public boolean compare(Term refValue, AbstractValue testValue,
            Map<String, VariableEvaluation> assignments)
    {
	    if(refValue instanceof BooleanConstant && testValue instanceof BooleanValue &&
	    		(refValue.getSort().equals(testValue.getSort()) ||
	    				refValue.getSort().isSuperSortOf(testValue.getSort())) && 
	    		((BooleanConstant)refValue).isValue() == ((BooleanValue)testValue).getValue())
	    {
	    	return true;
	    }
	    return false;
    }
}
