package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.IComparable;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Add;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Subtract;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.MultiSetOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class ArcInscriptionHandler
{
	private Operator operator = null;
	private ComparisonManager comparisonManager = null;
	
	private List<NumberOf> allNumberOf = new ArrayList<NumberOf>();
	
	public ArcInscriptionHandler(Operator operator, ComparisonManager comparisonManager)
	{
		this.operator = operator;
		this.comparisonManager = comparisonManager;
		
		if(operator instanceof MultiSetOperator)
		{
			findAllNumberOf((MultiSetOperator)operator, allNumberOf);
		}
	}
	
	public Map<TermWrapper, TermAssignment> match(IMSValue value)
	{
		// each inscription term compared to all multiset terms
		Map<TermWrapper, TermAssignment> assignments = new HashMap<TermWrapper, TermAssignment>();
		for(NumberOf nof : allNumberOf)
		{
			contains(value, comparisonManager, nof, assignments);
			
		}
		return assignments;
	}

	private static void contains(IMSValue multiset, 
			ComparisonManager resolutionManager, NumberOf numberOf, 
			Map<TermWrapper, TermAssignment> assignments)
	{
		Term refMul = numberOf.getSubterm().get(0);
		Term refValue = numberOf.getSubterm().get(1);
		
		for(Entry<IValue, Integer> entry : multiset.entrySet())
		{
			IValue testValue = entry.getKey();
			
			IComparable valueEvaluator = resolutionManager.getComparator(refValue.getClass());

			if(valueEvaluator.compare(refValue, testValue, assignments))
			{
				Integer multiplicity = entry.getValue();
				
				// if it is not simple number-to-number comparison
				// e.g. assigning values to a variable
				if(!(refMul instanceof NumberConstant))
				{
					IComparable mulEvaluator = resolutionManager.getComparator(refMul.getClass());
					for(int i = 0; i <= multiplicity; i++)
					{
						PosValue testMul = new PosValue();
						testMul.setN(i);
						testMul.setSort(IntegersFactory.eINSTANCE.createPositive());
						
						mulEvaluator.compare(refMul, testMul, assignments);
					}
				}
			}
		}
	}

	public Operator getOperator()
    {
    	return operator;
    }
	
	private static void findAllNumberOf(MultiSetOperator operator, List<NumberOf> allNumberOf)
	{
		if(operator instanceof NumberOf)
		{
			allNumberOf.add(((NumberOf)operator));
		}
		else if(operator instanceof Add)
		{
			for(Term subterm : operator.getSubterm())
			{
				if(subterm instanceof MultiSetOperator)
				{
					findAllNumberOf((MultiSetOperator)subterm, allNumberOf);
				}
			}
		}
		else if(operator instanceof Subtract)
		{
			Term subterm = operator.getSubterm().get(0);
			if(subterm instanceof MultiSetOperator)
			{
				findAllNumberOf((MultiSetOperator)subterm, allNumberOf);
			}
		}	
	}
}
