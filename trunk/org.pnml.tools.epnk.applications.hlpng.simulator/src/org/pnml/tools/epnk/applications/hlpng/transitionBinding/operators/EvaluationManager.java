package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.utils.CartesianProduct;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.impl.AndImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.impl.InequalityImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.impl.OrImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.AdditionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.GreaterThanImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.LessThanImpl;
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
		
		handlers.put(GreaterThanImpl.class, new GreaterThanEval());
		handlers.put(LessThanImpl.class, new LessThanEval());
		handlers.put(AndImpl.class, new AndEval());
		handlers.put(OrImpl.class, new OrEval());
		handlers.put(InequalityImpl.class, new InequalityEval());
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
		
		if(evaluator == null)
		{
			System.out.println(term.getClass());
		}
		
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
}
