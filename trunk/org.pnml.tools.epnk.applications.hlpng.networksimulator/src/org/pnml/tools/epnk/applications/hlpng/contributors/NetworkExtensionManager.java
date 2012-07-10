package org.pnml.tools.epnk.applications.hlpng.contributors;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.network.InputFunction;
import org.pnml.tools.epnk.applications.hlpng.network.consensus.MFunction;
import org.pnml.tools.epnk.applications.hlpng.network.consensus.RBFunction;
import org.pnml.tools.epnk.applications.hlpng.network.consensus.RFFunction;
import org.pnml.tools.epnk.applications.hlpng.network.echo.M1Function;
import org.pnml.tools.epnk.applications.hlpng.network.echo.M2Function;
import org.pnml.tools.epnk.applications.hlpng.network.mindist.NFunction;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.IUserExtensions;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.arbitrarydeclarations.ArbitrarySort;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserSort;


public class NetworkExtensionManager implements IUserExtensions
{
	private Map<String, IEvaluator> handlers = new HashMap<String, IEvaluator>();
	private InputFunction inputFunction = null;
	
	public NetworkExtensionManager()
	{
		this.inputFunction = new InputFunction();
		
		// min dist
        register("N", new NFunction());
        register("R", inputFunction);
        
        // consensus in networks
        register("U", inputFunction);
        register("M", new MFunction());
        register("RF", new RFFunction());
        register("RB", new RBFunction());

        // echo
        register("Initiators", inputFunction);
        register("Others", inputFunction);
        register("M1", new M1Function());
        register("M2", new M2Function());
	}
	
	public void register(String name, IEvaluator eval)
	{
		this.handlers.put(name, eval);
	}
	
	public Collection<IEvaluator> getEvaluators()
	{
		return this.getHandlers().values();
	}
	
	public IEvaluator getEvaluator(String name)
	{
		return this.handlers.get(name);
	}
	
	@Override
	public IValue evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, IValue> assignments) throws UnknownVariableException
	{
		String name = ((UserOperator)term).getDeclaration().getName();
		IEvaluator eval = this.handlers.get(name);
		
	    return eval.evaluate(term, evaluationManager, assignments);
    }

	public Map<String, IEvaluator> getHandlers()
    {
    	return handlers;
    }

	@Override
    public String validate(Object obj)
    {
		if(obj instanceof UserOperator)
		{
			String name = ((UserOperator)obj).getDeclaration().getName();
			IEvaluator eval = this.handlers.get(name);
			if(eval != null)
			{
				return null;
			}
			return "User defined\n" + name;
		}
		if(obj instanceof UserSort)
		{
			UserSort userSort = (UserSort) obj;
			if(userSort.getDeclaration() instanceof ArbitrarySort &&
					userSort.getDeclaration().getName().equals("AGENT"))
			{
				return null;
			}
			else
			{
				return "User defined sort\n" + userSort.getDeclaration().getName();
			}
		}
		return null;
    }

	public InputFunction getInputFunction()
    {
    	return inputFunction;
    }

	@Override
    public IValue evaluate(Sort sort)
    {
	    if(sort instanceof UserSort && 
	    		((UserSort)sort).getDeclaration() instanceof ArbitrarySort &&
	    		((UserSort)sort).getDeclaration().getName().equals("AGENT"))
	    {
	    	return inputFunction.evaluate(sort);
	    }
	    return null;
    }
}
