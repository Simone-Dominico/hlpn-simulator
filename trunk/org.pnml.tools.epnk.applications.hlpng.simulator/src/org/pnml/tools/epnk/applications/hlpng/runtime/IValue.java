package org.pnml.tools.epnk.applications.hlpng.runtime;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public interface IValue
{
	public Sort getSort();
	public void setSort(Sort sort);
}
