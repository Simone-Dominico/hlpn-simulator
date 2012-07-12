package org.pnml.tools.epnk.applications.hlpng.presentation.selection;

import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.pnml.tools.epnk.applications.hlpng.presentation.actions.IActionProvider;
import org.pnml.tools.epnk.applications.hlpng.simulator.ISimulator;

public class SelectionHandler implements SelectionListener, MouseListener
{	
	protected ISimulator simulator = null;
	
	public SelectionHandler(ISimulator simulator)
	{
		this.simulator = simulator;
	}
	
	@Override
    public void widgetSelected(SelectionEvent e)
    {
		if(e.getSource() instanceof MenuItem)
		{
			MenuItem item = (MenuItem) e.getSource();
			
			IActionProvider actionProvider = (IActionProvider) item.getData(PopupMenu.OWNER);
			PopupMenuItem action = (PopupMenuItem) item.getData(PopupMenu.DATA);
			actionProvider.executeAction(action);
		}
    }

	@Override
    public void widgetDefaultSelected(SelectionEvent e){}
	
	@Override
	public void mouseReleased(MouseEvent arg0){}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		// left-click
		if(e.button == 1)
		{
			if(e.getSource() instanceof IActionProvider && !simulator.isAutoModeEnabled())
			{
				final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow(); 
				final Shell shell = window.getShell();
				IActionProvider handler = (IActionProvider)e.getSource();
				if(handler.getActions().size() > 0)
				{
					new PopupMenu(shell, SWT.POP_UP, handler.getActions(), this, handler);
				}
			}
		}
	}
	
	@Override
	public void mouseDoubleClicked(MouseEvent arg0)
	{
		System.out.println(SelectionHandler.class + ": mouseDoubleClicked");
	}
}
