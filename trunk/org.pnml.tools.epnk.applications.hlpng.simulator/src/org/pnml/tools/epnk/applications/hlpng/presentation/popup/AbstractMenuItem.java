package org.pnml.tools.epnk.applications.hlpng.presentation.popup;

import org.pnml.tools.epnk.applications.hlpng.presentation.actions.IAction;

/*
 * Author: Mindaugas Laganeckas
 * Email: s100972@student.dtu.dk
 */

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