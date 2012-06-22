package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class EvaluationManager
{
	private Map<Object, IEvaluator> handlers = new HashMap<Object, IEvaluator>();
	
	public void register(Object targetObject, IEvaluator operator)
	{
		handlers.put(targetObject, operator);
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
	
	public void unregister(Object targetObject)
	{
		handlers.remove(targetObject);
	}
	
	public String check(Term term)
	{
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
				String err = check(subTerm);
				if(err != null)
				{
					return err;
				}
			}
		}
		
		return null;
	}
	
	public IValue evaluate(Term term, Map<TermWrapper, IValue> assignments) throws UnknownVariableException
	{
		IEvaluator evaluator = getHandler(term.getClass());
		
		return evaluator.evaluate(term, this, assignments);
	}
	
	public Set<IValue> evaluateAll(Term term, Map<TermWrapper, TermAssignment> assignments) throws UnknownVariableException
	{
		if(assignments == null || assignments.size() == 0)
		{
			Set<IValue> result = new HashSet<IValue>();
			IValue value = evaluate(term, null);
			result.add(value);
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
					
					result.add(evaluate(term, map));
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
			IValue value = evaluate(term, assignmentSet);
			result.add(value);
		}
		return result;
	}
}
