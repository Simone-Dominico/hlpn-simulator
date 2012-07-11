package org.pnml.tools.epnk.applications.hlpng.simulator.views;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuCreator;

/*
 * See http://www.eclipse.org/forums/index.php/m/455864/
 */

public class DropDownAction extends Action implements IMenuCreator
{
	private Menu fMenu;
	private ISimulationViewCallbackHandler callbackHandler = null;
	
	public DropDownAction()
	{
		setMenuCreator(this);
	}

	@Override
	public void dispose()
	{
		if(fMenu != null)
		{
			fMenu.dispose();
			fMenu = null;
		}
	}

	@Override
	public Menu getMenu(Menu parent)
	{
		return null;
	}

	@Override
	public Menu getMenu(Control parent)
	{
		if(fMenu != null) fMenu.dispose();

		fMenu = new Menu(parent);
		{
			Action action = new Action("0 ms")
			{
				public void run()
				{
					if(callbackHandler != null)
					{
						callbackHandler.speedChanged(0);
					}
				}
			};
			addActionToMenu(fMenu, action);
		}
		{
			Action action = new Action("500 ms")
			{
				public void run()
				{
					if(callbackHandler != null)
					{
						callbackHandler.speedChanged(500);
					}
				}
			};

			addActionToMenu(fMenu, action);
		}
		{
			Action action = new Action("1500 ms")
			{
				public void run()
				{
					if(callbackHandler != null)
					{
						callbackHandler.speedChanged(1500);
					}
				}
			};

			addActionToMenu(fMenu, action);
		}
		{
			Action action = new Action("2000 ms")
			{
				public void run()
				{
					if(callbackHandler != null)
					{
						callbackHandler.speedChanged(2000);
					}
				}
			};

			addActionToMenu(fMenu, action);
		}

		return fMenu;
	}

	
	protected void addActionToMenu(Menu parent, Action action)
	{
		ActionContributionItem item = new ActionContributionItem(action);
		item.fill(parent, -1);
	}

	void clear()
	{
		dispose();
	}

	public ISimulationViewCallbackHandler getCallbackHandler()
    {
    	return callbackHandler;
    }

	public void setCallbackHandler(ISimulationViewCallbackHandler callbackHandler)
    {
    	this.callbackHandler = callbackHandler;
    }
}
