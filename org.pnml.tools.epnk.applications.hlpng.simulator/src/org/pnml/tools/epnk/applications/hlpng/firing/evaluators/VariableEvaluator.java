package org.pnml.tools.epnk.applications.hlpng.firing.evaluators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.firing.operators.RuntimeVariable;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class VariableEvaluator implements IEvaluator
{
	@Override
	public boolean compare(EvaluationManager manager,
            Term refValue, AbstractValue testValue,
            Map<String, VariableEvaluation> assignments)
	{
		if(!(refValue instanceof Variable))
		{
			throw new RuntimeException("Wrong comparator: a reference value is" +
					" not an instance of " + Variable.class);
		}
		
		Variable var = (Variable)refValue;
		
		if(assignments.containsKey(var.getName()))
		{
			assignments.get(var.getName()).registerAssignment(testValue);
		}
		else
		{
			VariableEvaluation ve = new VariableEvaluation();
			RuntimeVariable rVariable = new RuntimeVariable();
			rVariable.setSort(var.getSort());
			rVariable.setVariable(var);
			ve.setVariable(rVariable);
			ve.registerAssignment(testValue);

			assignments.put(var.getName(), ve);
		}

		return true;
	}

}
