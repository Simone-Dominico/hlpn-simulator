package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public interface IEvaluator extends IValidator
{
	public AbstractValue evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, AbstractValue> assignments) throws UnknownVariableException;
}
