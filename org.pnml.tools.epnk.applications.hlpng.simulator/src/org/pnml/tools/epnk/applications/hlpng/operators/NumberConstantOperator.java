package org.pnml.tools.epnk.applications.hlpng.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IntValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NatValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Natural;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Positive;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class NumberConstantOperator extends AbstractTermHandler
{

	public NumberConstantOperator(TermManager termManager, AbstractTermHandler next)
    {
	    super(termManager, next);
    }

	@Override
	public AbstractValue handle(Term term)
	{
		if(term instanceof NumberConstant)
		{
			NumberConstant op = (NumberConstant) term;
			
			if(op.getSubterm().size() != 0 || !(op.getSort() instanceof 
					org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number))
			{
				throw new RuntimeException("Do not know how to handle: " + term);
			}
			
			NumberValue v = null;
			if(op.getSort() instanceof Natural)
			{
				v = new NatValue();
			}
			else if(op.getSort() instanceof Positive)
			{
				v = new PosValue();
			}
			else
			{
				v = new IntValue();
			}

			if(v != null)
			{
				v.setSort(op.getSort());
				v.setN(op.getValue());
			}
			return v;
		}
		return next.handle(term);
	}

}
