package org.pnml.tools.epnk.applications.hlpng.view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.pnml.tools.epnk.applications.hlpng.actions.IActionProvider;

public class PopupMenu
{
	public static final String OWNER = "owner";
	public static final String DATA = "data";
	
	protected SelectionListener selectionListener = null;
	
	public PopupMenu(Decorations parent, int style, 
			List<PopupMenuItem> categories, SelectionListener selectionListener,
			IActionProvider owner)
    {
		this.selectionListener = selectionListener;
		
		Menu root = new Menu(parent, style);
	    
		root.setVisible(false);
	    
	    for(PopupMenuItem item : categories)
	    {
	    	createMenuItems(item, root, parent, selectionListener, owner);	
	    }
	    
	    root.setVisible(true);
    }
	
	private static void createMenuItems(PopupMenuItem item, Menu menu, 
			final Decorations parent, final SelectionListener selectionListener,
			IActionProvider owner)
	{
		if(item instanceof PopupMenuCategory)
		{
			PopupMenuCategory category = (PopupMenuCategory) item;
			
			MenuItem categoryItem = new MenuItem(menu, SWT.CASCADE);
			categoryItem.setText(category.getName());
			
			Menu subMenu = new Menu(parent, SWT.DROP_DOWN);
			categoryItem.setMenu(subMenu);
			
			for(PopupMenuItem child : category.getItems())
			{
				createMenuItems(child, subMenu, parent, selectionListener, owner);
			}
			subMenu.setVisible(false);
		}
		else
		{
			MenuItem subItem = new MenuItem(menu, SWT.CASCADE);
			subItem.setText(item.getName());
			subItem.setData(OWNER, owner);
			subItem.setData(DATA, item);
			subItem.addSelectionListener(selectionListener);
		}
	}

}
