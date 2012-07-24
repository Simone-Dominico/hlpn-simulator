package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.Concatenation;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.StringConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class StringsEval implements IEvaluator
{
	@Override
	public ITermWrapper evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, IValue> assignments) throws UnknownVariableException
	{
		Pair<Boolean, ITermWrapper> p = evaluationManager.evalSubterms(term, assignments);
		if(!p.getKey())
		{
			return p.getValue();
		}
			
		final List<ITermWrapper> values = p.getValue().getSubterms();
		if(term instanceof StringConstant)
		{
			if(values.size() != 0)
			{
				throw new RuntimeException();
			}
			
			StringValue value = new StringValue();
			value.setSort(term.getSort());
			value.setData(((StringConstant)term).getValue());
			
			return value;
		}
		if(term instanceof Concatenation)
		{
			if(values.size() < 1)
			{
				throw new RuntimeException("Not enough arguments!");
			}
			
			StringBuffer text = new StringBuffer();
			for(ITermWrapper value : values)
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

	@Override
    public String validate(Object term)
    {
		if(term instanceof StringConstant)
		{
			return null;
		}
		if(term instanceof Concatenation)
		{
			return null;
		}
	    return term.getClass().toString();
    }
}
