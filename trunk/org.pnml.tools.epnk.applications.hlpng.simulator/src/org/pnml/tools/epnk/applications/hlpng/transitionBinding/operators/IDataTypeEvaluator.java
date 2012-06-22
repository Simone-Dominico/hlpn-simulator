package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public interface IDataTypeEvaluator extends IValidator
{
	public IValue evaluate(Sort sort);
}
