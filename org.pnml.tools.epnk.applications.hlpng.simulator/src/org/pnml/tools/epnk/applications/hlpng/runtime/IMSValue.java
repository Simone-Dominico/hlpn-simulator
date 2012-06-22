package org.pnml.tools.epnk.applications.hlpng.runtime;

import java.util.Collection;
import java.util.Map.Entry;

public interface IMSValue
{
	public Collection<Entry<AbstractValue, Integer>> entrySet();
	public int size();
	public boolean contains(AbstractValue value);
	public Integer get(AbstractValue value);
	public void put(AbstractValue value, Integer multiplicity);
	public void remove(AbstractValue value);
	public void putAll(Collection<Entry<AbstractValue, Integer>> entrySet);
	public void clear();
}