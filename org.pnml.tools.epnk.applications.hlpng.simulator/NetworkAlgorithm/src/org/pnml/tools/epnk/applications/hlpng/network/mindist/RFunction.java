package org.pnml.tools.epnk.applications.hlpng.network.mindist;

import java.util.Collection;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class RFunction implements IEvaluator
{

	@Override
    public AbstractValue evaluate(Collection<AbstractValue> values,
            Operator operator)
    {
		UserOperator uOp = (UserOperator) operator;
		
		MSValue msValue = new MSValue();
		msValue.setSort(uOp.getOutputSort());
	    
		for(int i = 'A'; i < 'C'; i++)
		{
			StringValue strValue = new StringValue();
			strValue.setData(String.valueOf((char)i));
			strValue.setSort(TermsFactory.eINSTANCE.createUserSort());
			
			msValue.getValues().put(strValue, 1);
		}
		
	    return msValue;
    }

}
