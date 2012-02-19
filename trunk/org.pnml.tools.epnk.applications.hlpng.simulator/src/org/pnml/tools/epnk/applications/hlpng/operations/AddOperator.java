package org.pnml.tools.epnk.applications.hlpng.operations;

import java.util.Map;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Add;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

import runtime.AbstractValue;
import runtime.MSValue;
import runtime.RuntimeFactory;

public class AddOperator extends AbstractOperator
{

	public AddOperator(Map<Class, AbstractOperator> handlers)
	{
		super(handlers);
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
			MSValue set = RuntimeFactory.eINSTANCE.createMSValue();
			set.setSort(op.getSort());
			{
				Term t = op.getSubterm().get(0);
				AbstractOperator handler = handlers.get(t.getClass());
				set.append((MSValue)handler.handle(t));
			}
			{
				Term t = op.getSubterm().get(1);
				AbstractOperator handler = handlers.get(t.getClass());
				set.append((MSValue)handler.handle(t));
			}

			return set;
		}
		throw new RuntimeException("Mismatch: " + term);
	}

}
