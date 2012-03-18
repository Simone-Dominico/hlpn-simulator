package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeVariable;

public interface IComparator
{
	public boolean compare(ComparatorManager manager,
            AbstractValue refValue, AbstractValue testValue,
			Map<RuntimeVariable, AbstractValue> assignments);
}
