package org.pnml.tools.epnk.applications.hlpng.operations;

import java.util.Map;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

import runtime.AbstractValue;

public class DefaultOperator extends AbstractOperator
{

	public DefaultOperator(Map<Class, AbstractOperator> handlers,
	        AbstractOperator next)
	{
		super(handlers, next);
	}

	@Override
	public AbstractValue handle(Term term)
	{
		throw new RuntimeException("Mismatch: " + term);
	}

}
