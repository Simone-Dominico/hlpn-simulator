package org.pnml.tools.epnk.applications.hlpng.simulator.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeStateContainer;
import org.pnml.tools.epnk.applications.hlpng.simulator.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public class SimulationViewController implements ISimulationViewController,
        ISimulationViewCallbackHandler
{
	private final SimulationViewController me = this;
	
	private ISimulator simulator = null;
	private SimulationView simulationView = null;
	private Display display = null;
	
	private List<TableRecord> records = new ArrayList<TableRecord>();

	public SimulationViewController(ISimulator simulator)
	{
		this.simulator = simulator;
		init();
	}

	/* (non-Javadoc)
     * @see org.pnml.tools.epnk.applications.hlpng.simulator.views.ISumulationViewController#resetRecords(org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeStateContainer)
     */
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
	
	/* (non-Javadoc)
     * @see org.pnml.tools.epnk.applications.hlpng.simulator.views.ISumulationViewController#record(org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState)
     */
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
    public void clearFromView()
	{
		this.records.clear();
	}
	
	@Override
    public void clear()
	{
		if(simulationView != null) 
		{
			// clears the records
			this.records.clear();
			display.asyncExec(new Runnable()
			{
				public void run()
				{
					simulationView.clear(me);
				}
			});
		}
	}
	
	@Override
    public void itemSelected(Object data)
	{
		simulator.show((IRuntimeState)data);
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

	@Override
    public void speedChanged(int speed)
    {
	    this.simulator.setSimulationPause(speed);
    }

}
