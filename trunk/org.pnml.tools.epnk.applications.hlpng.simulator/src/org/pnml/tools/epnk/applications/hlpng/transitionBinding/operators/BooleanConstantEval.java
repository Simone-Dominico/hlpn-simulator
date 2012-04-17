package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.Collection;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleanConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class BooleanConstantEval implements IEvaluator
{
	@Override
    public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
    {
		if(values.size() != 0)
		{
			throw new RuntimeException();
		}
		
		BooleanValue value = new BooleanValue();
		value.setSort(operator.getSort());
		value.setValue(((BooleanConstant)operator).isValue());
		
		return value;
    }
}
