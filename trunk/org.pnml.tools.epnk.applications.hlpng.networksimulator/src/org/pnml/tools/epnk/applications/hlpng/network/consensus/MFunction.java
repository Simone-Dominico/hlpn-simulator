package org.pnml.tools.epnk.applications.hlpng.network.consensus;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class MFunction implements IEvaluator
{
	@Override
    public AbstractValue evaluate(List<AbstractValue> values, Operator operator)
    {
		UserOperator uOp = (UserOperator) operator;
		
		MSValue msValue = new MSValue();
		msValue.setSort(uOp.getOutputSort());
	    
		for(AbstractValue value : Database.getMessages())
		{
			msValue.getValues().put(value, 1);
		}
		
	    return msValue;
    }

}
