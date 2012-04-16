package org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.consensus.MFunction;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.consensus.RBFunction;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.consensus.RFFunction;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.consensus.UFunction;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.echo.InitiatorsFunction;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.echo.M1Function;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.echo.M2Function;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.echo.OthersFunction;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.mindist.IFunction;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.mindist.NFunction;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.mindist.RFunction;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class UserExtensionManager implements IEvaluator
{
	private Map<String, IEvaluator> handlers = null;
	
	public UserExtensionManager()
	{
		// min dist
		this.handlers = new HashMap<String, IEvaluator>();
		this.handlers.put("R", new RFunction());
		this.handlers.put("I", new IFunction());
		this.handlers.put("N", new NFunction());
		
		// consensus in networks
		this.handlers.put("U", new UFunction());
		this.handlers.put("M", new MFunction());
		this.handlers.put("RF", new RFFunction());
		this.handlers.put("RB", new RBFunction());
		
		// echo
		this.handlers.put("INITIATORS", new InitiatorsFunction());
		this.handlers.put("OTHERS", new OthersFunction());
		this.handlers.put("M1", new M1Function());
		this.handlers.put("M2", new M2Function());
	}

	@Override
    public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
    {
		String name = ((UserOperator)operator).getDeclaration().getName();
		IEvaluator eval = this.handlers.get(name);
	    return eval.evaluate(values, operator);
    }
}
