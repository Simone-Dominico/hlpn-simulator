package org.pnml.tools.epnk.applications.hlpng.network.echo;

import java.util.Collection;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class InitiatorsFunction implements IEvaluator
{

	@Override
    public AbstractValue evaluate(Collection<AbstractValue> values,
            Operator operator)
    {
		MSValue msValue = new MSValue();
		msValue.setSort(((UserOperator)operator).getOutputSort());
	    
		for(AbstractValue value : Database.getInitiators())
		{
			msValue.getValues().put(value, 1);
		}
		
	    return msValue;
    }

}
