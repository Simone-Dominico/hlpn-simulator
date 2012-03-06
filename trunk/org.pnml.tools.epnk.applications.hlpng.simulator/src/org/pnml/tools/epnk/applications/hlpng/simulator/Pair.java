package org.pnml.tools.epnk.applications.hlpng.simulator;

public class Pair<K, V>
{
	private K key = null;
	private V value = null;
	
	public Pair(K key, V value)
	{
		this.key = key;
		this.value = value;
	}
	
	public K getKey()
    {
    	return key;
    }

	public V getValue()
    {
    	return value;
    }
}
