package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class ComparisonManager implements IComparable
{
	private Map<Object, IComparable> handlers = new HashMap<Object, IComparable>();
	
	public void register(Object targetObject, IComparable comparator)
	{
		handlers.put(targetObject, comparator);
	}
	
	public void unregister(Object targetObject)
	{
		handlers.remove(targetObject);
	}
	
	@Override
	public boolean compare(Term refValue, Object testValue, 
			Map<TermWrapper, TermAssignment> assignments)
	{
		IComparable comparator = getComparator(refValue.getClass());
		if(comparator != null)
		{
			return comparator.compare(refValue, testValue, assignments);
		}
		
		throw new RuntimeException("Do not know how to compare: " + refValue.getClass());
	}
	
	private IComparable getComparator(Class<? extends Term> targetClass)
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
}
