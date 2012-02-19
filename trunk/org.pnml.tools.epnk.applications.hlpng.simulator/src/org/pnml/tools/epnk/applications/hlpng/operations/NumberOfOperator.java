package org.pnml.tools.epnk.applications.hlpng.operations;

import java.util.Map;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

import runtime.AbstractValue;
import runtime.MSValue;
import runtime.RuntimeFactory;

public class NumberOfOperator extends AbstractOperator
{

	public NumberOfOperator(Map<Class, AbstractOperator> handlers, 
			AbstractOperator next)
    {
	    super(handlers, next);
    }

	@Override
	public AbstractValue handle(Term term)
	{
		if(term instanceof NumberOf)
		{
			NumberOf operator = (NumberOf) term;
			
			if(operator.getSubterm().size() != 2 || 
					!(operator.getSubterm().get(0) instanceof NumberConstant))
			{
				throw new RuntimeException("Do not know how to handle: " + operator);
			}
			
			NumberConstant nc = (NumberConstant) operator.getSubterm().get(0);
			int muliplicity = nc.getValue();
			
			Term t = operator.getSubterm().get(1);
			AbstractValue value = handlers.get(t.getClass()).handle(t);
			
			MSValue set = RuntimeFactory.eINSTANCE.createMSValue();
			set.setSort(operator.getSort());
			set.add(value, muliplicity);
			
			return set;
		}
		return next.handle(term);
	}

}
