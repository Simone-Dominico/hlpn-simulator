package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public interface IReversibleOperation extends IEvaluator
{
	public Term getRootTerm();
	public void setRootTerm(Term rootTerm);
	public List<Term> getArguments();
	public AbstractValue reverseAll(AbstractValue result, Collection<AbstractValue> args);
	public AbstractValue reverse(AbstractValue result, AbstractValue arg);
}
