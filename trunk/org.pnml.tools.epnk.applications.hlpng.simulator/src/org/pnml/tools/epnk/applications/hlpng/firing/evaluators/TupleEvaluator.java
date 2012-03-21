package org.pnml.tools.epnk.applications.hlpng.firing.evaluators;

import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Tuple;

public class TupleEvaluator implements IEvaluator
{
	@Override
	public boolean compare(EvaluationManager manager,
            Term refValue, AbstractValue testValue,
            Map<String, VariableEvaluation> assignments)
    {
	    if(!(refValue instanceof Tuple || testValue instanceof ProductValue) ||
	    		!(refValue.getSort().equals(testValue.getSort()) ||
	    				refValue.getSort().isSuperSortOf(testValue.getSort())) ||
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
    		AbstractValue c2 = v2.getComponents().get(i);
    		
    		if(!manager.getComparator(c1.getClass()).compare(manager, c1, c2, assignments))
    		{
    			return false;
    		}
    	}
    	return true;
    }

}