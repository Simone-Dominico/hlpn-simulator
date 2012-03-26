package org.pnml.tools.epnk.applications.hlpng.runtime.operators;

import java.util.Collection;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IntValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NatValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Natural;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Positive;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class NumberConstantEval implements IEvaluator
{
	@Override
    public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
    {
		if(values.size() != 0)
		{
			throw new RuntimeException();
		}
		
		NumberValue v = null;
		if(operator.getSort() instanceof Natural)
		{
			v = new NatValue();
		}
		else if(operator.getSort() instanceof Positive)
		{
			v = new PosValue();
		}
		else
		{
			v = new IntValue();
		}

		if(v != null)
		{
			v.setSort(operator.getSort());
			v.setN(((NumberConstant)operator).getValue());
		}
		return v;
    }
}
