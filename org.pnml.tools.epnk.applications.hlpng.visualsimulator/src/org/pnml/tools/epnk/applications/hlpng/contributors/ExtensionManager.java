package org.pnml.tools.epnk.applications.hlpng.contributors;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.IUserExtensions;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;


public class ExtensionManager implements IUserExtensions
{
	private Map<String, IEvaluator> handlers = new HashMap<String, IEvaluator>();
	
	public void register(String name, IEvaluator eval)
	{
		this.handlers.put(name, eval);
	}
	
	public Collection<IEvaluator> getEvaluators()
	{
		return this.getHandlers().values();
	}
	
	@Override
	public AbstractValue evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, AbstractValue> assignments) throws UnknownVariableException
	{
		String name = ((UserOperator)term).getDeclaration().getName();
		IEvaluator eval = this.handlers.get(name);
	    return eval.evaluate(term, evaluationManager, assignments);
    }

	public Map<String, IEvaluator> getHandlers()
    {
    	return handlers;
    }
}
