package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AbstractReversibleOperation;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AdditionEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IReversibleOperation;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.MultiplicationEval;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.AdditionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.MultiplicationImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.UserOperatorImpl;

public class ReversibleOperationManager
{
	private static ReversibleOperationManager reversibleOperationManager = null;
	
	private Map<Class, IReversibleOperation> handlers = null;
	
	private ReversibleOperationManager()
	{
		handlers = new HashMap<Class, IReversibleOperation>();

		handlers.put(UserOperatorImpl.class, null);
		
		handlers.put(AdditionImpl.class, new AdditionEval());
		handlers.put(MultiplicationImpl.class, new MultiplicationEval());
	}
	
	public static ReversibleOperationManager getInstance()
	{
		if(reversibleOperationManager == null)
		{
			reversibleOperationManager = new ReversibleOperationManager();
		}
		return reversibleOperationManager;
	}
	
	public void register(Class targetClass, IReversibleOperation operator)
	{
		handlers.put(targetClass, operator);
	}
	
	public void unregister(Class targetClass)
	{
		handlers.remove(targetClass);
	}

	public AbstractReversibleOperation createHandler(Class c)
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
	
	private boolean resolve(AbstractValue result, IReversibleOperation operation,
			Map<String, VariableEvaluation> knownVariables)
	{
		List<Term> unknown = new ArrayList<Term>();
		List<Set<AbstractValue>> known = new ArrayList<Set<AbstractValue>>();
		
		for(Term arg : operation.getArguments())
		{
			try
            {
				Set<AbstractValue> value = EvaluationManager.getInstance().evaluateAll(arg, knownVariables);
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
		
		IReversibleOperation op = createHandler(unknown.get(0).getClass());
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
