package org.pnml.tools.epnk.applications.hlpng.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Tuple;

public class TupleOperator extends AbstractTermHandler
{

	public TupleOperator(TermManager termManager, AbstractTermHandler next)
	{
		super(termManager, next);
	}

	@Override
	public AbstractValue handle(Term term)
	{
		if(term instanceof Tuple)
		{
			Tuple op = (Tuple) term;
			
			ProductValue product = new ProductValue();
			product.setSort(op.getSort());

			for(Term t : op.getSubterm())
			{
				AbstractValue v = termManager.getHandler(t.getClass()).handle(t);
				if(v != null)
				{
					product.getComponents().add(v);
				}
			}
			return product;
		}
		return next.handle(term);
	}

}
