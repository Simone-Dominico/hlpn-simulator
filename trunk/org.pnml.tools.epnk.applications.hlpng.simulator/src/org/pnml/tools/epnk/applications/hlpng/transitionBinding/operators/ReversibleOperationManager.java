package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class ReversibleOperationManager
{
	private EvaluationManager evaluationManager = null;
	private Map<Class, IReversibleOperation> handlers = null;
	
	public ReversibleOperationManager(EvaluationManager evaluationManager)
	{
		this.evaluationManager = evaluationManager;
		this.handlers = new HashMap<Class, IReversibleOperation>();
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
	
	public boolean resolve(AbstractValue result, IReversibleOperation operation,
			Map<String, TermAssignment> knownVariables)
	{
		List<Term> unknown = new ArrayList<Term>();
		List<Set<AbstractValue>> known = new ArrayList<Set<AbstractValue>>();
		
		for(Term arg : operation.getArguments())
		{
			try
            {
				Set<AbstractValue> value = evaluationManager.evaluateAll(arg, knownVariables);
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
				AbstractValue value = operation.reverseAll(result, args);
				if(knownVariables.containsKey(varName))
				{
					knownVariables.get(varName).getValues().add(value);
				}
				else
				{
					TermWrapper rv = new TermWrapper();
					rv.setRootTerm(var);
					
					TermAssignment ve = new TermAssignment();
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
			resultList.add(operation.reverseAll(result, args));
		}
		return resolveAll(resultList, op, knownVariables);
	}
	
	public boolean resolveAll(Collection<AbstractValue> result, IReversibleOperation operation,
			Map<String, TermAssignment> knownVariables)
	{
		List<Map<String, TermAssignment>> copies = new ArrayList<Map<String,TermAssignment>>();
		for(AbstractValue value : result)
		{
			Map<String, TermAssignment> copy = copyMap(knownVariables);
			copies.add(copy);
			
			if(!resolve(value, operation, copy))
			{
				return false;
			}
		}
		for(Map<String, TermAssignment> map : copies)
		{
			mergeMap(knownVariables, map);
		}
		return true;
	}
	
	private static Map<String, TermAssignment> copyMap(Map<String, TermAssignment> map)
	{
		Map<String, TermAssignment> copy = new HashMap<String, TermAssignment>();
		
		for(String key : map.keySet())
		{
			TermAssignment ve = map.get(key);
			TermAssignment copyVe = new TermAssignment();
			copyVe.setVariable(ve.getVariable());
			copyVe.getValues().addAll(ve.getValues());
			
			copy.put(ve.getVariableName(), copyVe);
		}
		
		return copy;
	}
	
	private static void mergeMap(Map<String, TermAssignment> main,
			Map<String, TermAssignment> map2)
	{
		for(String key : map2.keySet())
		{
			if(main.containsKey(key))
			{
				main.get(key).getValues().addAll(map2.get(key).getValues());
			}
			else
			{
				TermAssignment ve = map2.get(key);
				TermAssignment copyVe = new TermAssignment();
				copyVe.setVariable(ve.getVariable());
				copyVe.getValues().addAll(ve.getValues());
				
				main.put(ve.getVariableName(), copyVe);		
			}
		}
	}
}
