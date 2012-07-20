package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Tuple;

public class TupleComparator implements IComparable
{
	private IComparable comparisonManager = null;
	
	public TupleComparator(IComparable comparisonManager)
	{
		this.comparisonManager = comparisonManager;
	}
	
	@Override
	public boolean compare(Term refValue, Object testValue,
            Map<TermWrapper, TermAssignment> assignments)
    {
		// FIXME: mla: errors in home made sort comparison
		// need to check || !(refValue.getSort().equals(testValue.getSort())
		// || refValue.getSort().isSuperSortOf(testValue.getSort()))
		// but it is not clear how to handle user created sorts
	    if(!(refValue instanceof Tuple || testValue instanceof ProductValue) ||
	    				((Tuple)refValue).getSubterm().size() != 
	    				((ProductValue)testValue).getComponents().size())
	    {
	    	return false;
	    }
	    
	    Tuple v1 = (Tuple)refValue;
    	ProductValue v2 = (ProductValue)testValue;
    	
    	for(int i = 0; i < v1.getSubterm().size(); i++)
    	{
    		Term c1 = v1.getSubterm().get(i);
    		ITermWrapper c2 = v2.getComponents().get(i);
    		
    		if(!comparisonManager.compare(c1, c2, assignments))
    		{
    			return false;
    		}
    	}
    	return true;
    }

}
