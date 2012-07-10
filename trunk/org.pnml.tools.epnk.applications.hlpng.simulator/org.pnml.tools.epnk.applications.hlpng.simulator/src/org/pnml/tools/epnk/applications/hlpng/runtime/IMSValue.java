package org.pnml.tools.epnk.applications.hlpng.runtime;

import java.util.Collection;
import java.util.Map.Entry;

public interface IMSValue extends IValue
{
	public Collection<Entry<IValue, Integer>> entrySet();
	public int size();
	public boolean contains(IValue value);
	public Integer get(IValue value);
	public void put(IValue value, Integer multiplicity);
	public void remove(IValue value);
	public void putAll(Collection<Entry<IValue, Integer>> entrySet);
	public void clear();
}