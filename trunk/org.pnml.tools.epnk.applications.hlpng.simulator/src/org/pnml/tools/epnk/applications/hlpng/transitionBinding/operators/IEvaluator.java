package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public interface IEvaluator extends IValidator
{
	public ITermWrapper evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, IValue> assignments) throws UnknownVariableException;
}
