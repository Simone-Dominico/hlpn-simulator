package org.pnml.tools.epnk.applications.hlpng.firing.evaluators;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.operators.RuntimeVariable;
import org.pnml.tools.epnk.applications.hlpng.runtime.IntValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NatValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;

public class EvaluationManager
{
	private Map<Class, IEvaluator> handlers = null;

	public EvaluationManager()
	{
		handlers = new HashMap<Class, IEvaluator>();
		{
			handlers.put(NatValue.class, new NumberConstantEvaluator());
			handlers.put(PosValue.class, new NumberConstantEvaluator());
			handlers.put(IntValue.class, new NumberConstantEvaluator());
			
			handlers.put(ProductValue.class, new TupleEvaluator());
			handlers.put(MSValue.class, new MultisetEvaluator());
			
			handlers.put(RuntimeVariable.class, new VariableEvaluator());
		}
	}
	
	public void register(Class targetClass, IEvaluator comparator)
	{
		handlers.put(targetClass, comparator);
	}
	
	public void unregister(Class targetClass)
	{
		handlers.remove(targetClass);
	}
	
	public IEvaluator getComparator(Class targetClass)
	{
		return handlers.get(targetClass);
	}
}
