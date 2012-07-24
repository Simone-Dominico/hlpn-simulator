package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.MultiSetOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class MultisetComparator implements IComparable
{
	private IComparable comparisonManager = null;
	
	public MultisetComparator(IComparable comparisonManager)
	{
		this.comparisonManager = comparisonManager;
	}
	
	@Override
    public boolean compare(Object refValue, Object testValue,
            Map<TermWrapper, TermAssignment> assignments)
    {
		if(refValue instanceof IMSValue && testValue instanceof IMSValue)
		{
			IMSValue refms = (IMSValue) refValue;
			IMSValue testms = (IMSValue) testValue;
			
			for(final Entry<ITermWrapper, Integer> refentry : refms.entrySet())
			{
				final ITermWrapper refkey = refentry.getKey();
				
				for(final Entry<ITermWrapper, Integer> testentry : testms.entrySet())
				{
					final ITermWrapper testkey = testentry.getKey();
					
					comparisonManager.compare(refkey, testkey, assignments);
				}
			}
			
			return true;
		}
		
		if(!(refValue instanceof MultiSetOperator || testValue instanceof IMSValue) ||
	    		!(((Term)refValue).getSort().isSuperSortOf(((IValue)testValue).getSort()) ||
	    				((Term)refValue).getSort().equals(((IValue)testValue).getSort())))
	    {
	    	return false;
	    }
	    
		MultiSetOperator v1 = (MultiSetOperator)refValue;
		IMSValue v2 = (IMSValue)testValue;
		
		if(v1.getSubterm().size() != v2.size())
		{
			return false;
		}

		List<Entry<ITermWrapper, Integer>> entries = 
				new ArrayList<Entry<ITermWrapper, Integer>>(v2.entrySet());
		
    	for(int i = 0; i < v1.getSubterm().size(); i++)
    	{    		
    		NumberOf nof = (NumberOf)v1.getSubterm().get(i);
    		
    		ITermWrapper value = entries.get(i).getKey();
    		Integer m = entries.get(i).getValue();
    		PosValue multiplicity = new PosValue();
    		multiplicity.setN(m);
    		multiplicity.setSort(IntegersFactory.eINSTANCE.createPositive());
    		
    		if(!comparisonManager.compare( 
    				nof.getSubterm().get(0), multiplicity, assignments) ||
    				!comparisonManager.compare( 
    	    				nof.getSubterm().get(1), value, assignments))
    		{
    			return false;
    		}
    	}
    	return true;
    }

}
