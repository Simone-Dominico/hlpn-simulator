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
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class ArcInscriptionHandler
{
	private Operator operator = null;
	private ComparisonManager evaluationManager = null;
	
	public ArcInscriptionHandler(Operator operator, ComparisonManager evaluationManager)
	{
		this.operator = operator;
		this.evaluationManager = evaluationManager;
	}
	
	public List<List<Map<TermWrapper, TermAssignment>>> match(MSValue value)
	{
		// each inscription term compared to all multiset terms
		List<List<Map<TermWrapper, TermAssignment>>> list = 
				new ArrayList<List<Map<TermWrapper,TermAssignment>>>();
		if(operator instanceof NumberOf)
		{
			List<Map<TermWrapper, TermAssignment>> assignments = contains(value, 
					evaluationManager, (NumberOf)operator);
			
			list.add(assignments);
		}
		else if(operator instanceof MultiSetOperator && checkMSOpertator((MultiSetOperator)operator))
		{
			for(Term refValue : operator.getSubterm())
			{
				List<Map<TermWrapper, TermAssignment>> assignments = contains(value, 
						evaluationManager, ((NumberOf)refValue));
				
				list.add(assignments);	
			}
		}
		return list;
	}
	
	private static boolean checkMSOpertator(MultiSetOperator operator)
	{
		for(Term term : operator.getSubterm())
		{
			if(!(term instanceof NumberOf))
			{
				return false;
			}
		}
		return true;
	}

	private static List<Map<TermWrapper, TermAssignment>> contains(
			MSValue multiset, ComparisonManager resolutionManager, NumberOf numberOf)
	{
		Term refMul = numberOf.getSubterm().get(0);
		Term refValue = numberOf.getSubterm().get(1);
		
		List<Map<TermWrapper, TermAssignment>> list = new ArrayList<Map<TermWrapper,TermAssignment>>();

		for(AbstractValue testValue : multiset.getValues().keySet())
		{
			Map<TermWrapper, TermAssignment> assignments = new HashMap<TermWrapper, TermAssignment>();
			
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

	public Operator getOperator()
    {
    	return operator;
    }
}
