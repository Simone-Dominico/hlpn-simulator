package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.StringConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class StringConstantComparator implements IComparable
{
	@Override
	public boolean compare(Term refValue, AbstractValue testValue,
            Map<String, VariableEvaluation> assignments)
    {
	    if(refValue instanceof StringConstant && testValue instanceof StringValue &&
	    		(refValue.getSort().equals(testValue.getSort()) ||
	    				refValue.getSort().isSuperSortOf(testValue.getSort())) && 
	    		((StringConstant)refValue).getValue().equals(((StringValue)testValue).getData()))
	    {
	    	return true;
	    }
	    return false;
    }
}
