package org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Add;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.All;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.Subtract;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserSort;

public class MultisetsEval implements IEvaluator
{
	private ISortEvaluator sortEvaluator = null;
	private RuntimeValueFactory factory = null;
	
	public MultisetsEval(RuntimeValueFactory factory)
	{
		this.factory = factory; 
	}
	
	@Override
	public ITermWrapper evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, IValue> assignments) throws UnknownVariableException
	{
		Pair<Boolean, ITermWrapper> p = evaluationManager.evalSubterms(term, assignments);
		if(!p.getKey())
		{
			return p.getValue();
		}
			
		final List<ITermWrapper> values = p.getValue().getSubterms();

		if(term instanceof NumberOf)
		{
			IMSValue set = this.factory.createMSValue();
			set.setSort(term.getSort());

			int multiplicity = ((NumberValue)values.get(0)).getN();
			if(values.get(1) instanceof IMSValue)
			{
				for(Entry<IValue, Integer> value : ((IMSValue)values.get(1)).entrySet())
				{
					IMSValue msValue = this.factory.createMSValue();
					msValue.setSort(value.getKey().getSort());
					msValue.put(value.getKey(), value.getValue());
					
					set = AbstractValueMath.add(set, msValue, multiplicity, this.factory);
				}
			}
			else
			{
				set = AbstractValueMath.add(set, (IValue)values.get(1), multiplicity, this.factory);	
			}

		    return set;
		}
		if(term instanceof Add)
		{
			IMSValue set = this.factory.createMSValue();
			set.setSort(term.getSort());
			
			for(ITermWrapper value : values)
			{
				IMSValue ms = (IMSValue) value;
				set = AbstractValueMath.append(set, ms, this.factory);
			}
			return set;
		}
		if(term instanceof Subtract)
		{
			IMSValue set = (IMSValue) values.get(0);
			
			for(int i = 1; i < values.size(); i++)
			{
				IMSValue ms = (IMSValue) values.get(i);
				set = AbstractValueMath.subtract(set, ms, this.factory);
			}
			return set;
		}
		if(term instanceof All)
		{
			All allOp = (All) term;
			return sortEvaluator.evaluate(allOp.getRefsort());
		}
		return null;
	}

	public void setSortEvaluator(
            ISortEvaluator dataTypeEvaluationManager)
    {
    	this.sortEvaluator = dataTypeEvaluationManager;
    }

	@Override
    public String validate(Object term)
    {
		if(term instanceof NumberOf)
		{
			return null;
		}
		if(term instanceof Add)
		{
			return null;
		}
		if(term instanceof Subtract)
		{
			return null;
		}
		if(term instanceof All)
		{
			All all = (All)term;
			if(sortEvaluator == null)
			{
				if(all.getRefsort() instanceof UserSort)
				{
					String name = ((UserSort)all.getRefsort()).
							getDeclaration().getName();
					return "User defined sort\n" + name;
				}
				return "all:" + all.getRefsort().getClass().getName();
			}
			
			return sortEvaluator.validate(all);
		}
		return term.getClass().toString();
    }
}
