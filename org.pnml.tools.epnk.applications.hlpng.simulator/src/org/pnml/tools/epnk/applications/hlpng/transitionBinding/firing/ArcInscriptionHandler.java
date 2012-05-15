package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
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
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class ArcInscriptionHandler
{
	private Operator operator = null;
	private ComparisonManager comparisonManager = null;
	
	private List<NumberOf> allNumberOf = new ArrayList<NumberOf>();
	private Set<TermWrapper> variables = new HashSet<TermWrapper>();
	
	public ArcInscriptionHandler(Operator operator, ComparisonManager comparisonManager)
	{
		this.operator = operator;
		this.comparisonManager = comparisonManager;
		
		if(operator instanceof MultiSetOperator)
		{
			findAllNumberOf((MultiSetOperator)operator, allNumberOf);
			
			for(NumberOf nof : allNumberOf)
			{
				allVars(nof, variables);
			}
		}
	}
	
	public Map<TermWrapper, TermAssignment> match(MSValue value)
	{
		// each inscription term compared to all multiset terms
		Map<TermWrapper, TermAssignment> assignments = new HashMap<TermWrapper, TermAssignment>();
		for(NumberOf nof : allNumberOf)
		{
			contains(value, comparisonManager, nof, assignments);
			
		}
		return assignments;
	}

	private static void contains(MSValue multiset, 
			ComparisonManager resolutionManager, NumberOf numberOf, 
			Map<TermWrapper, TermAssignment> assignments)
	{
		Term refMul = numberOf.getSubterm().get(0);
		Term refValue = numberOf.getSubterm().get(1);
		
		for(AbstractValue testValue : multiset.getValues().keySet())
		{
			IComparable valueEvaluator = resolutionManager.getComparator(refValue.getClass());

			if(valueEvaluator.compare(refValue, testValue, assignments))
			{
				Integer multiplicity = multiset.getValues().get(testValue);
				
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
	
	public Set<TermWrapper> getVariables()
    {
    	return variables;
    }
	
	private static void allVars(Term term, Set<TermWrapper> vars)
	{
		if(term instanceof Variable)
		{
			VariableWrapper wrapper = new VariableWrapper();
			wrapper.setRootTerm(term);
			wrapper.setVariable((Variable)term);
			
			vars.add(wrapper);
		}
		else if(term instanceof Operator)
		{
			Operator operator = (Operator) term;
			for(Term subterm : operator.getSubterm())
			{
				allVars(subterm, vars);
			}
		}
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
