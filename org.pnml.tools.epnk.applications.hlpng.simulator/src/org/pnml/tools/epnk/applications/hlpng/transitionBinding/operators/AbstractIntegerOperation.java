package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IntValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NatValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Natural;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Positive;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public abstract class AbstractIntegerOperation extends AbstractReversibleOperation
{
	protected abstract int computeTotal(int a, int b);
	protected abstract int computeArg(int result, int a);

	@Override
    public AbstractValue revert(AbstractValue result, AbstractValue arg)
    {
		Sort sort = arg.getSort();
		
		NumberValue v =  createResultObject(sort);
		v.setSort(sort);
		v.setN(computeArg(((NumberValue)result).getN(), ((NumberValue)arg).getN()));
		
	    return v;
    }

    public AbstractValue evaluate(AbstractValue arg1, AbstractValue arg2,
            Operator operator)
    {
		Sort sort = arg1.getSort();
		
		NumberValue v =  createResultObject(sort);
		v.setSort(sort);
		v.setN(computeTotal(((NumberValue)arg1).getN(), ((NumberValue)arg2).getN()));
		
	    return v;
    }
	
	protected NumberValue createResultObject(Sort sort)
	{
		NumberValue v = null;
		if(sort instanceof Positive)
		{
			v = new PosValue();
		}
		else if(sort instanceof Natural)
		{
			v = new NatValue();
		}
		else
		{
			v = new IntValue();
		}
		return v;
	}
}
