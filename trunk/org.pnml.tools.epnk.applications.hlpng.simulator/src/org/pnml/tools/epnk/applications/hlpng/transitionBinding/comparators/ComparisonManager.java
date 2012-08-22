package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.AbstractManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class ComparisonManager extends AbstractManager<IComparable, Term> implements IComparable
{
	private IComparable defaultComparator = null;
	
	@Override
	public boolean compare(Term refValue, IValue testValue, 
			Map<TermWrapper, TermAssignment> assignments)
	{
		IComparable comparator = getHandler(refValue.getClass());
		if(comparator != null)
		{
			return comparator.compare(refValue, testValue, assignments);
		}
		
		return defaultComparator.compare(refValue, testValue, assignments);
	}
	
	public void setDefaultComparator(IComparable defaultComparator)
    {
    	this.defaultComparator = defaultComparator;
    }
}
