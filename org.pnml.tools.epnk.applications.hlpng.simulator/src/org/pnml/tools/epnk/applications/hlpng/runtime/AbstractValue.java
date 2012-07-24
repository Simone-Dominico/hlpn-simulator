package org.pnml.tools.epnk.applications.hlpng.runtime;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public abstract class AbstractValue extends TermWrapper implements IValue
{
	protected Sort sort;

	@Override
	public Sort getSort()
    {
    	return sort;
    }

	@Override
	public void setSort(Sort sort)
    {
    	this.sort = sort;
    }
}
