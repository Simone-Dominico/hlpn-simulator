package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.Collection;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public interface IEvaluator
{
	public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator);
}
