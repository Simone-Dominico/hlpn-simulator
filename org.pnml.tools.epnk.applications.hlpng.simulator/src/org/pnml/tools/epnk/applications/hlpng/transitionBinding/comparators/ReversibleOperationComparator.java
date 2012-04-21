package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AbstractReversibleOperation;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class ReversibleOperationComparator implements IComparable
{
	private EvaluationManager evaluationManager = null;
	private ReversibleOperationManager reversibleOperationManager = null;
	
	public ReversibleOperationComparator(EvaluationManager evaluationManager,
			ReversibleOperationManager reversibleOperationManager)
	{
		this.evaluationManager = evaluationManager;
		this.reversibleOperationManager = reversibleOperationManager;
	}
	
	@Override
	public boolean compare(Term refValue, AbstractValue testValue,
            Map<String, VariableEvaluation> assignments)
    {
		boolean cannotEval = false;
		try
        {
            Set<AbstractValue> evals = evaluationManager.evaluateAll(refValue, assignments);
            return evals.contains(testValue);
        }
        catch(UnknownVariableException e)
        {
        	cannotEval = true;
        }
		if(cannotEval)
		{
			AbstractReversibleOperation operation = reversibleOperationManager.createHandler(refValue.getClass());
			operation.setRootTerm(refValue);
			
			if(assignments.containsKey(operation.getName()))
			{
				assignments.get(operation.getName()).getValues().add(testValue);
			}
			else
			{
				VariableEvaluation ve = new VariableEvaluation();
				ve.getValues().add(testValue);
				ve.setVariable(operation);

				assignments.put(operation.getName(), ve);
			}
		}
		return true;
    }
}
