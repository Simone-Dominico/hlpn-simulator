package org.pnml.tools.epnk.applications.hlpng.operations;

import java.util.Map;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

import runtime.AbstractValue;

public abstract class AbstractOperator
{
	protected Map<Class, AbstractOperator> handlers = null;
	protected AbstractOperator next = null;
	
	public AbstractOperator(Map<Class, AbstractOperator> handlers, 
			AbstractOperator next)
	{
		this.handlers = handlers;
		this.next = next;
	}
	
	public abstract AbstractValue handle(Term term);
}
