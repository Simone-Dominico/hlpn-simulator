package org.pnml.tools.epnk.applications.hlpng.firing.evaluators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public interface IEvaluator
{
	public boolean compare(EvaluationManager manager,
            Term refValue, AbstractValue testValue,
			Map<String, VariableEvaluation> assignments);
}
