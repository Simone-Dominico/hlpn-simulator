package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.VariableWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.arbitrarydeclarations.ArbitraryOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.NamedOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class UserOperatorEval implements IEvaluator
{
	private IEvaluator arbitraryOperatorEvaluator = null;
	private EvaluationManager evaluationManager = null;
	
	public UserOperatorEval(EvaluationManager evaluationManager)
	{
		this.evaluationManager = evaluationManager;
	}
	
	@Override
	public AbstractValue evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, AbstractValue> assignments) throws UnknownVariableException
	{		
		UserOperator userOperator = (UserOperator)term;
		if(userOperator.getDeclaration() instanceof NamedOperator)
		{
			NamedOperator namedOperator = (NamedOperator) userOperator.getDeclaration();
			
			Map<TermWrapper, AbstractValue> newAssignments = new HashMap<TermWrapper, AbstractValue>();
			for(int i = 0; i < userOperator.getSubterm().size(); i++)
			{
				Term subterm = userOperator.getSubterm().get(i);
				AbstractValue value = evaluationManager.evaluate(subterm, assignments);
				
				VariableWrapper varWrapper = new VariableWrapper();
				Variable var = TermsFactory.eINSTANCE.createVariable();
				var.setRefvariable(namedOperator.getParameters().get(i));
				varWrapper.setRootTerm(var);
				varWrapper.setVariable(var);
				
				newAssignments.put(varWrapper, value);
			}	
			return evaluationManager.evaluate(namedOperator.getDef(), newAssignments);
		}
		if(userOperator.getDeclaration() instanceof ArbitraryOperator)
		{
			return arbitraryOperatorEvaluator.evaluate(term, evaluationManager, assignments);
		}
		
		throw new RuntimeException("Unknown user operator: " + term);
	}

	public IEvaluator getArbitraryOperatorEvaluator()
    {
    	return arbitraryOperatorEvaluator;
    }

	public void setArbitraryOperatorEvaluator(IEvaluator arbitraryOperatorEvaluator)
    {
    	this.arbitraryOperatorEvaluator = arbitraryOperatorEvaluator;
    }

	@Override
    public String validate(Object term)
    {
		UserOperator userOperator = (UserOperator)term;
		
		if(userOperator.getDeclaration() instanceof NamedOperator)
		{
			for(Term subterm : userOperator.getSubterm())
			{
				String err = evaluationManager.check(subterm);
				if(err != null)
				{
					return err;
				}
			}	
			return null;
		}
		if(userOperator.getDeclaration() instanceof ArbitraryOperator)
		{
			return arbitraryOperatorEvaluator.validate(term);
		}
		
		throw new RuntimeException("Unknown user operator: " + term);
    }
}
