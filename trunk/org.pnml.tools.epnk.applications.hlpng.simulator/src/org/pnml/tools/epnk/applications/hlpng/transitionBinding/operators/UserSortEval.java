package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserSort;

public class UserSortEval implements ISortEvaluator
{
	private List<ISortEvaluator> arbitraryOperatorEvaluators = null;

	public List<ISortEvaluator> getArbitraryOperatorEvaluators()
    {
    	return arbitraryOperatorEvaluators;
    }

	public void setArbitraryOperatorEvaluators(List<ISortEvaluator> evals)
    {
    	this.arbitraryOperatorEvaluators = evals;
    }

	@Override
    public String validate(Object obj)
    {
		if(obj instanceof UserSort)
		{
			for(ISortEvaluator aEval : arbitraryOperatorEvaluators)
			{
				if(aEval.validate(obj) == null)
				{
					return null;
				}
				UserSort userSort = (UserSort) obj;
				return "User defined sort\n" + userSort.getDeclaration().getName();
			}	
		}
		
		return null;
    }

	@Override
    public IValue evaluate(Sort sort)
    {
		for(ISortEvaluator aEval : arbitraryOperatorEvaluators)
		{
			if(aEval.validate(sort) == null)
			{
				return aEval.evaluate(sort);
			}
		}
		
		throw new RuntimeException("Unknown user operator: " + sort);
    }
}
