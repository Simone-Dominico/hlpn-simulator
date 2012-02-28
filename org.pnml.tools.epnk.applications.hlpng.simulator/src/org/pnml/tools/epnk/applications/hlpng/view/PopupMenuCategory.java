package org.pnml.tools.epnk.applications.hlpng.view;

import java.util.ArrayList;
import java.util.List;

public class PopupMenuCategory extends PopupMenuItem
{
	private List<PopupMenuItem> items = null;
	
	public List<PopupMenuItem> getItems()
    {
    	return items;
    }

	public void setItems(List<PopupMenuItem> items)
    {
    	this.items = items;
    }

	public PopupMenuCategory(String name)
    {
	    super(name);
	    
	    this.items = new ArrayList<PopupMenuItem>();
    }

	
}
