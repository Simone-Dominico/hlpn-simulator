package org.pnml.tools.epnk.applications.hlpng.simulator.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.pnml.tools.epnk.applications.hlpng.resources.ResourceManager;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeStateContainer;
import org.pnml.tools.epnk.applications.hlpng.simulator.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public class SimulationViewController implements ISimulationViewController
{
	private final SimulationViewController me = this;
	
	protected ISimulator simulator = null;
	protected SimulationView simulationView = null;
	protected Display display = null;
	protected List<TableRecord> records = new ArrayList<TableRecord>();
	
	private Action[] actions = null;

	public SimulationViewController()
	{
		init();
	}

	@Override
    public void resetRecords(final IRuntimeStateContainer runtimeStates)
	{
		if(simulationView != null) 
		{
			this.records = new ArrayList<TableRecord>();
			
			for(IRuntimeState runtimeState : runtimeStates)
			{
				FiringMode firingMode = runtimeState.getFiringMode();
				
				// the last state does not have a firing mode assigned
				if(firingMode != null)
				{
					final String[] text = new String[] 
							{
								firingMode.getTransition().getId(), 
								firingMode.toString()
							};

					final TableRecord record = new TableRecord();
					record.setData(runtimeState);
					record.setText(text);
					
					// registers the record
					this.records.add(record);	
				}
			}
			
			display.asyncExec(new Runnable()
			{
				public void run()
				{
					simulationView.resetRecords(me, records);
				}
			});
		}
	}
	
	@Override
    public void record(final IRuntimeState runtimeState)
	{
		if(simulationView != null) 
		{
			FiringMode firingMode = runtimeState.getFiringMode();
			
			final String[] text = new String[] 
					{
						firingMode.getTransition().getId(), 
						firingMode.toString()
					};

			final TableRecord record = new TableRecord();
			record.setData(runtimeState);
			record.setText(text);
			
			// registers the record
			this.records.add(record);
			display.asyncExec(new Runnable()
			{
				public void run()
				{
					simulationView.record(me, record);
				}
			});
		}
	}
	
	@Override
    public void clear()
	{
		if(simulationView != null) 
		{
			// clears the records
			this.records.clear();
			display.syncExec(new Runnable()
			{
				public void run()
				{
					simulationView.clear(me);
				}
			});
		}
	}

	@Override
    public void setCurrent()
	{
		if(simulationView != null) 
		{
			simulationView.setCurrentController(me);
			
			display.asyncExec(new Runnable()
			{
				public void run()
				{
					simulationView.resetRecords(me, records);
				}
			});
		}
	}
	
	private void init()
    {
		try
		{
    		if(PlatformUI.getWorkbench() != null && 
    				PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null &&
    				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null &&
    				simulationView == null)
    		{
    			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    			IViewPart view = activePage.findView(SimulationView.ID);
    			
    			if(view instanceof SimulationView)
    			{
    				simulationView = (SimulationView) view;	
    			}
    		}
    		if(display == null)
    		{
    			display = Display.getCurrent();
    		}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
    }

    public void pauseChanged(long pause)
    {
    	if(simulator != null)
    	{
    		this.simulator.setSimulationPause(pause);	
    	}
    }
	
	@Override
    public void selectionChanged(SelectionChangedEvent event)
    {
		callback();
    }
	
	private void callback()
	{
		if(simulationView != null)
		{
			Object data = currentData(simulationView.getViewer());
			if(data != null && simulator != null)
			{
				simulator.show((IRuntimeState)data);
			}	
		}
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
	
	@Override
	public Action[] getActions()
	{
		if(actions == null)
		{
			actions = new Action[2];
			
			// speed management action
			{
				actions[0] = new DropDownAction(this);
				actions[0].setToolTipText("Animation speed");
				
				ImageDescriptor desc = ResourceManager.getImageDescriptor("icons/speed.png",
						ResourceManager.SIMULATOR_PLUGIN_ID);
				if(desc != null)
				{
					actions[0].setImageDescriptor(desc);	
				}
			}
			// clear
			{
				actions[1] = new Action("Clear all")
				{
					public void run()
					{
						if(simulationView != null)
						{
							simulationView.getViewer().getTable().removeAll();
							records.clear();	
						}
					}
				};
				actions[1].setToolTipText("Clear all");
				actions[1].setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				        .getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));	
			}
		}
		return actions;
	}

	@Override
	public void setSimulator(ISimulator simulator)
    {
    	this.simulator = simulator;
    }
	
	@Override
	public void shutDown()
	{
		clear();
		if(simulationView != null) 
		{
			simulationView.setCurrentController(null);
		}
	}
}
