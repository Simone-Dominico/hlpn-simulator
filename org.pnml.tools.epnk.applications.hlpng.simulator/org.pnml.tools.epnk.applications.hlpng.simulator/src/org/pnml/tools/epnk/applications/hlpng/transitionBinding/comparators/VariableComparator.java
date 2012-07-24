package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.VariableWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class VariableComparator implements IComparable
{
	@Override
	public boolean compare(Object refValue, Object testValue,
            Map<TermWrapper, TermAssignment> assignments)
	{
		if(refValue instanceof VariableWrapper)
		{
			updateAssignments(assignments, (VariableWrapper)refValue, (IValue)testValue);
			
			return true;
		}
		
		if(!(refValue instanceof Variable))
		{
			throw new RuntimeException("Wrong comparator: a reference value is" +
					" not an instance of " + Variable.class);
		}
		
		Variable var = (Variable)refValue;
		VariableWrapper rv = new VariableWrapper();
		rv.setRootTerm(var);
		rv.setVariable(var);
		
		updateAssignments(assignments, rv, (IValue) testValue);

		return true;
	}
	
	private static void updateAssignments(Map<TermWrapper, TermAssignment> assignments,
			VariableWrapper wrapper, IValue value)
	{
		if(assignments.containsKey(wrapper))
		{
			assignments.get(wrapper).getValues().add(value);
		}
		else
		{
			TermAssignment ve = new TermAssignment();
			ve.getValues().add(value);
			ve.setTermWrapper(wrapper);

			assignments.put(wrapper, ve);
		}
	}

}
