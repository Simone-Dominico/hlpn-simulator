package org.pnml.tools.epnk.applications.hlpng.transitionBinding;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractManager<K, T> implements IManager<K, T>
{
	private Map<Object, K> handlers = new HashMap<Object, K>();
	
	@Override
	public void register(Object target, K eval)
	{
		handlers.put(target, eval);
	}
	
	@Override
	public void unregister(Object target)
	{
		handlers.remove(target);
	}
	
	@Override
	public boolean contains(Object target)
	{
		return handlers.containsKey(target);
	}
	
	@Override
	public K getHandler(Class<? extends T> targetClass)
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
}
