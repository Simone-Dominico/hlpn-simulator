package org.pnml.tools.epnk.applications.hlpng.firing.resolvers;

import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.firing.VariableEvaluation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.operators.AbstractReversibleOperation;
import org.pnml.tools.epnk.applications.hlpng.runtime.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.operators.UnknownVariableException;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.BuiltInOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class BinaryOperationEvaluator implements IAssignable
{
	@Override
	public boolean compare(ResolutionManager manager,
            Term refValue, AbstractValue testValue,
            Map<String, VariableEvaluation> assignments)
    {
		if(refValue instanceof BuiltInOperator)
		{
			try
            {
	            Set<AbstractValue> evals = EvaluationManager.getInstance().evaluateAll(refValue, assignments);
	            
	            return evals.contains(testValue);
            }
            catch(UnknownVariableException e)
            {
            	AbstractReversibleOperation operation = EvaluationManager
    					.getInstance().createRevertableOperationHandler(refValue.getClass());
    			operation.setRootTerm(refValue);
    			if(!EvaluationManager.getInstance().resolve(testValue, operation, assignments))
    			{
    				if(assignments.containsKey(operation.getName()))
    				{
    					assignments.get(operation.getName()).getValues().add(testValue);
    				}
    				else
    				{
    					VariableEvaluation ve = new VariableEvaluation();
    					ve.getValues().add(testValue);
    					ve.setVariable(operation);
    					ve.setVariableName(operation.getName());

    					assignments.put(operation.getName(), ve);
    				}
    			}
            }
			return true;
		}
	    return false;
    }
}
