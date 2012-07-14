package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Add;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.All;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Subtract;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserSort;

public class MultisetsEval implements IEvaluator
{
	private SortEvaluationManager sortEvaluationManager = null;
	private RuntimeValueFactory factory = null;
	
	public MultisetsEval(RuntimeValueFactory factory)
	{
		this.factory = factory; 
	}
	
	@Override
	public IValue evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, IValue> assignments) throws UnknownVariableException
	{
		Operator operator = (Operator) term;
		List<IValue> values = new ArrayList<IValue>();
		for(Term subterm : operator.getSubterm())
		{
			IEvaluator evaluator = evaluationManager.getHandler(subterm.getClass()); 
			IValue value = evaluator.evaluate(subterm, evaluationManager, assignments);
			values.add(value);
		}
		if(operator instanceof NumberOf)
		{
			IMSValue set = this.factory.createMSValue();
			set.setSort(operator.getSort());

			int multiplicity = ((NumberValue)values.get(0)).getN();
			if(values.get(1) instanceof IMSValue)
			{
				for(Entry<IValue, Integer> value : ((IMSValue)values.get(1)).entrySet())
				{
					IMSValue msValue = this.factory.createMSValue();
					msValue.setSort(value.getKey().getSort());
					msValue.put(value.getKey(), value.getValue());
					
					set = AbstractValueMath.add(set, msValue, multiplicity, this.factory);
				}
			}
			else
			{
				set = AbstractValueMath.add(set, values.get(1), multiplicity, this.factory);	
			}

		    return set;
		}
		if(operator instanceof Add)
		{
			IMSValue set = this.factory.createMSValue();
			set.setSort(operator.getSort());
			
			for(IValue value : values)
			{
				IMSValue ms = (IMSValue) value;
				set = AbstractValueMath.append(set, ms, this.factory);
			}
			return set;
		}
		if(operator instanceof Subtract)
		{
			IMSValue set = (IMSValue) values.get(0);
			
			for(int i = 1; i < values.size(); i++)
			{
				IMSValue ms = (IMSValue) values.get(i);
				set = AbstractValueMath.subtract(set, ms, this.factory);
			}
			return set;
		}
		if(operator instanceof All)
		{
			All allOp = (All) operator;
			return sortEvaluationManager.evaluate(allOp.getRefsort());
		}
		return null;
	}

	public SortEvaluationManager getSortEvaluationManager()
    {
    	return sortEvaluationManager;
    }

	public void setSortEvaluationManager(
            SortEvaluationManager dataTypeEvaluationManager)
    {
    	this.sortEvaluationManager = dataTypeEvaluationManager;
    }

	@Override
    public String validate(Object term)
    {
		if(term instanceof NumberOf)
		{
			return null;
		}
		if(term instanceof Add)
		{
			return null;
		}
		if(term instanceof Subtract)
		{
			return null;
		}
		if(term instanceof All)
		{
			All all = (All)term;
			if(sortEvaluationManager == null)
			{
				if(all.getRefsort() instanceof UserSort)
				{
					String name = ((UserSort)all.getRefsort()).
							getDeclaration().getName();
					return "User defined sort\n" + name;
				}
				return "all:" + all.getRefsort().getClass().getName();
			}
			ISortEvaluator eval = 
					sortEvaluationManager.getHandler(all.getRefsort().getClass());
			
			if(eval == null)
			{
				return "all:" + all.getRefsort().getClass().getName();
			}
			return eval.validate(all.getRefsort());
		}
		return term.getClass().toString();
    }
}