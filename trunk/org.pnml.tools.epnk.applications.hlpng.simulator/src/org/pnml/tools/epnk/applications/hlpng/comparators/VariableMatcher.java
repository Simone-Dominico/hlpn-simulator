package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeVariable;

public class VariableMatcher implements IComparator
{
	@Override
	public boolean compare(ComparatorManager manager,
            AbstractValue refValue, AbstractValue testValue,
            Map<String, VariableEvaluation> assignments)
	{
		if(!(refValue instanceof RuntimeVariable))
		{
			throw new RuntimeException("Wrong comparator: a reference value is" +
					" not an instance of " + RuntimeVariable.class);
		}
		
		RuntimeVariable var = (RuntimeVariable)refValue;
		
		if(assignments.containsKey(var.getVariable().getName()))
		{
			assignments.get(var.getVariable().getName()).registerAssignment(testValue);
		}
		else
		{
			VariableEvaluation ve = new VariableEvaluation();
			ve.setVariable(var);
			ve.registerAssignment(testValue);

			assignments.put(var.getVariable().getName(), ve);
		}

		return true;
	}

}
