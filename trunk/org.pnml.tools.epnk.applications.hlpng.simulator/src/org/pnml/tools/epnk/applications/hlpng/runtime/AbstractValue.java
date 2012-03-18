package org.pnml.tools.epnk.applications.hlpng.runtime;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public abstract class AbstractValue
{
	protected Sort sort;

	public Sort getSort()
    {
    	return sort;
    }

	public void setSort(Sort sort)
    {
    	this.sort = sort;
    }
}
