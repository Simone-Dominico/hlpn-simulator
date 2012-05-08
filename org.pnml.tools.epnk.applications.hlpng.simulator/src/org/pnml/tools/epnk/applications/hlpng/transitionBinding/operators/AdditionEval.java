package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;


public class AdditionEval extends AbstractIntegerOperation
{
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
			res.setN(0);
			
			result = res;
		}
		
		for(AbstractValue value : values)
		{
			result = evaluate(result, value, (Operator)rootTerm);
		}
		
		return result;
	}
	
	@Override
    protected int computeTotal(int a, int b)
    {
	    return a + b;
    }

	@Override
    protected int computeFirstArg(int result, int[] a)
    {
		int sum = 0;
		for(int i : a)
		{
			sum += i;
		}
	    return result - sum;
    }

	@Override
    protected int computeSecondArg(int result, int[] a)
    {
		return computeFirstArg(result, a);
    }
}
