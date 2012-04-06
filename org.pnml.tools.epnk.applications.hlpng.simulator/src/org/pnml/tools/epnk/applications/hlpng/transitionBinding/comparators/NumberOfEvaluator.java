package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class NumberOfEvaluator implements IComparable
{
	@Override
    public boolean compare(Term refValue, AbstractValue testValue,
            Map<String, VariableEvaluation> assignments)
    {
		if(!(refValue instanceof NumberOf || testValue instanceof MSValue))
	    {
	    	return false;
	    }
	    
		NumberOf nof = (NumberOf)refValue;
		MSValue v2 = (MSValue)testValue;
		
		boolean wasMatch = false;
    	for(AbstractValue value : v2.getValues().keySet())
    	{    		
    		Integer m = v2.getValues().get(value);
    		PosValue multiplicity = new PosValue();
    		multiplicity.setN(m);
    		multiplicity.setSort(IntegersFactory.eINSTANCE.createPositive());
    		
    		ComparisonManager manager = ComparisonManager.getInstance();
    		if(manager.getComparator(nof.getSubterm().get(0).getClass()).compare( 
    				nof.getSubterm().get(0), multiplicity, assignments) &&
    				manager.getComparator(nof.getSubterm().get(1).getClass()).compare( 
    	    				nof.getSubterm().get(1), value, assignments))
    		{
    			wasMatch = true;
    		}
    	}
    	return wasMatch;
    }

}
