package org.pnml.tools.epnk.applications.hlpng.runtime.operators;

import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.firing.AbstractUndefinedVariable;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public abstract class AbstractReversibleOperation extends AbstractUndefinedVariable
	implements IEvaluator, IReversibleOperation
{
	protected Term rootTerm = null;

	@Override
	public List<Term> getArguments()
	{
		return ((Operator)rootTerm).getSubterm();
	}
	
	@Override
	public AbstractValue revert(AbstractValue result, Collection<AbstractValue> args)
	{
		return revert(result, evaluate(args, ((Operator)rootTerm)));
	}
	
	public Term getRootTerm()
    {
    	return rootTerm;
    }

	public void setRootTerm(Term rootTerm)
    {
    	this.rootTerm = rootTerm;
    }
}
