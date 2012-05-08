package org.pnml.tools.epnk.applications.hlpng.network.consensus;

import java.util.ArrayList;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class RBFunction implements IEvaluator
{
	@Override
    public AbstractValue evaluate(List<AbstractValue> values, Operator operator)
    {
		UserOperator uOp = (UserOperator) operator;
		
		MSValue msValue = new MSValue();
		msValue.setSort(uOp.getOutputSort());
	    
		List<AbstractValue> list = new ArrayList<AbstractValue>(values);
		for(AbstractValue value : Database.getMessagesByReceiver(list.get(0)))
		{
			msValue.getValues().put(value, 1);
		}
		
	    return msValue;
    }
}
