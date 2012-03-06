package org.pnml.tools.epnk.applications.hlpng.comparators;

import runtime.AbstractValue;

public class VariableMatcher implements IComparator
{

	@Override
	public boolean compare(ComparatorManager manager, AbstractValue value1,
	        AbstractValue value2)
	{
		System.out.println(value1);
		System.out.println(value2);
		return true;
	}

}
