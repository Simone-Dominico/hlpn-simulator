package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.VariableWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class VariableEval implements IEvaluator
{
	@Override
	public AbstractValue evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, AbstractValue> assignments) throws UnknownVariableException
	{
		VariableWrapper wrapper = new VariableWrapper();
		wrapper.setRootTerm(term);
		wrapper.setVariable((Variable)term);

		if(assignments == null)
		{
			throw new UnknownVariableException("Unknown variable: " + wrapper.getVariable().getName());
		}
		
		AbstractValue value = assignments.get(wrapper);
		if(value == null)
		{
			throw new UnknownVariableException("Unknown variable: " + wrapper.getVariable().getName());
		}
		
		return value;
	}
}
