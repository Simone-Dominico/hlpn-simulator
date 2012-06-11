package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.DotValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.dots.DotConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.dots.DotsFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class DotsEval implements IEvaluator
{
	@Override
	public AbstractValue evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, AbstractValue> assignments) throws UnknownVariableException
	{
		if(term instanceof DotConstant)
		{
			DotValue dot = new DotValue();
			dot.setSort(DotsFactory.eINSTANCE.createDot());
			
		    return dot;
		}
		return null;
    }

	@Override
    public String validate(Object term)
    {
		if(term instanceof DotConstant)
		{
		    return null;
		}
		return term.getClass().toString();
    }
}
