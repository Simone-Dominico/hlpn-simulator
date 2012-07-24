package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.And;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleanConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleansFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.Equality;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.Inequality;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.Or;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class BooleansEval implements IEvaluator
{

	@Override
	public ITermWrapper evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, IValue> assignments) throws UnknownVariableException
	{
		Pair<Boolean, ITermWrapper> p = evaluationManager.evalSubterms(term, assignments);
		if(!p.getKey())
		{
			return p.getValue();
		}
			
		final List<ITermWrapper> values = p.getValue().getSubterms();
		if(term instanceof Or)
		{
			if(values.size() < 2)
			{
				throw new RuntimeException("Wrong number of arguments!");
			}
				
			BooleanValue result = new BooleanValue();
			result.setSort(BooleansFactory.eINSTANCE.createBool());
			result.setValue(false);

			for(int i = 0; i < values.size() && !result.getValue(); i++)
			{
				ITermWrapper value = values.get(i);
				if(((BooleanValue)value).getValue())
				{
					result.setValue(true);
				}
			}
			
			return result;
		}
		if(term instanceof And)
		{
			if(values.size() < 2)
			{
				throw new RuntimeException("Wrong number of arguments!");
			}
				
			BooleanValue result = new BooleanValue();
			result.setSort(BooleansFactory.eINSTANCE.createBool());
			result.setValue(true);

			for(int i = 0; i < values.size() && result.getValue(); i++)
			{
				ITermWrapper value = values.get(i);
				if(!((BooleanValue)value).getValue())
				{
					result.setValue(false);
				}
			}
			
			return result;
		}
		if(term instanceof Inequality)
		{
			if(values.size() != 2)
			{
				throw new RuntimeException("Wrong number of arguments!");
			}
				
			BooleanValue result = new BooleanValue();
			result.setSort(BooleansFactory.eINSTANCE.createBool());
			
			if(!values.get(0).equals(values.get(1)))
			{
				result.setValue(true);
			}
			else
			{
				result.setValue(false);
			}
			
			return result;
		}
		if(term instanceof Equality)
		{
			if(values.size() != 2)
			{
				throw new RuntimeException("Wrong number of arguments!");
			}

			BooleanValue result = new BooleanValue();
			result.setSort(BooleansFactory.eINSTANCE.createBool());
			
			if(values.get(0) != null && values.get(1) != null && 
					values.get(0).equals(values.get(1)))
			{
				result.setValue(true);
			}
			else
			{
				result.setValue(false);
			}
			
			return result;
		}
		if(term instanceof BooleanConstant)
		{
			if(values.size() != 0)
			{
				throw new RuntimeException();
			}
			
			BooleanValue value = new BooleanValue();
			value.setSort(term.getSort());
			value.setValue(((BooleanConstant)term).isValue());
			
			return value;
		}
		return null;
	}

	@Override
    public String validate(Object term)
    {
		if(term instanceof Or)
		{
			return null;
		}
		if(term instanceof And)
		{
			return null;
		}
		if(term instanceof Inequality)
		{
			return null;
		}
		if(term instanceof Equality)
		{
			return null;
		}
		if(term instanceof BooleanConstant)
		{
			return null;
		}
	    return term.getClass().toString();
    }
}
