package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleansFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class LessThanEval implements IEvaluator
{
	@Override
	public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
	{
		if(values.size() != 2)
		{
			throw new RuntimeException("Wrong number of arguments!");
		}
			
		List<AbstractValue> list = new ArrayList<AbstractValue>(values);
		
		BooleanValue result = new BooleanValue();
		result.setSort(BooleansFactory.eINSTANCE.createBool());
		
		if(((NumberValue)list.get(0)).getN() < ((NumberValue)list.get(1)).getN())
		{
			result.setValue(true);
		}
		else
		{
			result.setValue(false);
		}
		
		return result;
	}
}
