package org.pnml.tools.epnk.applications.hlpng.presentation.popup;

import java.util.List;

/*
 * Author: Mindaugas Laganeckas
 * Email: s100972@student.dtu.dk
 */

public class AbstractMenuCategory extends AbstractMenuItem
{

	protected List<AbstractMenuItem> items = null;

	public AbstractMenuCategory(String name)
	{
		super(name);
	}

	public List<AbstractMenuItem> getItems()
    {
    	return items;
    }

	public void setItems(List<AbstractMenuItem> items)
    {
    	this.items = items;
    }

}