package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.Concatenation;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.StringConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public class StringsEval implements IEvaluator
{
	@Override
	public AbstractValue evaluate(List<AbstractValue> values, Operator operator)
	{
		if(operator instanceof StringConstant)
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
		if(operator instanceof Concatenation)
		{
			if(values.size() < 1)
			{
				throw new RuntimeException("Not enough arguments!");
			}
			
			StringBuffer text = new StringBuffer();
			for(AbstractValue value : values)
			{
				text.append(((StringValue)value).getData());
			}
			
			StringValue result = new StringValue();
			{
				StringValue str = (StringValue)values.get(0);
				
				Sort sort = str.getSort();

				result.setSort(sort);
				result.setData(text.toString());
			}
			
			return result;
		}
		return null;
	}
}
