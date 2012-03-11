package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;

import runtime.AbstractValue;
import runtime.RuntimeVariable;

public interface IComparator
{
	public boolean compare(ComparatorManager manager,
            AbstractValue refValue, AbstractValue testValue,
			Map<RuntimeVariable, AbstractValue> assignments);
}
