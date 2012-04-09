package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleansFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class AndEval implements IEvaluator
{
	@Override
	public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
	{
		if(values.size() < 2)
		{
			throw new RuntimeException("Wrong number of arguments!");
		}
			
		BooleanValue result = new BooleanValue();
		result.setSort(BooleansFactory.eINSTANCE.createBool());
		result.setValue(true);
		
		List<AbstractValue> list = new ArrayList<AbstractValue>(values);
		
		for(int i = 0; i < list.size() && result.getValue(); i++)
		{
			AbstractValue value = list.get(i);
			if(!((BooleanValue)value).getValue())
			{
				result.setValue(false);
			}
		}
		
		return result;
	}
}
