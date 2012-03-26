package org.pnml.tools.epnk.applications.hlpng.runtime.operators;

import java.util.Collection;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class AddEval implements IEvaluator
{
	@Override
	public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
	{
		MSValue set = new MSValue();
		set.setSort(operator.getSort());
		
		for(AbstractValue value : values)
		{
			MSValue ms = (MSValue) value;
			set = AbstractValueMath.append(set, ms);
		}
		return set;
	}


}
