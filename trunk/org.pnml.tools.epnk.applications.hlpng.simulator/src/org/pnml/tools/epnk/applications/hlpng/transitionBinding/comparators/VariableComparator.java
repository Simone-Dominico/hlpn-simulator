package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.VariableWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class VariableComparator implements IComparable
{
	@Override
	public boolean compare(Term refValue, AbstractValue testValue,
            Map<TermWrapper, TermAssignment> assignments)
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
			VariableWrapper rv = new VariableWrapper();
			rv.setRootTerm(var);
			rv.setVariable(var);
			
			TermAssignment ve = new TermAssignment();
			ve.getValues().add(testValue);
			ve.setVariable(rv);

			assignments.put(rv, ve);
		}

		return true;
	}

}
