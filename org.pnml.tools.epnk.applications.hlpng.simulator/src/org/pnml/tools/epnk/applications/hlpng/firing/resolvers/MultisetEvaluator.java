package org.pnml.tools.epnk.applications.hlpng.firing.resolvers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.MultiSetOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class MultisetEvaluator implements IAssignable
{
	@Override
    public boolean compare(ResolutionManager manager,
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
		
		if(v1.getSubterm().size() != v2.getValues().keySet().size())
		{
			return false;
		}

		List<AbstractValue> keys = new ArrayList<AbstractValue>(v2.getValues().keySet());
		
    	for(int i = 0; i < v1.getSubterm().size(); i++)
    	{    		
    		NumberOf nof = (NumberOf)v1.getSubterm().get(i);
    		
    		AbstractValue value = keys.get(i);
    		Integer m = v2.getValues().get(value);
    		PosValue multiplicity = new PosValue();
    		multiplicity.setN(m);
    		multiplicity.setSort(IntegersFactory.eINSTANCE.createPositive());
    		
    		if(!(manager.getComparator(nof.getSubterm().get(0).getClass())).compare(manager, 
    				nof.getSubterm().get(0), multiplicity, assignments) ||
    				!(manager.getComparator(nof.getSubterm().get(1).getClass())).compare(manager, 
    	    				nof.getSubterm().get(1), value, assignments))
    		{
    			return false;
    		}
    	}
    	return true;
    }

}