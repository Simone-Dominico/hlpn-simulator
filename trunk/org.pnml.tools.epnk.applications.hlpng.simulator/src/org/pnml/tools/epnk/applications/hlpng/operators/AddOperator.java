package org.pnml.tools.epnk.applications.hlpng.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Add;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class AddOperator extends AbstractTermHandler
{

	public AddOperator(TermManager termManager, AbstractTermHandler next)
	{
		super(termManager, next);
	}

	@Override
	public AbstractValue handle(Term term)
	{
		if(term instanceof Add)
		{
			Add op = (Add) term;
			if(op.getSubterm().size() != 2)
			{
				throw new RuntimeException("Incorrect number of arguments: " + term);
			}
			MSValue set = new MSValue();
			set.setSort(op.getSort());
			{
				Term t = op.getSubterm().get(0);
				AbstractTermHandler handler = termManager.getHandler(t.getClass());
				set = AbstractValueMath.append(set, (MSValue)handler.handle(t));
			}
			{
				Term t = op.getSubterm().get(1);
				AbstractTermHandler handler = termManager.getHandler(t.getClass());
				set = AbstractValueMath.append(set, (MSValue)handler.handle(t));
			}

			return set;
		}
		return next.handle(term);
	}

}
