package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.ArrayList;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.comparators.ComparatorManager;

import runtime.AbstractValue;
import runtime.MSValue;

public class SructuralPatternMatcher
{
	private MSValue cachedValue = null;
	private ComparatorManager comparatorManager = null;
	
	public SructuralPatternMatcher(MSValue value, ComparatorManager comparatorManager)
	{
		this.cachedValue = value;
		this.comparatorManager = comparatorManager;
	}
	
	public List<AbstractValue> match(MSValue value)
	{
		List<AbstractValue> list = new ArrayList<AbstractValue>();
		
		for(AbstractValue aValue : value.getValues().keySet())
		{
			if(cachedValue == null || contains(cachedValue, aValue, 
					value.getValues().get(aValue), comparatorManager))
			{
				list.add(aValue);
			}
		}
		
		if(list.size() == 0)
		{
			return null;
		}
		
		return list;
	}
	
	private static boolean contains(MSValue reference, AbstractValue value, 
			int multiplicity, ComparatorManager comparatorManager)
	{
		for(AbstractValue refValue : reference.getValues().keySet())
		{
			if(comparatorManager.getComparator(refValue.getClass())
					.equals(comparatorManager, refValue, value))
			{
				return true;
			}
		}
		return false;
	}
}
