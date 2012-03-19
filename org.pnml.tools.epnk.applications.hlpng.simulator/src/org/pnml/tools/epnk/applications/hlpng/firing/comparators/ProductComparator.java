package org.pnml.tools.epnk.applications.hlpng.firing.comparators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;

public class ProductComparator implements IComparator
{
	@Override
	public boolean compare(ComparatorManager manager,
            AbstractValue refValue, AbstractValue testValue,
            Map<String, VariableEvaluation> assignments)
    {
	    if(!(refValue instanceof ProductValue || testValue instanceof ProductValue) ||
	    		!(refValue.getSort().equals(testValue.getSort()) ||
	    				refValue.getSort().isSuperSortOf(testValue.getSort())))
	    {
	    	return false;
	    }
	    
    	ProductValue v1 = (ProductValue)refValue;
    	ProductValue v2 = (ProductValue)testValue;
    	
    	for(int i = 0; i < v1.getComponents().size(); i++)
    	{
    		AbstractValue c1 = v1.getComponents().get(i);
    		AbstractValue c2 = v2.getComponents().get(i);
    		
    		if(!manager.getComparator(c1.getClass()).compare(manager, c1, c2, assignments))
    		{
    			return false;
    		}
    	}
    	return true;
    }

}
