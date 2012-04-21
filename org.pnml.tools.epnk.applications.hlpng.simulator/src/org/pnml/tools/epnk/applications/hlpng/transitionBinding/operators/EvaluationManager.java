package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

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
	
	public AbstractValue evaluateAdapt(Term term, Map<Variable, AbstractValue> assignments) throws UnknownVariableException
	{
		Map<String, AbstractValue> newAssignments = new HashMap<String, AbstractValue>();
		
		for(Variable key : assignments.keySet())
		{
			newAssignments.put(key.getName(), assignments.get(key));
		}
		
		return evaluate(term, newAssignments);
	}
	
	public AbstractValue evaluate(Term term, Map<String, AbstractValue> assignments) throws UnknownVariableException
	{
		if(term instanceof Variable)
		{
			Variable var = (Variable) term;
			
			if(assignments == null)
			{
				throw new UnknownVariableException("Unknown variable: " + var.getName());
			}
			AbstractValue value = assignments.get(var.getName());
			if(value == null)
			{
				throw new UnknownVariableException("Unknown variable: " + var.getName());
			}
			
			return value;
		}
		
		Operator op = (Operator) term;
		List<AbstractValue> values = new ArrayList<AbstractValue>();
		for(Term subterm : op.getSubterm())
		{
			AbstractValue value = evaluate(subterm, assignments);
			values.add(value);
		}

		IEvaluator evaluator = getHandler(term.getClass());
		
		if(evaluator == null)
		{
			System.out.println(term.getClass());
		}
		
		return evaluator.evaluate(values, op);
	}
	
	public Set<AbstractValue> evaluateAll(Term term, Map<String, TermAssignment> assignments) throws UnknownVariableException
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
					Map<String, AbstractValue> map = new HashMap<String, AbstractValue>();
					map.put(ve.getVariableName(), value);
					
					result.add(evaluate(term, map));
				}
			}
			return result;
		}
		
		List<List<Pair<String, AbstractValue>>> mainList = new ArrayList<List<Pair<String,AbstractValue>>>();
		
		for(String varName : assignments.keySet())
		{
			List<Pair<String, AbstractValue>> subList = new ArrayList<Pair<String,AbstractValue>>();
			for(AbstractValue value : assignments.get(varName).getValues())
			{
				Pair<String, AbstractValue> p = new Pair<String, AbstractValue>();
				p.setKey(varName);
				p.setValue(value);
				
				subList.add(p);
			}
			mainList.add(subList);
		}
		
		CartesianProduct<Pair<String, AbstractValue>> product = new CartesianProduct<Pair<String,AbstractValue>>();
		List<List<Pair<String, AbstractValue>>> prodList = product.product(mainList);
		
		Set<AbstractValue> result = new HashSet<AbstractValue>();
		for(List<Pair<String, AbstractValue>> subSet : prodList)
		{
			Map<String, AbstractValue> assignmentSet = new HashMap<String, AbstractValue>();
			for(Pair<String, AbstractValue> p : subSet)
			{
				assignmentSet.put(p.getKey(), p.getValue());
			}
			AbstractValue value = evaluate(term, assignmentSet);
			result.add(value);
		}
		return result;
	}
}
