package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.HashMap;
import java.util.Map;

import numberRuntime.IntValue;
import numberRuntime.impl.NatValueImpl;
import numberRuntime.impl.PosValueImpl;

import productRuntime.impl.ProductValueImpl;
import runtime.impl.MSValueImpl;

public class ComparatorManager
{
	private Map<Class, IComparator> handlers = null;

	public ComparatorManager()
	{
		handlers = new HashMap<Class, IComparator>();
		{
			handlers.put(NatValueImpl.class, new NumberConstantComparator());
			handlers.put(PosValueImpl.class, new NumberConstantComparator());
			handlers.put(IntValue.class, new NumberConstantComparator());
			
			handlers.put(ProductValueImpl.class, new ProductComparator());
			handlers.put(MSValueImpl.class, new MultisetComparator());
		}
	}
	
	public void register(Class targetClass, IComparator comparator)
	{
		handlers.put(targetClass, comparator);
	}
	
	public void unregister(Class targetClass)
	{
		handlers.remove(targetClass);
	}
	
	public IComparator getComparator(Class targetClass)
	{
		return handlers.get(targetClass);
	}
}
