package org.pnml.tools.epnk.applications.hlpng.operations;

import java.util.Map;

import numberRuntime.NumberValue;
import numberRuntime.NumberruntimeFactory;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Natural;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Positive;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

import runtime.AbstractValue;

public class NumberConstantOperator extends AbstractOperator
{

	public NumberConstantOperator(Map<Class, AbstractOperator> handlers)
    {
	    super(handlers);
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
				v = NumberruntimeFactory.eINSTANCE.createNatValue();
			}
			else if(op.getSort() instanceof Positive)
			{
				v = NumberruntimeFactory.eINSTANCE.createPosValue();
			}
			else
			{
				v = NumberruntimeFactory.eINSTANCE.createIntValue();
			}

			if(v != null)
			{
				v.setSort(op.getSort());
				v.setN(op.getValue());
			}
			return v;
		}
		throw new RuntimeException("Mismatch: " + term);
	}

}
