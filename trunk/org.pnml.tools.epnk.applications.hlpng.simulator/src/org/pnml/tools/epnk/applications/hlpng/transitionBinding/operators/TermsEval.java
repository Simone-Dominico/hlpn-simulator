package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Tuple;

public class TermsEval implements IEvaluator
{
	@Override
    public AbstractValue evaluate(List<AbstractValue> values, Operator operator)
    {
		if(operator instanceof Tuple)
		{
			ProductValue product = new ProductValue();
			product.setSort(operator.getSort());
			
			for(AbstractValue value : values)
			{
				product.getComponents().add(value);
			}
			
		    return product;
		}
		return null;
    }
}
