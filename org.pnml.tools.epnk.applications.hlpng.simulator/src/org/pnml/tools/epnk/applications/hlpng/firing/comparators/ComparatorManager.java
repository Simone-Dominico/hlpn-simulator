package org.pnml.tools.epnk.applications.hlpng.firing.comparators;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IntValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NatValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeVariable;

public class ComparatorManager
{
	private Map<Class, IComparator> handlers = null;

	public ComparatorManager()
	{
		handlers = new HashMap<Class, IComparator>();
		{
			handlers.put(NatValue.class, new NumberConstantComparator());
			handlers.put(PosValue.class, new NumberConstantComparator());
			handlers.put(IntValue.class, new NumberConstantComparator());
			
			handlers.put(ProductValue.class, new ProductComparator());
			handlers.put(MSValue.class, new MultisetComparator());
			
			handlers.put(RuntimeVariable.class, new VariableMatcher());
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
