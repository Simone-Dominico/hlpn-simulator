package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public interface IReversibleOperation extends IEvaluator
{
	public Term getRootTerm();
	public void setRootTerm(Term rootTerm);
	public IValue reverseAll(IValue result, 
			List<IValue> args, Boolean firstArgumentIsKnown);
}
