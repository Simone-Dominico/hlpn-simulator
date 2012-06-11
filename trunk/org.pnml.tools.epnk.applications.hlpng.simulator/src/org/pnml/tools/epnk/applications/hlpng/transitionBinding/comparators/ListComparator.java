package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ListValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.lists.MakeList;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class ListComparator implements IComparable
{
	private ComparisonManager comparisonManager = null;
	
	public ListComparator(ComparisonManager comparisonManager)
	{
		this.comparisonManager = comparisonManager;
	}
	
	@Override
	public boolean compare(Term refValue, AbstractValue testValue,
            Map<TermWrapper, TermAssignment> assignments)
    {
	    if(!(refValue instanceof MakeList || testValue instanceof ListValue) ||
	    				((MakeList)refValue).getSubterm().size() != 
	    				((ListValue)testValue).getElements().size() ||
	    				!((MakeList)refValue).getRefsort().equals(((ListValue)testValue).getSort()))
	    {
	    	return false;
	    }
	    
	    MakeList v1 = (MakeList)refValue;
	    ListValue v2 = (ListValue)testValue;
    	
    	for(int i = 0; i < v1.getSubterm().size(); i++)
    	{
    		Term c1 = v1.getSubterm().get(i);
    		AbstractValue c2 = v2.getElements().get(i);
    		
    		if(!comparisonManager.getComparator(c1.getClass()).compare(c1, c2, assignments))
    		{
    			return false;
    		}
    	}
    	return true;
    }

}
