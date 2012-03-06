package org.pnml.tools.epnk.applications.hlpng.operations;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

import runtime.AbstractValue;

public class DefaultOperator extends AbstractOperator
{

	public DefaultOperator(TermManager termManager, AbstractOperator next)
	{
		super(termManager, next);
	}

	@Override
	public AbstractValue handle(Term term)
	{
		throw new RuntimeException("Mismatch: " + term);
	}

}
