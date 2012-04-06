package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.RuntimeVariable;
import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class VariableEvaluator implements IAssignable
{
	@Override
	public boolean compare(ResolutionManager manager,
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
			assignments.get(var.getName()).getValues().add(testValue);
		}
		else
		{
			RuntimeVariable rv = new RuntimeVariable();
			rv.setSort(var.getSort());
			rv.setVariable(var);
			
			VariableEvaluation ve = new VariableEvaluation();
			ve.getValues().add(testValue);
			ve.setVariable(rv);

			assignments.put(var.getName(), ve);
		}

		return true;
	}

}
