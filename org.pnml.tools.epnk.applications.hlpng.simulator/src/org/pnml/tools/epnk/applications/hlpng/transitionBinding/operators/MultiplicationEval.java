package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.Collection;
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
    protected int computeArg(int result, int a)
    {
	    return result / a;
    }

	@Override
	public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
	{
		if(values.size() < 1)
		{
			throw new RuntimeException("Not enough arguments!");
		}
			
		AbstractValue result = null;
		{
			List<AbstractValue> list = new ArrayList<AbstractValue>(values);
			NumberValue number = (NumberValue)list.get(0);
			
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
