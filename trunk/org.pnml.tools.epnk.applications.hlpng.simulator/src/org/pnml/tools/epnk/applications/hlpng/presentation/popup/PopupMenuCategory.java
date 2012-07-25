package org.pnml.tools.epnk.applications.hlpng.presentation.popup;

import java.util.ArrayList;

/*
 * Author: Mindaugas Laganeckas
 * Email: s100972@student.dtu.dk
 */

public class PopupMenuCategory extends AbstractMenuCategory
{
	public PopupMenuCategory(String name)
    {
	    super(name);
	    
	    this.items = new ArrayList<AbstractMenuItem>();
    }
}
