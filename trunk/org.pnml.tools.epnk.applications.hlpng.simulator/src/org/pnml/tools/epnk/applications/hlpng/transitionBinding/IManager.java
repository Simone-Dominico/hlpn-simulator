package org.pnml.tools.epnk.applications.hlpng.transitionBinding;

public interface IManager<K, T>
{
	public void register(Object o, K operator);
	
	public void unregister(Object o);

	public boolean contains(Class<? extends T> o);
	
	public K getHandler(Class<? extends T> o);
}
