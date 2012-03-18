package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeVariable;

public class VariableMatcher implements IComparator
{
	@Override
	public boolean compare(ComparatorManager manager,
            AbstractValue refValue, AbstractValue testValue,
			Map<RuntimeVariable, AbstractValue> assignments)
	{
		if(!(refValue instanceof RuntimeVariable))
		{
			throw new RuntimeException("Wrong comparator: a reference value is" +
					" not an instance of " + RuntimeVariable.class);
		}
		
		assignments.put((RuntimeVariable)refValue, testValue);

		return true;
	}

}
