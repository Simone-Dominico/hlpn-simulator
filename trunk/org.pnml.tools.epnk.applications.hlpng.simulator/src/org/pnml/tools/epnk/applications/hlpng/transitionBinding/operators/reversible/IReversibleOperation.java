package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.reversible;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;

public interface IReversibleOperation extends IEvaluator
{
	public IValue reverseAll(IValue result, 
			List<IValue> args, Boolean firstArgumentIsKnown);
}
