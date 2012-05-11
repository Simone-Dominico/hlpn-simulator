package org.pnml.tools.epnk.applications.hlpng.network;

import java.util.List;

import networkmodel.Category;
import networkmodel.Node;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class InputFunction implements IEvaluator
{
	protected List<Category> categories = null;
	
	public InputFunction(List<Category> categories)
	{
		this.categories = categories;
	}
	
	@Override
    public AbstractValue evaluate(List<AbstractValue> values, Operator operator)
    {
		UserOperator uOp = (UserOperator) operator;
		
		MSValue msValue = new MSValue();
		msValue.setSort(uOp.getOutputSort());
	    
		for(Node n : getNodes(uOp.getName(), categories))
		{
			StringValue sValue = new StringValue();
			sValue.setData(n.getLabel());
			sValue.setSort(TermsFactory.eINSTANCE.createUserSort());
			
			msValue.getValues().put(sValue, 1);
		}
		return msValue;
    }
	
	protected List<Node> getNodes(String categoryName, List<Category> categories)
	{
		for(Category c : categories)
		{
			if(c.getName().equals(categoryName))
			{
				return c.getNode();
			}
		}
		return null;
	}
}
