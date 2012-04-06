package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.Collection;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.StringConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class StringConstantEval implements IEvaluator
{
	@Override
    public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
    {
		if(values.size() != 0)
		{
			throw new RuntimeException();
		}
		
		StringValue value = new StringValue();
		value.setSort(operator.getSort());
		value.setData(((StringConstant)operator).getValue());
		
		return value;
    }
}
