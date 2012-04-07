package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.RuntimeVariable;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.AdditionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.MultiplicationImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.AddImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.impl.StringConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.TupleImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.UserOperatorImpl;

public class EvaluationManager
{
	private static EvaluationManager termManager = null;
	
	private Map<Class, IEvaluator> handlers = null;
	
	private EvaluationManager()
	{
		handlers = new HashMap<Class, IEvaluator>();
		
		handlers.put(NumberConstantImpl.class, new NumberConstantEval());
		handlers.put(StringConstantImpl.class, new StringConstantEval());
		handlers.put(NumberOfImpl.class, new NumberOfEval());
		handlers.put(AddImpl.class, new AddEval());
		handlers.put(TupleImpl.class, new TupleEval());
		
		handlers.put(UserOperatorImpl.class, null);
		
		handlers.put(AdditionImpl.class, new AdditionEval());
		handlers.put(MultiplicationImpl.class, new MultiplicationEval());
	}
	
	public static EvaluationManager getInstance()
	{
		if(termManager == null)
		{
			termManager = new EvaluationManager();
		}
		return termManager;
	}
	
	public void register(Class targetClass, IEvaluator operator)
	{
		handlers.put(targetClass, operator);
	}
	
	public void unregister(Class targetClass)
	{
		handlers.remove(targetClass);
	}
	
	public AbstractReversibleOperation createReversibleOperationHandler(Class c)
	{
		try
        {
			Object obj = handlers.get(c).getClass().newInstance();
	        return (AbstractReversibleOperation)obj;
        }
        catch(Exception e)
        {
        	System.err.println("Could not create a handler for: " + c);
        	e.printStackTrace();
        }
		return null;
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

		IEvaluator evaluator = handlers.get(term.getClass());
		
		return evaluator.evaluate(values, op);
	}
	
	public Set<AbstractValue> evaluateAll(Term term, Map<String, VariableEvaluation> assignments) throws UnknownVariableException
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
			
			for(VariableEvaluation ve : assignments.values())
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
	
	private boolean resolve(AbstractValue result, IReversibleOperation operation,
			Map<String, VariableEvaluation> knownVariables)
	{
		List<Term> unknown = new ArrayList<Term>();
		List<Set<AbstractValue>> known = new ArrayList<Set<AbstractValue>>();
		
		for(Term arg : operation.getArguments())
		{
			try
            {
				Set<AbstractValue> value = evaluateAll(arg, knownVariables);
				known.add(value);
            }
            catch(Exception e)
            {
	            unknown.add(arg);
            }
		}

		if(unknown.size() == 0)
		{
			return true;
		}
		if(unknown.size() > 1)
		{
			return false;
		}
		
		List<List<AbstractValue>> setsOfResults = null;
		{
			List<List<AbstractValue>> list = new ArrayList<List<AbstractValue>>();
			for(Set<AbstractValue> set : known)
			{
				list.add(new ArrayList<AbstractValue>(set));
			}
			CartesianProduct<AbstractValue> product = new CartesianProduct<AbstractValue>();
			setsOfResults = product.product(list);
		}
		
		if(unknown.size() == 1 && unknown.get(0) instanceof Variable)
		{
			for(List<AbstractValue> args : setsOfResults)
			{
				Variable var = (Variable)unknown.get(0);
				String varName = var.getName();
				AbstractValue value = operation.revert(result, args);
				if(knownVariables.containsKey(varName))
				{
					knownVariables.get(varName).getValues().add(value);
				}
				else
				{
					RuntimeVariable rv = new RuntimeVariable();
					rv.setSort(var.getSort());
					rv.setVariable(var);
					
					VariableEvaluation ve = new VariableEvaluation();
					ve.getValues().add(value);
					ve.setVariable(rv);
					
					knownVariables.put(varName, ve);
				}
			}
			return true;
		}
		
		IReversibleOperation op = createReversibleOperationHandler(unknown.get(0).getClass());
		op.setRootTerm(unknown.get(0));
		
		List<AbstractValue> resultList = new ArrayList<AbstractValue>();
		for(List<AbstractValue> args : setsOfResults)
		{
			resultList.add(operation.revert(result, args));
		}
		return resolveAll(resultList, op, knownVariables);
	}
	
	public boolean resolveAll(Collection<AbstractValue> result, IReversibleOperation operation,
			Map<String, VariableEvaluation> knownVariables)
	{
		List<Map<String, VariableEvaluation>> copies = new ArrayList<Map<String,VariableEvaluation>>();
		for(AbstractValue value : result)
		{
			Map<String, VariableEvaluation> copy = copyMap(knownVariables);
			copies.add(copy);
			
			if(!resolve(value, operation, copy))
			{
				return false;
			}
		}
		for(Map<String, VariableEvaluation> map : copies)
		{
			mergeMap(knownVariables, map);
		}
		return true;
	}
	
	private static Map<String, VariableEvaluation> copyMap(Map<String, VariableEvaluation> map)
	{
		Map<String, VariableEvaluation> copy = new HashMap<String, VariableEvaluation>();
		
		for(String key : map.keySet())
		{
			VariableEvaluation ve = map.get(key);
			VariableEvaluation copyVe = new VariableEvaluation();
			copyVe.setVariable(ve.getVariable());
			copyVe.getValues().addAll(ve.getValues());
			
			copy.put(ve.getVariableName(), copyVe);
		}
		
		return copy;
	}
	
	private static void mergeMap(Map<String, VariableEvaluation> main,
			Map<String, VariableEvaluation> map2)
	{
		for(String key : map2.keySet())
		{
			if(main.containsKey(key))
			{
				main.get(key).getValues().addAll(map2.get(key).getValues());
			}
			else
			{
				VariableEvaluation ve = map2.get(key);
				VariableEvaluation copyVe = new VariableEvaluation();
				copyVe.setVariable(ve.getVariable());
				copyVe.getValues().addAll(ve.getValues());
				
				main.put(ve.getVariableName(), copyVe);		
			}
		}
	}
}
