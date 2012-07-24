package org.pnml.tools.epnk.applications.hlpng.runtime;

import java.util.Collection;
import java.util.Map.Entry;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;

public interface IMSValue extends IValue
{
	public Collection<Entry<ITermWrapper, Integer>> entrySet();
	public int size();
	public boolean contains(ITermWrapper value);
	public Integer get(ITermWrapper value);
	public void put(ITermWrapper value, Integer multiplicity);
	public void remove(ITermWrapper value);
	public void putAll(Collection<Entry<ITermWrapper, Integer>> entrySet);
	public void clear();
}