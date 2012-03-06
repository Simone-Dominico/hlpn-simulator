package org.pnml.tools.epnk.applications.hlpng.operations;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

import runtime.AbstractValue;

public abstract class AbstractOperator
{
	protected TermManager termManager = null;
	protected AbstractOperator next = null;
	
	public AbstractOperator(TermManager termManager, AbstractOperator next)
	{
		this.termManager = termManager;
		this.next = next;
	}
	
	public abstract AbstractValue handle(Term term);
}
