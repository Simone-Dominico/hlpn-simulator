package org.pnml.tools.epnk.applications.hlpng.presentation.selection;

import org.pnml.tools.epnk.applications.hlpng.presentation.actions.IAction;

public class AbstractMenuItem implements IAction
{

	protected String name = null;

	public AbstractMenuItem(String name)
	{
		this.name = name;
	}

	public String getName()
    {
    	return name;
    }

	public void setName(String name)
    {
    	this.name = name;
    }

}