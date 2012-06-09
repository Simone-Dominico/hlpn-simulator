package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;


public class MultiplicationEval extends AbstractIntegerOperation
{
	@Override
    protected int computeTotal(int a, int b)
    {
	    return a * b;
    }

	@Override
    protected int computeFirstArg(int result, int[] a)
    {
		int mul = 1;
		for(int i : a)
		{
			mul *= i;
		}
	    return result / mul;
    }

	@Override
    protected int computeSecondArg(int result, int[] a)
    {
		return computeFirstArg(result, a);
    }

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
		
		if(values.size() < 1)
		{
			throw new RuntimeException("Not enough arguments!");
		}
			
		AbstractValue result = null;
		{
			NumberValue number = (NumberValue)values.get(0);
			
			Sort sort = number.getSort();
			NumberValue res=  createResultObject(sort);
			res.setSort(sort);
			res.setN(1);
			
			result = res;
		}
		
		for(AbstractValue value : values)
		{
			result = evaluate(result, value, (Operator)rootTerm);
		}
		
		return result;
	}

	@Override
    public String validate(Object term)
    {
	    return null;
    }
}
