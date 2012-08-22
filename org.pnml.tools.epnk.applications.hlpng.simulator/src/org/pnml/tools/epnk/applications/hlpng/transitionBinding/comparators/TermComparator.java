package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermAssignment;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class TermComparator implements IComparable
{
	private EvaluationManager evaluationManager = null;
	
	public TermComparator(EvaluationManager evaluationManager)
	{
		this.evaluationManager = evaluationManager;
	}
	
	@Override
	public boolean compare(Term refValue, IValue testValue,
            Map<TermWrapper, TermAssignment> assignments)
    {
		// simple evaluation
		try
        {
            Set<IValue> evals = evaluationManager.evaluateAll(refValue, assignments);
            return evals.contains(testValue);
        }
        catch(UnknownVariableException e){}
		
		final TermWrapper operation = new TermWrapper();
		operation.setRootTerm(refValue);
		if(assignments.containsKey(operation))
		{
			assignments.get(operation).getValues().add(testValue);
		}
		else
		{
			TermAssignment ve = new TermAssignment();
			ve.getValues().add(testValue);
			ve.setTermWrapper(operation);

			assignments.put(operation, ve);
		}
		
		return true;
    }
}
