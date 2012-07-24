package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.DotValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleanConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.dots.DotConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.StringConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class DatatypesComparator implements IComparable
{

	@Override
	public boolean compare(Object refValue, Object testValue,
	        Map<TermWrapper, TermAssignment> assignments)
	{
		if(refValue instanceof BooleanConstant && testValue instanceof BooleanValue &&
	    		(((Term)refValue).getSort().equals(((IValue)testValue).getSort()) ||
	    				((Term)refValue).getSort().isSuperSortOf(((IValue)testValue).getSort())) && 
	    		((BooleanConstant)refValue).isValue() == ((BooleanValue)testValue).getValue())
	    {
	    	return true;
	    }
		if(refValue instanceof NumberConstant && testValue instanceof NumberValue &&
	    		(((Term)refValue).getSort().equals(((IValue)testValue).getSort()) ||
	    				((Term)refValue).getSort().isSuperSortOf(((IValue)testValue).getSort())) && 
	    		((NumberConstant)refValue).getValue() == ((NumberValue)testValue).getN())
	    {
	    	return true;
	    }
		if(refValue instanceof NumberValue && testValue instanceof NumberValue)
	    {
	    	return ((NumberValue)refValue).getN() == ((NumberValue)testValue).getN();
	    }
		if(refValue instanceof StringConstant && testValue instanceof StringValue &&
	    		(((Term)refValue).getSort().equals(((IValue)testValue).getSort()) ||
	    				((Term)refValue).getSort().isSuperSortOf(((IValue)testValue).getSort())) && 
	    		((StringConstant)refValue).getValue().equals(((StringValue)testValue).getData()))
	    {
	    	return true;
	    }
		if(refValue instanceof DotConstant || testValue instanceof DotValue)
	    {
			return true;
	    }
    	
		return false;
	}

}
