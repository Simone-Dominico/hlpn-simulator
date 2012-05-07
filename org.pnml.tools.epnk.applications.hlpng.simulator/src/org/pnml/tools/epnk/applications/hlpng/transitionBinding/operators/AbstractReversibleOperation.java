package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public abstract class AbstractReversibleOperation extends TermWrapper
	implements IEvaluator, IReversibleOperation
{
	@Override
	public List<Term> getArguments()
	{
		return ((Operator)rootTerm).getSubterm();
	}
	
	@Override
	public AbstractValue reverseAll(AbstractValue result, Collection<AbstractValue> args)
	{
		return reverse(result, evaluate(args, ((Operator)rootTerm)));
	}
}
