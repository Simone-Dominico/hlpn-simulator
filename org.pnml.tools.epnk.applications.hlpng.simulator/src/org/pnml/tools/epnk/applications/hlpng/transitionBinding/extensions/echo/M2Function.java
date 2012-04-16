package org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.echo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class M2Function implements IEvaluator
{
	@Override
    public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
    {
		UserOperator uOp = (UserOperator) operator;
		
		MSValue msValue = new MSValue();
		msValue.setSort(uOp.getOutputSort());
	    
		List<AbstractValue> list = new ArrayList<AbstractValue>(values);
		for(AbstractValue value : Database.getM2(list.get(0)))
		{
			msValue.getValues().put(value, 1);
		}
		
	    return msValue;
    }
}
