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
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.VariableWrapper;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
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
			Map<TermWrapper, TermAssignment> knownVariables)
	{
		Term unknownTerm = null;
		int numberOfUnknowns = 0;
		List<Boolean> termEval = new ArrayList<Boolean>();
		List<Set<AbstractValue>> known = new ArrayList<Set<AbstractValue>>();
		
		for(Term arg : ((Operator)operation.getRootTerm()).getSubterm())
		{
			try
            {
				//TODO mla
				Map<String, TermAssignment> strAssignments = new HashMap<String, TermAssignment>();
				for(TermWrapper wrapper : knownVariables.keySet())
				{
					strAssignments.put(wrapper.getName(), knownVariables.get(wrapper));
				}
				Set<AbstractValue> value = evaluationManager.evaluateAll(arg, strAssignments);
				known.add(value);
				termEval.add(true);
            }
            catch(Exception e)
            {
	            unknownTerm = arg;
	            numberOfUnknowns++;
	            termEval.add(false);
            }
		}

		if(numberOfUnknowns == 0)
		{
			return true;
		}
		if(numberOfUnknowns > 1)
		{
			return false;
		}
		
		// only 1 unknown argument
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
		
		if(unknownTerm instanceof Variable)
		{
			for(List<AbstractValue> args : setsOfResults)
			{
				Variable var = (Variable)unknownTerm;
				
				VariableWrapper rv = new VariableWrapper();
				rv.setRootTerm(var);
				rv.setVariable(var);
				
				AbstractValue value = operation.reverseAll(result, args, termEval.get(0));
				if(knownVariables.containsKey(rv))
				{
					knownVariables.get(rv).getValues().add(value);
				}
				else
				{
					TermAssignment ve = new TermAssignment();
					ve.getValues().add(value);
					ve.setVariable(rv);
					
					knownVariables.put(rv, ve);
				}
			}
			return true;
		}
		
		IReversibleOperation op = createHandler(unknownTerm.getClass());
		op.setRootTerm(unknownTerm);
		
		List<AbstractValue> resultList = new ArrayList<AbstractValue>();
		for(List<AbstractValue> args : setsOfResults)
		{
			resultList.add(operation.reverseAll(result, args, termEval.get(0)));
		}
		return resolveAll(resultList, op, knownVariables);
	}
	
	public boolean resolveAll(Collection<AbstractValue> result, IReversibleOperation operation,
			Map<TermWrapper, TermAssignment> knownVariables)
	{
		List<Map<TermWrapper, TermAssignment>> copies = new ArrayList<Map<TermWrapper,TermAssignment>>();
		for(AbstractValue value : result)
		{
			Map<TermWrapper, TermAssignment> copy = copyMap(knownVariables);
			copies.add(copy);
			
			if(!resolve(value, operation, copy))
			{
				return false;
			}
		}
		for(Map<TermWrapper, TermAssignment> map : copies)
		{
			mergeMap(knownVariables, map);
		}
		return true;
	}
	
	private static Map<TermWrapper, TermAssignment> copyMap(Map<TermWrapper, TermAssignment> map)
	{
		Map<TermWrapper, TermAssignment> copy = new HashMap<TermWrapper, TermAssignment>();
		
		for(TermWrapper key : map.keySet())
		{
			TermAssignment ve = map.get(key);
			TermAssignment copyVe = new TermAssignment();
			copyVe.setVariable(ve.getVariable());
			copyVe.getValues().addAll(ve.getValues());
			
			copy.put(key, copyVe);
		}
		
		return copy;
	}
	
	private static void mergeMap(Map<TermWrapper, TermAssignment> main,
			Map<TermWrapper, TermAssignment> map2)
	{
		for(TermWrapper key : map2.keySet())
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
				
				main.put(key, copyVe);		
			}
		}
	}
}
