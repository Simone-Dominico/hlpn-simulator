package org.pnml.tools.epnk.applications.hlpng.operators;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Tuple;

import productRuntime.ProductValue;
import productRuntime.ProductRuntimeFactory;

import runtime.AbstractValue;

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
			
			ProductValue product = ProductRuntimeFactory.eINSTANCE.createProductValue();
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
