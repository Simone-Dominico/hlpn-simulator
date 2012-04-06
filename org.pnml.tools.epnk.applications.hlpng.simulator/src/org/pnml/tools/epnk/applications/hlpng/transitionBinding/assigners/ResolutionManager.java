package org.pnml.tools.epnk.applications.hlpng.transitionBinding.assigners;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.AdditionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.MultiplicationImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.impl.StringConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.MultiSetOperatorImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.TupleImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.VariableImpl;

public class ResolutionManager
{
	private static ResolutionManager evaluationManager = null;
	
	private Map<Class, IAssignable> handlers = null;

	private ResolutionManager()
	{
		handlers = new HashMap<Class, IAssignable>();
		{
			handlers.put(NumberConstantImpl.class, new NumberConstantEvaluator());
			handlers.put(StringConstantImpl.class, new StringConstantEvaluator());
			
			handlers.put(TupleImpl.class, new TupleEvaluator());
			handlers.put(MultiSetOperatorImpl.class, new MultisetEvaluator());
			handlers.put(NumberOfImpl.class, new NumberOfEvaluator());
			
			BinaryOperationEvaluator binEval = new BinaryOperationEvaluator();
			handlers.put(AdditionImpl.class, binEval);
			handlers.put(MultiplicationImpl.class, binEval);
			
			handlers.put(VariableImpl.class, new VariableEvaluator());
		}
	}
	
	public static ResolutionManager getInstance()
	{
		if(evaluationManager == null)
		{
			evaluationManager = new ResolutionManager();
		}
		return evaluationManager;
	}
	
	public void register(Class targetClass, IAssignable comparator)
	{
		handlers.put(targetClass, comparator);
	}
	
	public void unregister(Class targetClass)
	{
		handlers.remove(targetClass);
	}
	
	public IAssignable getComparator(Class targetClass)
	{
		return handlers.get(targetClass);
	}
}
