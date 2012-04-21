package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.RuntimeVariable;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class VariableComparator implements IComparable
{
	@Override
	public boolean compare(Term refValue, AbstractValue testValue,
            Map<String, TermAssignment> assignments)
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
			
			TermAssignment ve = new TermAssignment();
			ve.getValues().add(testValue);
			ve.setVariable(rv);

			assignments.put(var.getName(), ve);
		}

		return true;
	}

}
