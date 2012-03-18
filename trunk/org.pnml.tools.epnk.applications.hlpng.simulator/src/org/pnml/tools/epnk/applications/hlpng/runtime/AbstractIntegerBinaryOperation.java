package org.pnml.tools.epnk.applications.hlpng.runtime;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Natural;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Positive;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public abstract class AbstractIntegerBinaryOperation extends AbstractBinaryOperation
{
	protected NumberValue createResultObject(Sort sort)
	{
		NumberValue v = null;
		if(sort instanceof Natural)
		{
			v = new NatValue();
		}
		else if(sort instanceof Positive)
		{
			v = new PosValue();
		}
		else
		{
			v = new IntValue();
		}
		return v;
	}
}
