package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
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
	private final Operator operator;
	private final ComparisonManager comparisonManager;
	
	final private List<ITermWrapper> terms = new ArrayList<ITermWrapper>();
	
	public ArcInscriptionHandler(Operator operator, 
			ComparisonManager comparisonManager, EvaluationManager evaluationManager)
	{
		this.operator = operator;
		this.comparisonManager = comparisonManager;
		
		final List<NumberOf> allNumberOf = new ArrayList<NumberOf>();
		if(operator instanceof MultiSetOperator)
		{
			findAllNumberOf((MultiSetOperator)operator, allNumberOf);
		}
		
		for(final NumberOf nof : allNumberOf)
		{
			try
            {
	            final ITermWrapper w = evaluationManager.evaluate(nof, null);
	            terms.add(w);
            }
            catch(Exception e)
            {
            	final TermWrapper w = new TermWrapper();
            	w.setRootTerm(nof);
            	terms.add(w);
            }
		}
	}
	
	public Map<TermWrapper, TermAssignment> match(IMSValue value)
	{
		// each inscription term compared to all multiset terms
		final Map<TermWrapper, TermAssignment> assignments = 
				new HashMap<TermWrapper, TermAssignment>();
		for(final ITermWrapper nof : terms)
		{
			contains(value, comparisonManager, nof, assignments);
		}
		return assignments;
	}

	private static void contains(IMSValue testValue, 
			ComparisonManager comparisonManager, ITermWrapper refValue, 
			Map<TermWrapper, TermAssignment> assignments)
	{
		if(!(refValue instanceof IValue) && refValue.getRootTerm() != null &&
				refValue.getRootTerm() instanceof NumberOf)
		{
			contains(testValue, comparisonManager, 
					(NumberOf)refValue.getRootTerm(), assignments);
		}
		else if(refValue instanceof MSValue)
		{
			comparisonManager.compare(refValue, testValue, assignments);
		}
		else
		{
			System.err.println("ERR: do not know how to compare " + refValue.getClass());
		}
	}
	
	private static void contains(IMSValue multiset, 
			ComparisonManager comparisonManager, NumberOf numberOf, 
			Map<TermWrapper, TermAssignment> assignments)
	{
		Term refMul = numberOf.getSubterm().get(0);
		Term refValue = numberOf.getSubterm().get(1);
		
		for(Entry<ITermWrapper, Integer> entry : multiset.entrySet())
		{
			ITermWrapper testValue = entry.getKey();
			
			if(comparisonManager.compare(refValue, testValue, assignments))
			{
				Integer multiplicity = entry.getValue();
				
				// if it is not simple number-to-number comparison
				// e.g. assigning values to a variable
				if(!(refMul instanceof NumberConstant))
				{
					for(int i = 0; i <= multiplicity; i++)
					{
						PosValue testMul = new PosValue();
						testMul.setN(i);
						testMul.setSort(IntegersFactory.eINSTANCE.createPositive());
						
						comparisonManager.compare(refMul, testMul, assignments);
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
