package org.pnml.tools.epnk.applications.hlpng.presentation.popup;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.pnml.tools.epnk.applications.hlpng.presentation.actions.IAction;
import org.pnml.tools.epnk.applications.hlpng.presentation.actions.IActionProvider;

/*
 * Author: Mindaugas Laganeckas
 * Email: s100972@student.dtu.dk
 */

public class PopupMenu
{
	public static final String OWNER = "owner";
	public static final String DATA = "data";
	
	protected SelectionListener selectionListener = null;
	
	public PopupMenu(Decorations parent, int style, 
			List<IAction> categories, SelectionListener selectionListener,
			IActionProvider owner)
    {
		this.selectionListener = selectionListener;
		
		Menu root = new Menu(parent, style);
	    
		root.setVisible(false);
	    
	    for(IAction action : categories)
	    {
	    	AbstractMenuItem item = (AbstractMenuItem) action;
	    	createMenuItems(item, root, parent, selectionListener, owner);	
	    }
	    
	    root.setVisible(true);
    }
	
	private static void createMenuItems(AbstractMenuItem item, Menu menu, 
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
			
			for(AbstractMenuItem child : category.getItems())
			{
				createMenuItems(child, subMenu, parent, selectionListener, owner);
			}
			subMenu.setVisible(false);
		}
		else if(item instanceof AbstractMenuItem)
		{
			MenuItem subItem = new MenuItem(menu, SWT.CASCADE);
			subItem.setText(item.getName());
			subItem.setData(OWNER, owner);
			subItem.setData(DATA, item);
			subItem.addSelectionListener(selectionListener);
		}
	}

}
