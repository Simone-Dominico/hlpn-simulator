package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Add;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.All;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Subtract;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class MultisetsEval implements IEvaluator
{
	private DataTypeEvaluationManager dataTypeEvaluationManager = null;
	
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

			int multiplicity = ((NumberValue)values.get(0)).getN();
			if(values.get(1) instanceof MSValue)
			{
				for(Entry<AbstractValue, Integer> value : ((MSValue)values.get(1)).getValues().entrySet())
				{
					MSValue msValue = new MSValue();
					msValue.setSort(value.getKey().getSort());
					msValue.getValues().put(value.getKey(), value.getValue());
					
					set = AbstractValueMath.add(set, msValue, multiplicity);
				}
			}
			else
			{
				set = AbstractValueMath.add(set, values.get(1), multiplicity);	
			}

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
		if(operator instanceof All)
		{
			All allOp = (All) operator;
			return dataTypeEvaluationManager.evaluate(allOp.getRefsort());
		}
		return null;
	}

	public DataTypeEvaluationManager getDataTypeEvaluationManager()
    {
    	return dataTypeEvaluationManager;
    }

	public void setDataTypeEvaluationManager(
            DataTypeEvaluationManager dataTypeEvaluationManager)
    {
    	this.dataTypeEvaluationManager = dataTypeEvaluationManager;
    }
}
