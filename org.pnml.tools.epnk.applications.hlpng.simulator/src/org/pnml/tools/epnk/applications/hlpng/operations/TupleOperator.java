package org.pnml.tools.epnk.applications.hlpng.operations;

import java.util.Map;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Tuple;

import productRuntime.ProductValue;
import productRuntime.ProductruntimeFactory;

import runtime.AbstractValue;

public class TupleOperator extends AbstractOperator
{

	public TupleOperator(Map<Class, AbstractOperator> handlers, 
			AbstractOperator next)
	{
		super(handlers, next);
	}

	@Override
	public AbstractValue handle(Term term)
	{
		if(term instanceof Tuple)
		{
			Tuple op = (Tuple) term;
			
			ProductValue product = ProductruntimeFactory.eINSTANCE.createProductValue();
			product.setSort(op.getSort());

			for(Term t : op.getSubterm())
			{
				AbstractValue v = handlers.get(t.getClass()).handle(t);
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
