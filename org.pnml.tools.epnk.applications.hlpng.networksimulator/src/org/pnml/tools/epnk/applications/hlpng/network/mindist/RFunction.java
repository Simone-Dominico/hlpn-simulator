package org.pnml.tools.epnk.applications.hlpng.network.mindist;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.network.AbstractInputFunction;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.utils.NodeWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class RFunction extends AbstractInputFunction
{
	public RFunction(List<NodeWrapper> nodes)
	{
		super(nodes);
	}
	
	@Override
    public AbstractValue evaluate(List<AbstractValue> values,
            Operator operator)
    {
		UserOperator uOp = (UserOperator) operator;
		
		MSValue msValue = new MSValue();
		msValue.setSort(uOp.getOutputSort());
	    
		for(NodeWrapper nw : nodes)
		{
			if(contains(nw.getNode().getCategory(), "R"))
			{
				StringValue strValue = new StringValue();
				strValue.setData(nw.getNode().getLabel());
				strValue.setSort(TermsFactory.eINSTANCE.createUserSort());
				
				msValue.getValues().put(strValue, 1);
			}
		}
		
	    return msValue;
    }
}
