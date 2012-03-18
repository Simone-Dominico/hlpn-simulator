package org.pnml.tools.epnk.applications.hlpng.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;

public interface IComparator
{
	public boolean compare(ComparatorManager manager,
            AbstractValue refValue, AbstractValue testValue,
			Map<String, VariableEvaluation> assignments);
}
