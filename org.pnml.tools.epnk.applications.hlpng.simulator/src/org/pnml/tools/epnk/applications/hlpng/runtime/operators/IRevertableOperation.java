package org.pnml.tools.epnk.applications.hlpng.runtime.operators;

import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public interface IRevertableOperation
{
	public Term getRootTerm();
	public void setRootTerm(Term rootTerm);
	public List<Term> getArguments();
	public AbstractValue revert(AbstractValue result, Collection<AbstractValue> args);
	public AbstractValue revert(AbstractValue result, AbstractValue arg);
}
