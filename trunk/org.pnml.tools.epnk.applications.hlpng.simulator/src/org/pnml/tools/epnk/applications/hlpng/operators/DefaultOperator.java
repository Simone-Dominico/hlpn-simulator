package org.pnml.tools.epnk.applications.hlpng.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class DefaultOperator extends AbstractTermHandler
{

	public DefaultOperator(TermManager termManager, AbstractTermHandler next)
	{
		super(termManager, next);
	}

	@Override
	public AbstractValue handle(Term term)
	{
		throw new RuntimeException("Mismatch: " + term);
	}

}
