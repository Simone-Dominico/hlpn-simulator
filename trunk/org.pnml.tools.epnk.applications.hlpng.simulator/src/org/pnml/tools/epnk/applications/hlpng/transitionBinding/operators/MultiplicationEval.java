package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;


public class MultiplicationEval extends AbstractIntegerOperation
{
	@Override
    protected int computeTotal(int a, int b)
    {
	    return a * b;
    }

	@Override
    protected int computeFirstArg(int result, int[] a)
    {
		int mul = 1;
		for(int i : a)
		{
			mul *= i;
		}
	    return result / mul;
    }

	@Override
    protected int computeSecondArg(int result, int[] a)
    {
		return computeFirstArg(result, a);
    }

	@Override
	public AbstractValue evaluate(List<AbstractValue> values, Operator operator)
	{
		if(values.size() < 1)
		{
			throw new RuntimeException("Not enough arguments!");
		}
			
		AbstractValue result = null;
		{
			NumberValue number = (NumberValue)values.get(0);
			
			Sort sort = number.getSort();
			NumberValue res=  createResultObject(sort);
			res.setSort(sort);
			res.setN(1);
			
			result = res;
		}
		
		for(AbstractValue value : values)
		{
			result = evaluate(result, value, (Operator)rootTerm);
		}
		
		return result;
	}
}
