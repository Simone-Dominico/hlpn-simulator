package org.pnml.tools.epnk.applications.hlpng.runtime;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public interface IValue extends ITermWrapper
{
	public Sort getSort();
	public void setSort(Sort sort);
}
