package org.pnml.tools.epnk.applications.hlpng.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeVariable;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class VariableHandler extends AbstractTermHandler
{

	public VariableHandler(TermManager termManager, AbstractTermHandler next)
    {
	    super(termManager, next);
    }

	@Override
	public AbstractValue handle(Term term)
	{
		if(term instanceof Variable)
		{
			Variable var = (Variable) term;
			
			RuntimeVariable rVariable = new RuntimeVariable();
			rVariable.setSort(var.getSort());
			rVariable.setVariable(var);
			
			return rVariable;
		}
		return next.handle(term);
	}

}
