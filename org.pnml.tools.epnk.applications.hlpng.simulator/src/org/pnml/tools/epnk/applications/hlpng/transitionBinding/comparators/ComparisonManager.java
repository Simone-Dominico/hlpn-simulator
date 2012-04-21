package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.HashMap;
import java.util.Map;

public class ComparisonManager
{
	private Map<Object, IComparable> handlers = new HashMap<Object, IComparable>();
	
	public void register(Object targetObject, IComparable comparator)
	{
		handlers.put(targetObject, comparator);
	}
	
	public IComparable getComparator(Class targetClass)
	{
		if(handlers.containsKey(targetClass))
		{
			return handlers.get(targetClass);
		}
		if(handlers.containsKey(targetClass.getPackage()))
		{
			return handlers.get(targetClass.getPackage());
		}
		return null;
	}
	
	public void unregister(Object targetObject)
	{
		handlers.remove(targetObject);
	}
}
