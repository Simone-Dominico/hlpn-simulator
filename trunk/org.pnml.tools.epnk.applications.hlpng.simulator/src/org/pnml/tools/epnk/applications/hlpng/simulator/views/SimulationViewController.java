package org.pnml.tools.epnk.applications.hlpng.simulator.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.pnml.tools.epnk.applications.hlpng.resources.ResourceManager;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeStateContainer;
import org.pnml.tools.epnk.applications.hlpng.simulator.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

public class SimulationViewController implements ISimulationViewController
{
	private final SimulationViewController me = this;
	
	protected ISimulator simulator = null;
	protected Display display = null;
	protected List<TableRecord> records = new ArrayList<TableRecord>();
	
	private Action[] actions = null;

	public SimulationViewController()
	{
		this.display = Display.getCurrent();
	}

	@Override
    public void resetRecords(final IRuntimeStateContainer runtimeStates)
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
							getName(firingMode.getTransition()), 
							firingMode.toString()
						};

				final TableRecord record = new TableRecord();
				record.setData(runtimeState);
				record.setText(text);
				
				// registers the record
				this.records.add(record);	
			}
		}
		
		final SimulationView simulationView = getSimulationView();
		if(simulationView != null) 
		{	
			display.asyncExec(new Runnable()
			{
				public void run()
				{
					simulationView.resetRecords(me, records);
				}
			});
		}
	}
	
	private static String getName(Transition t)
	{
		if(t.getName() != null)
		{
			return t.getName().getText();
		}
		return t.getId();
	}
	
	@Override
    public void record(final IRuntimeState runtimeState)
	{
		FiringMode firingMode = runtimeState.getFiringMode();
		
		final String[] text = new String[] 
				{
					getName(firingMode.getTransition()), 
					firingMode.toString()
				};

		final TableRecord record = new TableRecord();
		record.setData(runtimeState);
		record.setText(text);
		
		// registers the record
		this.records.add(record);
		
		final SimulationView simulationView = getSimulationView();
		if(simulationView != null) 
		{
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
		// clears the records
		this.records.clear();
					
		final SimulationView simulationView = getSimulationView();
		if(simulationView != null) 
		{
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
		final SimulationView simulationView = getSimulationView();
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
	
	private SimulationView getSimulationView()
    {
		try
		{
    		if(PlatformUI.getWorkbench() != null && 
    				PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null &&
    				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null)
    		{
    			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    			IViewPart view = activePage.findView(SimulationView.ID);
    			
    			if(view instanceof SimulationView)
    			{
    				return (SimulationView) view;	
    			}
    		}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		return null;
    }

	@Override
    public void selectionChanged(SelectionChangedEvent event)
    {
		callback();
    }
	
	private void callback()
	{
		final SimulationView simulationView = getSimulationView();
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
			actions = new Action[1];
			
			// clear
			{
				actions[0] = new Action("Reset")
				{
					public void run()
					{
						simulator.reset();
					}
				};
				actions[0].setToolTipText("Reset");
				{
					ImageDescriptor desc = ResourceManager.getImageDescriptor("icons/reset.png",
							ResourceManager.SIMULATOR_PLUGIN_ID);
					if(desc != null)
					{
						actions[0].setImageDescriptor(desc);	
					}
				}	
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
		final SimulationView simulationView = getSimulationView();
		if(simulationView != null) 
		{
			simulationView.reset(this);
		}
	}
}
