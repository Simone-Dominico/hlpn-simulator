package org.pnml.tools.epnk.applications.hlpng.contributors;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.IUserExtensions;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
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
    public AbstractValue evaluate(List<AbstractValue> values, Operator operator)
    {
		String name = ((UserOperator)operator).getDeclaration().getName();
		IEvaluator eval = this.handlers.get(name);
	    return eval.evaluate(values, operator);
    }

	public Map<String, IEvaluator> getHandlers()
    {
    	return handlers;
    }
}
