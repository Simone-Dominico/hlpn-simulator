package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;


public class SubtractionEval extends AbstractIntegerOperation
{
	@Override
    protected int computeTotal(int a, int b)
    {
	    return a - b;
    }

	@Override
    protected int computeFirstArg(int result, int[] a)
    {
		int sum = 0;
		for(int i : a)
		{
			sum += i;
		}
	    return result + sum;
    }

	@Override
    protected int computeSecondArg(int result, int[] a)
    {
		int sum = 0;
		for(int i = 1; i < a.length; i++)
		{
			sum += a[i];
		}
		return a[0] - result - sum;
    }

	@Override
	public AbstractValue evaluate(List<AbstractValue> values, Operator operator)
	{
		if(values.size() < 1)
		{
			throw new RuntimeException("Not enough arguments!");
		}
			
		int sum = 0;
		for(int i = 1; i < values.size(); i++)
		{
			sum += ((NumberValue)values.get(i)).getN();
		}
		
		AbstractValue result = null;
		{
			NumberValue number = (NumberValue)values.get(0);
			
			Sort sort = number.getSort();
			NumberValue res =  createResultObject(sort);
			res.setSort(sort);
			res.setN(number.getN() - sum);
			
			result = res;
		}
		
		return result;
	}
}
