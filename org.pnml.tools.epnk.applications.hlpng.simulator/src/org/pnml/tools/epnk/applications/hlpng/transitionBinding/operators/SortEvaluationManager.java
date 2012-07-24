package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.All;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public class SortEvaluationManager implements ISortEvaluator
{
	private Map<Object, ISortEvaluator> handlers = new HashMap<Object, ISortEvaluator>();
	
	public void register(Object targetObject, ISortEvaluator operator)
	{
		handlers.put(targetObject, operator);
	}
	
	public void unregister(Object targetObject)
	{
		handlers.remove(targetObject);
	}
	
	private ISortEvaluator getHandler(Class<? extends Sort> targetClass)
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
	
	@Override
	public IValue evaluate(Sort sort)
	{
		ISortEvaluator evaluator = getHandler(sort.getClass());
		
		return evaluator.evaluate(sort);
	}

	@Override
    public String validate(Object obj)
    {
	    if(obj instanceof All)
	    {
	    	All all = (All) obj;
	    	
			ISortEvaluator eval = getHandler(all.getRefsort().getClass());
			
			if(eval == null)
			{
				return "all:" + all.getRefsort().getClass().getName();
			}
			return eval.validate(all.getRefsort());
	    }
	    return null;
    }
}
