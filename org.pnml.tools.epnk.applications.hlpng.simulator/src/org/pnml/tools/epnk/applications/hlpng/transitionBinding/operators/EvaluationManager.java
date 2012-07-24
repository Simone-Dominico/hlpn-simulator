package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class EvaluationManager implements IEvaluator
{
	private Map<Object, IEvaluator> handlers = new HashMap<Object, IEvaluator>();
	
	public void register(Object targetObject, IEvaluator operator)
	{
		handlers.put(targetObject, operator);
	}
	
	public void unregister(Object targetObject)
	{
		handlers.remove(targetObject);
	}
	
	public ITermWrapper evaluate(Term term, Map<TermWrapper, IValue> assignments) throws UnknownVariableException
	{
		IEvaluator evaluator = getHandler(term.getClass());
		
		return evaluator.evaluate(term, this, assignments);
	}
	
	public Set<IValue> evaluateAll(Term term, Map<TermWrapper, TermAssignment> assignments) throws UnknownVariableException
	{
		if(assignments == null || assignments.size() == 0)
		{
			Set<IValue> result = new HashSet<IValue>();
			ITermWrapper value = evaluate(term, null);
			if(!(value instanceof IValue))
			{
				throw new UnknownVariableException("At least one of the variables is not known!");
			}
			result.add((IValue)value);
			return result;
		}
		// only one variable
		if(assignments.size() == 1)
		{
			Set<IValue> result = new HashSet<IValue>();
			
			for(TermAssignment ve : assignments.values())
			{
				for(IValue value : ve.getValues())
				{
					// evaluate with each possible result
					Map<TermWrapper, IValue> map = new HashMap<TermWrapper, IValue>();
					map.put(ve.getTermWrapper(), value);
					
					ITermWrapper v = evaluate(term, map);
					if(!(value instanceof IValue))
					{
						throw new UnknownVariableException("At least one of the variables is not known!");
					}
					result.add((IValue)v);
				}
			}
			return result;
		}
		
		List<List<Pair<TermWrapper, IValue>>> mainList = new ArrayList<List<Pair<TermWrapper,IValue>>>();
		
		for(TermWrapper wrapper : assignments.keySet())
		{
			List<Pair<TermWrapper, IValue>> subList = new ArrayList<Pair<TermWrapper,IValue>>();
			for(IValue value : assignments.get(wrapper).getValues())
			{
				Pair<TermWrapper, IValue> p = new Pair<TermWrapper, IValue>();
				p.setKey(wrapper);
				p.setValue(value);
				
				subList.add(p);
			}
			mainList.add(subList);
		}
		
		CartesianProduct<Pair<TermWrapper, IValue>> product = new CartesianProduct<Pair<TermWrapper,IValue>>();
		List<List<Pair<TermWrapper, IValue>>> prodList = product.product(mainList);
		
		Set<IValue> result = new HashSet<IValue>();
		for(List<Pair<TermWrapper, IValue>> subSet : prodList)
		{
			Map<TermWrapper, IValue> assignmentSet = new HashMap<TermWrapper, IValue>();
			for(Pair<TermWrapper, IValue> p : subSet)
			{
				assignmentSet.put(p.getKey(), p.getValue());
			}
			ITermWrapper value = evaluate(term, assignmentSet);
			if(!(value instanceof IValue))
			{
				throw new UnknownVariableException("At least one of the variables is not known!");
			}
			result.add((IValue)value);
		}
		return result;
	}
	
	public IEvaluator getHandler(Class<? extends Term> targetClass)
	{
		if(handlers.containsKey(targetClass))
		{
			return handlers.get(targetClass);
		}
		if(handlers.containsKey(targetClass.getPackage()))
		{
			return handlers.get(targetClass.getPackage());
		}
		return null;
	}
	
	@Override
    public String validate(Object obj)
    {
		if(obj instanceof Term)
		{
			Term term = (Term) obj;
			IValidator validator = getHandler(term.getClass());

			if(validator == null)
			{
				return "The term " + term.getClass().toString() + " is not supported!";
			}
			
			String error = validator.validate(term);
			if(error != null)
			{
				return error;
			}
			
			if(term instanceof Operator)
			{
				Operator operator = (Operator)term;
				
				for(Term subTerm : operator.getSubterm())
				{
					String err = validate(subTerm);
					if(err != null)
					{
						return err;
					}
				}
			}	
		}
		
		return null;
    }

	@Override
    public ITermWrapper evaluate(Term term, EvaluationManager evaluationManager,
            Map<TermWrapper, IValue> assignments)
            throws UnknownVariableException
    {
		IEvaluator evaluator = getHandler(term.getClass());
		if(evaluator != null)
		{
			return evaluator.evaluate(term, evaluationManager, assignments);
		}
	    throw new RuntimeException("Do not know how to evaluate: " + term.getClass());
    }
	
	public Pair<Boolean, ITermWrapper> evalSubterms(Term term, 
			Map<TermWrapper, IValue> assignments) throws UnknownVariableException
	{
		TermWrapper wrapper = new TermWrapper();
		wrapper.setRootTerm(term);
		
		boolean complete = true;
		Operator operator = (Operator) term;
		for(Term subterm : operator.getSubterm())
		{
			ITermWrapper value = evaluate(subterm, this, assignments);
			wrapper.getSubterms().add(value);
			if(!(value instanceof IValue))
			{
				complete = false;
			}
		}
		
		return new Pair<Boolean, ITermWrapper>(complete, wrapper);
	}
}
