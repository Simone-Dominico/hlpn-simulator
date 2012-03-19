package org.pnml.tools.epnk.applications.hlpng.runtime.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class NumberOfOperator extends AbstractTermHandler
{

	public NumberOfOperator(TermManager termManager, 
			AbstractTermHandler next)
    {
	    super(termManager, next);
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
			AbstractValue value = termManager.getHandler(t.getClass()).handle(t);
			
			MSValue set = new MSValue();
			set.setSort(operator.getSort());
			set = AbstractValueMath.add(set, value, muliplicity);
			
			return set;
		}
		return next.handle(term);
	}

}
