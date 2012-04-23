package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IntValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NatValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleansFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.GreaterThan;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.LessThan;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Natural;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Positive;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class IntegersEval implements IEvaluator
{
	@Override
	public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
	{
		if(operator instanceof NumberConstant)
		{
			if(values.size() != 0)
			{
				throw new RuntimeException();
			}
			
			NumberValue v = null;
			if(operator.getSort() instanceof Positive)
			{
				v = new PosValue();
			}
			else if(operator.getSort() instanceof Natural)
			{
				v = new NatValue();
			}
			else
			{
				v = new IntValue();
			}

			if(v != null)
			{
				v.setSort(operator.getSort());
				v.setN(((NumberConstant)operator).getValue());
			}
			return v;
		}
		if(operator instanceof LessThan)
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
		if(operator instanceof GreaterThan)
		{
			if(values.size() != 2)
			{
				throw new RuntimeException("Wrong number of arguments!");
			}
				
			List<AbstractValue> list = new ArrayList<AbstractValue>(values);
			
			BooleanValue result = new BooleanValue();
			result.setSort(BooleansFactory.eINSTANCE.createBool());
			
			if(((NumberValue)list.get(0)).getN() > ((NumberValue)list.get(1)).getN())
			{
				result.setValue(true);
			}
			else
			{
				result.setValue(false);
			}
			
			return result;
		}
		
		return null;
	}

}
