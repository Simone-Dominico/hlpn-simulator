package org.pnml.tools.epnk.applications.hlpng.comparators;

import runtime.AbstractValue;

public interface IComparator
{
	public boolean equals(ComparatorManager manager, 
			AbstractValue value1, AbstractValue value2);
}
