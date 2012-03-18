package org.pnml.tools.epnk.applications.hlpng.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
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
			
			if(operator.getSubterm().size() != 2)
			{
				throw new RuntimeException("Do not know how to handle: " + operator);
			}

			Term multiplicityTerm = operator.getSubterm().get(0);
			
			AbstractTermHandler multiplicityHandler = termManager.getHandler(multiplicityTerm.getClass());
			AbstractValue multiplicity = multiplicityHandler.handle(multiplicityTerm);
			
			Term valueTerm = operator.getSubterm().get(1);
			AbstractValue value = termManager.getHandler(valueTerm.getClass()).handle(valueTerm);
			
			MSValue set = new MSValue();
			set.setSort(operator.getSort());
			set = AbstractValueMath.add(set, value, multiplicity);
			
			return set;
		}
		return next.handle(term);
	}

}
