package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public class DataTypeEvaluationManager
{
	private Map<Object, IDataTypeEvaluator> handlers = new HashMap<Object, IDataTypeEvaluator>();
	
	public void register(Object targetObject, IDataTypeEvaluator operator)
	{
		handlers.put(targetObject, operator);
	}
	
	public IDataTypeEvaluator getHandler(Class targetClass)
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
	
	public AbstractValue evaluate(Sort sort)
	{
		IDataTypeEvaluator evaluator = getHandler(sort.getClass());
		
		return evaluator.evaluate(sort);
	}
}
