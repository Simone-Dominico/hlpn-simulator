package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.reversible.AbstractReversibleOperation;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.reversible.ReversibleOperationManager;
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
	public boolean compare(Term refValue, Object testValue,
            Map<TermWrapper, TermAssignment> assignments)
    {
		boolean cannotEval = false;
		try
        {
            Set<IValue> evals = evaluationManager.evaluateAll(refValue, assignments);
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
			
			if(assignments.containsKey(operation))
			{
				assignments.get(operation).getValues().add(((IValue)testValue));
			}
			else
			{
				TermAssignment ve = new TermAssignment();
				ve.getValues().add(((IValue)testValue));
				ve.setTermWrapper(operation);

				assignments.put(operation, ve);
			}
		}
		return true;
    }
}
