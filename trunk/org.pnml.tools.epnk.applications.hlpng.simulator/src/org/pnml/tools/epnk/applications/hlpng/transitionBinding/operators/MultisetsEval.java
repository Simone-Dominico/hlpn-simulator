package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Add;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Subtract;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class MultisetsEval implements IEvaluator
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
		if(operator instanceof NumberOf)
		{
			MSValue set = new MSValue();
			set.setSort(operator.getSort());

			set = AbstractValueMath.add(set, values.get(1), ((NumberValue)values.get(0)).getN());

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
			MSValue set = (MSValue) values.get(0);
			
			for(int i = 1; i < values.size(); i++)
			{
				MSValue ms = (MSValue) values.get(i);
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
