package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class NumberOfEval implements IEvaluator
{
	@Override
    public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
    {
		MSValue set = new MSValue();
		set.setSort(operator.getSort());
		
		List<AbstractValue> tmp = new ArrayList<AbstractValue>(values);
		set = AbstractValueMath.add(set, tmp.get(1), ((NumberValue)tmp.get(0)).getN());

	    return set;
    }
}
