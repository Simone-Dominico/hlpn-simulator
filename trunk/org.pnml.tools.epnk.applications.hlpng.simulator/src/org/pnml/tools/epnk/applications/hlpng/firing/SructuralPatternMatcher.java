package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.comparators.ComparatorManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeVariable;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;

public class SructuralPatternMatcher
{
	private MSValue cachedValue = null;
	private ComparatorManager comparatorManager = null;
	
	public SructuralPatternMatcher(MSValue value, ComparatorManager comparatorManager)
	{
		this.cachedValue = value;
		this.comparatorManager = comparatorManager;
	}
	
	public List<InscriptionMatch> match(MSValue value)
	{
		List<InscriptionMatch> list = new ArrayList<InscriptionMatch>();
		
		for(AbstractValue refValue : cachedValue.getValues().keySet())
		{
			Integer multiplicity = AbstractValueMath.calcMultiplicity(cachedValue, refValue);
			
			List<VariableAssignmnet> assignments = contains(value, comparatorManager,
					refValue, multiplicity);
			if(assignments.size() > 0)
			{
				InscriptionMatch match = new InscriptionMatch();

				match.setMultiplicity(AbstractValueMath.calcMultiplicity(cachedValue, refValue));
				match.setAssignmnets(assignments);
				
				list.add(match);
			}
		}
		
		if(list.size() == 0)
		{
			return null;
		}
		
		return list;
	}
	
	private static List<VariableAssignmnet> contains(
			MSValue multiset, ComparatorManager comparatorManager,
			AbstractValue refValue, int refMultiplicity)
	{
		List<VariableAssignmnet> allAssignments = new ArrayList<VariableAssignmnet>();
		
		for(AbstractValue testValue : multiset.getValues().keySet())
		{
			Integer count = AbstractValueMath.calcMultiplicity(multiset, testValue);
			if(count != null && refMultiplicity <= count)
			{
				Map<RuntimeVariable, AbstractValue> assignments = 
						new HashMap<RuntimeVariable, AbstractValue>();
				if(comparatorManager.getComparator(refValue.getClass())
						.compare(comparatorManager, refValue, testValue, assignments))
				{
					// a test value against an inscription (having * number of variables)
					VariableAssignmnet assignment = new VariableAssignmnet();
					assignment.setAssignments(assignments);
					assignment.setParentValue(testValue);
					allAssignments.add(assignment);
				}
			}
		}
		return allAssignments;
	}



	public MSValue getCachedValue()
    {
    	return cachedValue;
    }
}
