package org.pnml.tools.epnk.applications.hlpng.operations;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

import runtime.AbstractValue;
import variableruntime.RuntimeVariable;
import variableruntime.VariableruntimeFactory;

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
			
			RuntimeVariable rVariable = VariableruntimeFactory.eINSTANCE.createRuntimeVariable();
			rVariable.setSort(var.getSort());
			rVariable.setVariable(var);
			
			return rVariable;
		}
		return next.handle(term);
	}

}
