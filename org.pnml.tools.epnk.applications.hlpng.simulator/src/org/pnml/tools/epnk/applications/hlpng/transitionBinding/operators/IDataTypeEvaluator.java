package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public interface IDataTypeEvaluator
{
	public AbstractValue evaluate(Sort sort);
}
