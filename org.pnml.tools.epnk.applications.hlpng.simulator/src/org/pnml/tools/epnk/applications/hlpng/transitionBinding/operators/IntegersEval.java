package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IntValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NatValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleansFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.GreaterThan;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.LessThan;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Modulo;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Natural;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Positive;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class IntegersEval implements IEvaluator
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
		if(term instanceof NumberConstant)
		{
			if(values.size() != 0)
			{
				throw new RuntimeException();
			}
			
			NumberValue v = null;
			if(term.getSort() instanceof Positive)
			{
				v = new PosValue();
			}
			else if(term.getSort() instanceof Natural)
			{
				v = new NatValue();
			}
			else
			{
				v = new IntValue();
			}

			if(v != null)
			{
				v.setSort(term.getSort());
				v.setN(((NumberConstant)term).getValue());
			}
			return v;
		}
		if(term instanceof LessThan)
		{
			if(values.size() != 2)
			{
				throw new RuntimeException("Wrong number of arguments!");
			}

			BooleanValue result = new BooleanValue();
			result.setSort(BooleansFactory.eINSTANCE.createBool());
			
			if(((NumberValue)values.get(0)).getN() < ((NumberValue)values.get(1)).getN())
			{
				result.setValue(true);
			}
			else
			{
				result.setValue(false);
			}
			
			return result;
		}
		if(term instanceof GreaterThan)
		{
			if(values.size() != 2)
			{
				throw new RuntimeException("Wrong number of arguments!");
			}

			BooleanValue result = new BooleanValue();
			result.setSort(BooleansFactory.eINSTANCE.createBool());
			
			if(((NumberValue)values.get(0)).getN() > ((NumberValue)values.get(1)).getN())
			{
				result.setValue(true);
			}
			else
			{
				result.setValue(false);
			}
			
			return result;
		}
		if(term instanceof Modulo)
		{
			if(values.size() != 2)
			{
				throw new RuntimeException("Wrong number of arguments!");
			}
			
			NumberValue v = null;
			Operator operator = (Operator) term;
			if(operator.getOutputSort() instanceof Positive)
			{
				v = new PosValue();
			}
			else if(operator.getOutputSort() instanceof Natural)
			{
				v = new NatValue();
			}
			else
			{
				v = new IntValue();
			}

			v.setSort(operator.getOutputSort());
			v.setN(((NumberValue)values.get(0)).getN() % ((NumberValue)values.get(1)).getN());
			
			return v;
		}
		
		return null;
	}

	@Override
    public String validate(Object term)
    {
		if(term instanceof NumberConstant)
		{
			return null;
		}
		if(term instanceof LessThan)
		{
			return null;
		}
		if(term instanceof GreaterThan)
		{
			return null;
		}
		if(term instanceof Modulo)
		{
			return null;
		}
	    return term.getClass().toString();
    }

}
