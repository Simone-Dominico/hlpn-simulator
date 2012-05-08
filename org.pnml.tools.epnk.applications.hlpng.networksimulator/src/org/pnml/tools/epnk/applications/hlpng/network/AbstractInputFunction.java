package org.pnml.tools.epnk.applications.hlpng.network;

import java.util.List;

import networkmodel.Category;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.utils.NodeWrapper;

public abstract class AbstractInputFunction implements IEvaluator
{
	protected List<NodeWrapper> nodes = null;
	
	public AbstractInputFunction(List<NodeWrapper> wrappers)
	{
		this.nodes = wrappers;
	}
	
	protected boolean contains(List<Category> categories, String name)
	{
		for(Category c : categories)
		{
			if(c.getName().equals(name))
			{
				return true;
			}
		}
		return false;
	}
}
