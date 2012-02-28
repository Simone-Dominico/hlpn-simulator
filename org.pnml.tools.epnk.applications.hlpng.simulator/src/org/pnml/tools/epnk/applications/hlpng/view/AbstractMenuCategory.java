package org.pnml.tools.epnk.applications.hlpng.view;

import java.util.List;

public class AbstractMenuCategory extends AbstractMenuItem
{

	protected List<PopupMenuItem> items = null;

	public AbstractMenuCategory(String name)
	{
		super(name);
	}

	public List<PopupMenuItem> getItems()
    {
    	return items;
    }

	public void setItems(List<PopupMenuItem> items)
    {
    	this.items = items;
    }

}