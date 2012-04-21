package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public interface IComparable
{
	public boolean compare(Term refValue, AbstractValue testValue,
			Map<String, TermAssignment> assignments);
}
