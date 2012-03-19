package org.pnml.tools.epnk.applications.hlpng.firing.evaluators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.MultiSetOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class MultisetEvaluator implements IEvaluator
{
	@Override
    public boolean compare(EvaluationManager manager,
            Term refValue, AbstractValue testValue,
            Map<String, VariableEvaluation> assignments)
    {
		if(!(refValue instanceof MultiSetOperator || testValue instanceof MSValue) ||
	    		!(refValue.getSort().isSuperSortOf(testValue.getSort()) ||
	    				refValue.getSort().equals(testValue.getSort())))
	    {
	    	return false;
	    }
	    
		MultiSetOperator v1 = (MultiSetOperator)refValue;
		MSValue v2 = (MSValue)testValue;

    	for(Term k1 : v1.getSubterm())
    	{    		
    		for(AbstractValue k2 : v2.getValues().keySet())
    		{
        		if(manager.getComparator(k1.getClass()).compare(manager, k1, k2, assignments))
        		{
        			return false;
        		}	
    		}
    	}
    	return true;
    }

}
