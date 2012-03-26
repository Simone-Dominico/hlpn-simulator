package org.pnml.tools.epnk.applications.hlpng.runtime.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.firing.RuntimeVariable;
import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.AdditionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.MultiplicationImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.AddImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.TupleImpl;

public class EvaluationManager
{
	private static EvaluationManager termManager = null;
	
	private Map<Class, IEvaluator> handlers = null;
	
	private EvaluationManager()
	{
		handlers = new HashMap<Class, IEvaluator>();
		
		handlers.put(NumberConstantImpl.class, new NumberConstantEval());
		handlers.put(NumberOfImpl.class, new NumberOfEval());
		handlers.put(AddImpl.class, new AddEval());
		handlers.put(TupleImpl.class, new TupleEval());
		
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
	
	public AbstractValue evaluate(Term term, Map<String, AbstractValue> assignments) throws UnknownVariableException
	{
		if(term instanceof Variable)
		{
			Variable var = (Variable) term;
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
	
	public boolean resolve(AbstractValue result, IRevertableOperation operation,
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
					ve.setVariableName(varName);
					
					knownVariables.put(varName, ve);
				}
			}
			return true;
		}
		
		IRevertableOperation op = createRevertableOperationHandler(unknown.get(0).getClass());
		op.setRootTerm(unknown.get(0));
		
		for(List<AbstractValue> args : setsOfResults)
		{
			if(!resolve(operation.revert(result, args), op, knownVariables))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public AbstractRevertableOperation createRevertableOperationHandler(Class c)
	{
		try
        {
			Object obj = handlers.get(c).getClass().newInstance();
	        return (AbstractRevertableOperation)obj;
        }
        catch(Exception e)
        {
        	System.err.println("Could not create a handler for: " + c);
        	e.printStackTrace();
        }
		return null;
	}
}
