package org.pnml.tools.epnk.applications.hlpng.simulator.views;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.action.*;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;

public class SimulationView extends ViewPart implements ISelectionListener, ISelectionChangedListener
{
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.pnml.tools.epnk.applications.hlpng.simulator.views.SimulationView";

	private static final String[] columnHead = new String[] { "Transition ID", "Firing mode" };
	private static final int[] columnWidth = new int[] { 100, 500 };
	private static final int[] columnAlignment = new int[] { SWT.LEFT, SWT.LEFT };

	private TableViewer viewer;
	
	private Action clear;
	private Action doubleClickAction;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent)
	{
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
		        | SWT.V_SCROLL);

		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new SimulationViewLabelProvider());
		viewer.setInput(getViewSite());
		
		viewer.addSelectionChangedListener(this);
		
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn[] columns = new TableColumn[columnHead.length];

		for(int i = 0; i < columnHead.length; i++)
		{
			columns[i] = new TableColumn(table, columnAlignment[i]);
			columns[i].setText(columnHead[i]);
			columns[i].setWidth(columnWidth[i]);
		}

		// Create the help context id for the viewer's control
		PlatformUI
		        .getWorkbench()
		        .getHelpSystem()
		        .setHelp(viewer.getControl(),
		                "org.pnml.tools.epnk.applications.hlpng.simulator.viewer");
		makeActions();
		hookContextMenu();
		viewer.addDoubleClickListener(new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				doubleClickAction.run();
			}
		});
		contributeToActionBars();
	}

	private void hookContextMenu()
	{
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener()
		{
			public void menuAboutToShow(IMenuManager manager)
			{
				SimulationView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars()
	{
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager)
	{
		manager.add(clear);
		manager.add(new Separator());
	}

	private void fillContextMenu(IMenuManager manager)
	{
		manager.add(clear);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager)
	{
		manager.add(clear);
	}

	private void makeActions()
	{
		clear = new Action()
		{
			public void run()
			{
				if(currentController != null)
				{
					viewer.getTable().removeAll();
					currentController.clearFromView();
				}
			}
		};
		clear.setText("Clear all");
		clear.setToolTipText("Clear all");
		clear.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
		        .getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));

		doubleClickAction = new Action()
		{
			public void run()
			{
				callback();
			}
		};
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus()
	{
		viewer.getControl().setFocus();
	}

	@Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection){}

	@Override
    public void selectionChanged(SelectionChangedEvent event)
    {
		callback();
    }
	
	private static Object currentData(TableViewer viewer)
	{
		TableItem[] items = viewer.getTable().getSelection();
		if(items != null && items.length > 0)
		{
			return items[0].getData();
		}
		
		return null;
	}
	
	/*
	 * Communication with the Controller
	 */
	private SimulationViewController currentController = null;
	
	public void record(SimulationViewController controller, TableRecord record)
	{
		if(currentController == controller)
		{
			TableItem item = new TableItem(viewer.getTable(), SWT.NONE);
	        item.setText(record.getText());
	        item.setData(record.getData());	
		}
	}
	
	public void resetRecords(SimulationViewController controller, List<TableRecord> records)
	{
		if(currentController == controller)
		{
			viewer.getTable().removeAll();
			
			for(TableRecord record : records)
			{
				TableItem item = new TableItem(viewer.getTable(), SWT.NONE);
		        item.setText(record.getText());
		        item.setData(record.getData());		
			}
		}
	}
	
	public void clear(SimulationViewController controller)
	{
		if(currentController == controller)
		{
			viewer.getTable().removeAll();
		}
	}

	private void callback()
	{
		Object data = currentData(viewer);
		if(data != null && currentController != null)
		{
			currentController.itemSelected(data);
		}
	}
	
	public SimulationViewController getCurrentController()
    {
    	return currentController;
    }

	public void setCurrentController(SimulationViewController currentController)
    {
    	this.currentController = currentController;
    }

}