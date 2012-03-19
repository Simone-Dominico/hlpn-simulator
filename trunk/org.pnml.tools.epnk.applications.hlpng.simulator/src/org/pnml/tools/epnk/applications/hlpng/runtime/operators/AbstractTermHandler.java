package org.pnml.tools.epnk.applications.hlpng.runtime.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public abstract class AbstractTermHandler
{
	protected TermManager termManager = null;
	protected AbstractTermHandler next = null;
	
	public AbstractTermHandler(TermManager termManager, AbstractTermHandler next)
	{
		this.termManager = termManager;
		this.next = next;
	}
	
	public abstract AbstractValue handle(Term term);
}
