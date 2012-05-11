package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.Concatenation;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.StringConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class StringsEval implements IEvaluator
{
	@Override
	public AbstractValue evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, AbstractValue> assignments) throws UnknownVariableException
	{
		Operator operator = (Operator) term;
		List<AbstractValue> values = new ArrayList<AbstractValue>();
		for(Term subterm : operator.getSubterm())
		{
			IEvaluator evaluator = evaluationManager.getHandler(subterm.getClass()); 
			AbstractValue value = evaluator.evaluate(subterm, evaluationManager, assignments);
			values.add(value);
		}
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
