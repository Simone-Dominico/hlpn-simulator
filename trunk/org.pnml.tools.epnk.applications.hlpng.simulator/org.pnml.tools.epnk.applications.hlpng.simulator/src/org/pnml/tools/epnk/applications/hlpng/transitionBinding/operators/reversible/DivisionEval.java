package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.reversible;

import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;


public class DivisionEval extends AbstractIntegerOperation
{
	@Override
    protected int computeTotal(int a, int b)
    {
	    return a / b;
    }

	@Override
    protected int computeFirstArg(int result, int[] a)
    {
		int mult = 1;
		for(int i : a)
		{
			mult *= i;
		}
	    return result * mult;
    }

	@Override
    protected int computeSecondArg(int result, int[] a)
    {
		int mult = 1;
		for(int i = 1; i < a.length; i++)
		{
			mult *= a[i];
		}
		return computeTotal(computeTotal(a[0], result), mult);
    }

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
		if(values.size() < 1)
		{
			throw new RuntimeException("Not enough arguments!");
		}
			
		int mult = 1;
		for(int i = 1; i < values.size(); i++)
		{
			mult *= ((NumberValue)values.get(i)).getN();
		}
		
		IValue result = null;
		{
			NumberValue number = (NumberValue)values.get(0);
			
			Sort sort = number.getSort();
			NumberValue res =  createResultObject(sort);
			res.setSort(sort);
			res.setN(computeTotal(number.getN(), mult));
			
			result = res;
		}
		
		return result;
	}

	@Override
    public String validate(Object term)
    {
	    return null;
    }
}
