package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.And;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleanConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleansFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.Equality;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.Inequality;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.Or;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;

public class BooleansEval implements IEvaluator
{

	@Override
	public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
	{
		if(operator instanceof Or)
		{
			if(values.size() < 2)
			{
				throw new RuntimeException("Wrong number of arguments!");
			}
				
			BooleanValue result = new BooleanValue();
			result.setSort(BooleansFactory.eINSTANCE.createBool());
			result.setValue(false);
			
			List<AbstractValue> list = new ArrayList<AbstractValue>(values);
			
			for(int i = 0; i < list.size() && !result.getValue(); i++)
			{
				AbstractValue value = list.get(i);
				if(((BooleanValue)value).getValue())
				{
					result.setValue(true);
				}
			}
			
			return result;
		}
		if(operator instanceof And)
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
		if(operator instanceof Inequality)
		{
			if(values.size() != 2)
			{
				throw new RuntimeException("Wrong number of arguments!");
			}
				
			List<AbstractValue> list = new ArrayList<AbstractValue>(values);
			
			BooleanValue result = new BooleanValue();
			result.setSort(BooleansFactory.eINSTANCE.createBool());
			
			if(((NumberValue)list.get(0)).getN() != ((NumberValue)list.get(1)).getN())
			{
				result.setValue(true);
			}
			else
			{
				result.setValue(false);
			}
			
			return result;
		}
		if(operator instanceof Equality)
		{
			if(values.size() != 2)
			{
				throw new RuntimeException("Wrong number of arguments!");
			}
				
			List<AbstractValue> list = new ArrayList<AbstractValue>(values);
			
			BooleanValue result = new BooleanValue();
			result.setSort(BooleansFactory.eINSTANCE.createBool());
			
			if(((NumberValue)list.get(0)).getN() == ((NumberValue)list.get(1)).getN())
			{
				result.setValue(true);
			}
			else
			{
				result.setValue(false);
			}
			
			return result;
		}
		if(operator instanceof BooleanConstant)
		{
			if(values.size() != 0)
			{
				throw new RuntimeException();
			}
			
			BooleanValue value = new BooleanValue();
			value.setSort(operator.getSort());
			value.setValue(((BooleanConstant)operator).isValue());
			
			return value;
		}
		return null;
	}

}
