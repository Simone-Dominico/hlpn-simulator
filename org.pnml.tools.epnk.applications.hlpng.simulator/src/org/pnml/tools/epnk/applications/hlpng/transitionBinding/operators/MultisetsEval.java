package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Add;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Subtract;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class MultisetsEval implements IEvaluator
{

	@Override
	public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
	{
		if(operator instanceof NumberOf)
		{
			MSValue set = new MSValue();
			set.setSort(operator.getSort());
			
			List<AbstractValue> tmp = new ArrayList<AbstractValue>(values);
			set = AbstractValueMath.add(set, tmp.get(1), ((NumberValue)tmp.get(0)).getN());

		    return set;
		}
		if(operator instanceof Add)
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
		if(operator instanceof Subtract)
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
		if(operator instanceof NumberOf)
		{
			
		}
		return null;
	}

}
