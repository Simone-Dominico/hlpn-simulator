package org.pnml.tools.epnk.applications.hlpng.operations;

import java.util.Map;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

import runtime.AbstractValue;

public abstract class AbstractOperator
{
	protected Map<Class, AbstractOperator> handlers = null;
	
	public AbstractOperator(Map<Class, AbstractOperator> handlers)
	{
		this.handlers = handlers;
	}
	
	public abstract AbstractValue handle(Term term);
}
