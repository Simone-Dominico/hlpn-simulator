package org.pnml.tools.epnk.applications.hlpng.utils;

public class Pair<K, V>
{
	private K key = null;
	private V value = null;
	
	public Pair(K key, V value)
	{
		this.key = key;
		this.value = value;
	}
	public Pair(){}
	
	public K getKey()
    {
    	return key;
    }

	public V getValue()
    {
    	return value;
    }

	public void setKey(K key)
    {
    	this.key = key;
    }

	public void setValue(V value)
    {
    	this.value = value;
    }
	@Override
    public String toString()
    {
	    return key + "=" + value;
    }
}
