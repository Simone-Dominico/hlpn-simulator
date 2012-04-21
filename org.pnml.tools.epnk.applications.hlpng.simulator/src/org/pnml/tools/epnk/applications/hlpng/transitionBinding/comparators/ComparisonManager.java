package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.HashMap;
import java.util.Map;

public class ComparisonManager
{
	private Map<Class, IComparable> handlers = new HashMap<Class, IComparable>();
	
	public void register(Class targetClass, IComparable comparator)
	{
		handlers.put(targetClass, comparator);
	}
	
	public void unregister(Class targetClass)
	{
		handlers.remove(targetClass);
	}
	
	public IComparable getComparator(Class targetClass)
	{
		return handlers.get(targetClass);
	}
}
