package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;

public interface IComparable
{
	public boolean compare(Object refValue, Object testValue,
			Map<TermWrapper, TermAssignment> assignments);
}
