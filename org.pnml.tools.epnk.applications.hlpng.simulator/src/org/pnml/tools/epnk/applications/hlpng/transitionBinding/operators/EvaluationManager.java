package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
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
	
	public IEvaluator getHandler(Class targetClass)
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
	
	public AbstractValue evaluate(Term term, Map<TermWrapper, AbstractValue> assignments) throws UnknownVariableException
	{
		IEvaluator evaluator = getHandler(term.getClass());
		
		return evaluator.evaluate(term, this, assignments);
	}
	
	public Set<AbstractValue> evaluateAll(Term term, Map<TermWrapper, TermAssignment> assignments) throws UnknownVariableException
	{
		if(assignments == null || assignments.size() == 0)
		{
			Set<AbstractValue> result = new HashSet<AbstractValue>();
			AbstractValue value = evaluate(term, null);
			result.add(value);
			return result;
		}
		// only one variable
		if(assignments.size() == 1)
		{
			Set<AbstractValue> result = new HashSet<AbstractValue>();
			
			for(TermAssignment ve : assignments.values())
			{
				for(AbstractValue value : ve.getValues())
				{
					// evaluate with each possible result
					Map<TermWrapper, AbstractValue> map = new HashMap<TermWrapper, AbstractValue>();
					map.put(ve.getTermWrapper(), value);
					
					result.add(evaluate(term, map));
				}
			}
			return result;
		}
		
		List<List<Pair<TermWrapper, AbstractValue>>> mainList = new ArrayList<List<Pair<TermWrapper,AbstractValue>>>();
		
		for(TermWrapper wrapper : assignments.keySet())
		{
			List<Pair<TermWrapper, AbstractValue>> subList = new ArrayList<Pair<TermWrapper,AbstractValue>>();
			for(AbstractValue value : assignments.get(wrapper).getValues())
			{
				Pair<TermWrapper, AbstractValue> p = new Pair<TermWrapper, AbstractValue>();
				p.setKey(wrapper);
				p.setValue(value);
				
				subList.add(p);
			}
			mainList.add(subList);
		}
		
		CartesianProduct<Pair<TermWrapper, AbstractValue>> product = new CartesianProduct<Pair<TermWrapper,AbstractValue>>();
		List<List<Pair<TermWrapper, AbstractValue>>> prodList = product.product(mainList);
		
		Set<AbstractValue> result = new HashSet<AbstractValue>();
		for(List<Pair<TermWrapper, AbstractValue>> subSet : prodList)
		{
			Map<TermWrapper, AbstractValue> assignmentSet = new HashMap<TermWrapper, AbstractValue>();
			for(Pair<TermWrapper, AbstractValue> p : subSet)
			{
				assignmentSet.put(p.getKey(), p.getValue());
			}
			AbstractValue value = evaluate(term, assignmentSet);
			result.add(value);
		}
		return result;
	}
}
