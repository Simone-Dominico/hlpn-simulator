package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.VariableWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class VariableEval implements IEvaluator
{
	@Override
	public ITermWrapper evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, IValue> assignments) throws UnknownVariableException
	{
		VariableWrapper wrapper = new VariableWrapper();
		wrapper.setRootTerm(term);
		wrapper.setVariable((Variable)term);

		if(assignments != null)
		{
			IValue value = assignments.get(wrapper);
			if(value == null)
			{
				throw new UnknownVariableException("Unknown variable: " + wrapper.getVariable().getName());
			}	
			return value;
		}

		return wrapper;
	}

	@Override
    public String validate(Object term)
    {
	    return null;
    }
}
