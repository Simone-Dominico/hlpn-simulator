package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Tuple;

public class TermsEval implements IEvaluator
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
		if(term instanceof Tuple)
		{
			ProductValue product = new ProductValue();
			product.setSort(term.getSort());
			
			for(ITermWrapper value : values)
			{
				product.getComponents().add((IValue)value);
			}
			
		    return product;
		}
		return null;
    }

	@Override
    public String validate(Object term)
    {
		if(term instanceof Tuple)
		{
		    return null;
		}
		return term.getClass().toString();
    }
}
