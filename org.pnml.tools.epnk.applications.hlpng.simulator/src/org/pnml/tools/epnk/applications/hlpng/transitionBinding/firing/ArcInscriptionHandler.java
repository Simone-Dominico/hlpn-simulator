package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.IComparable;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.MultiSetOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class ArcInscriptionHandler
{
	private MultiSetOperator multiSetOperator = null;
	private ComparisonManager evaluationManager = null;
	
	public ArcInscriptionHandler(MultiSetOperator multiSetOperator, ComparisonManager evaluationManager)
	{
		this.multiSetOperator = multiSetOperator;
		this.evaluationManager = evaluationManager;
	}
	
	public List<List<Map<String, VariableEvaluation>>> match(MSValue value)
	{
		// each inscription term compared to all multiset terms
		List<List<Map<String, VariableEvaluation>>> list = 
				new ArrayList<List<Map<String,VariableEvaluation>>>();
		if(multiSetOperator instanceof NumberOf)
		{
			List<Map<String, VariableEvaluation>> assignments = contains(value, 
					evaluationManager, (NumberOf)multiSetOperator);
			
			list.add(assignments);
		}
		else
		{
			for(Term refValue : multiSetOperator.getSubterm())
			{
				List<Map<String, VariableEvaluation>> assignments = contains(value, 
						evaluationManager, ((NumberOf)refValue));
				
				list.add(assignments);
			}
		}
		return list;
	}

	private static List<Map<String, VariableEvaluation>> contains(
			MSValue multiset, ComparisonManager resolutionManager, NumberOf numberOf)
	{
		Term refMul = numberOf.getSubterm().get(0);
		Term refValue = numberOf.getSubterm().get(1);
		
		List<Map<String, VariableEvaluation>> list = new ArrayList<Map<String,VariableEvaluation>>();

		for(AbstractValue testValue : multiset.getValues().keySet())
		{
			Map<String, VariableEvaluation> assignments = new HashMap<String, VariableEvaluation>();
			
			IComparable valueEvaluator = resolutionManager.getComparator(refValue.getClass());
			boolean madeAssignment = false;
			if(valueEvaluator.compare(refValue, testValue, assignments))
			{
				Integer multiplicity = multiset.getValues().get(testValue);
				
				if(refMul instanceof NumberConstant && ((NumberConstant)refMul).getValue() <= multiplicity)
				{
					madeAssignment = true;
				}
				else if(!(refMul instanceof NumberConstant))
				{
					IComparable mulEvaluator = resolutionManager.getComparator(refMul.getClass());
					for(int i = 1; i <= multiplicity; i++)
					{
						PosValue testMul = new PosValue();
						testMul.setN(i);
						testMul.setSort(IntegersFactory.eINSTANCE.createPositive());
						
						mulEvaluator.compare(refMul, testMul, assignments);
					}
					madeAssignment = true;
				}
			}
			if(madeAssignment)
			{
				list.add(assignments);
			}
		}
		return list;
	}

	public MultiSetOperator getMultiSetOperator()
    {
    	return multiSetOperator;
    }
}