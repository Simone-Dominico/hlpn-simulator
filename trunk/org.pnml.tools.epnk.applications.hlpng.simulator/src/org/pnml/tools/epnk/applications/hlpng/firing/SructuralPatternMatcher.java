package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.comparators.ComparatorManager;

import runtime.AbstractValue;
import runtime.MSValue;
import runtime.RuntimeVariable;

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
			List<VariableAssignmnet> assignments = contains(value, comparatorManager,
					refValue, cachedValue.getMultiplicity(refValue));
			if(assignments.size() > 0)
			{
				InscriptionMatch match = new InscriptionMatch();

				match.setMultiplicity(cachedValue.getMultiplicity(refValue));
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
			if(refMultiplicity <= multiset.getMultiplicity(testValue))
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
}
