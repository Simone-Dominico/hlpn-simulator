package org.pnml.tools.epnk.applications.hlpng.simulator.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.simulator.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public class SimulationViewController
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

	public void record(FiringMode firingMode, final IRuntimeState runtimeState)
	{
		if(simulationView != null) 
		{
			final String[] text = new String[] {firingMode.getTransition().getId(), firingMode.toString()};

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
	
	public void clearFromView()
	{
		this.records.clear();
	}
	
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
	
	public void itemSelected(Object data)
	{
		simulator.show((IRuntimeState)data);
	}

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
		if(PlatformUI.getWorkbench() != null && 
				PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null &&
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null)
		{
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IViewPart view = activePage.findView(SimulationView.ID);
			
			if(view instanceof SimulationView)
			{
				simulationView = (SimulationView) view;	
			}
		}
		{
			display = Display.getCurrent();
		}
    }

}
