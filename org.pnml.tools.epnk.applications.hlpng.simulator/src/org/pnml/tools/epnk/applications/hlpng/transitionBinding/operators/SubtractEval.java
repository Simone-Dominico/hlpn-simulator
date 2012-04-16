package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class SubtractEval implements IEvaluator
{
	@Override
	public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
	{
		List<AbstractValue> valueList = new ArrayList<AbstractValue>(values);
		
		MSValue set = (MSValue) valueList.get(0);
		
		for(int i = 1; i < valueList.size(); i++)
		{
			MSValue ms = (MSValue) valueList.get(i);
			set = AbstractValueMath.subtract(set, ms);
		}
		return set;
	}


}
