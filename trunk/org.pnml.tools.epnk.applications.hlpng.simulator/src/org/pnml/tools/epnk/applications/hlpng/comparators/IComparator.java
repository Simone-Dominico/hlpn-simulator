package org.pnml.tools.epnk.applications.hlpng.comparators;

import runtime.AbstractValue;

public interface IComparator
{
	public boolean compare(ComparatorManager manager, 
			AbstractValue value1, AbstractValue value2);
}
