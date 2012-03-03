package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;

import runtime.AbstractValue;

public interface IComparator
{
	public boolean equals(Map<Class, IComparator> comparators, 
			AbstractValue value1, AbstractValue value2);
}
