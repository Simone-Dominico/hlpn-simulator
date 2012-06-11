package org.pnml.tools.epnk.applications.hlpng.simulator.views;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.pnml.tools.epnk.applications.hlpng.simulator.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public class SimulationViewController
{
	private ISimulator simulator = null;
	private IViewPart view = null;
	private Display display = null;

	public SimulationViewController(ISimulator simulator)
	{
		this.simulator = simulator;
	}

	public void record(FiringMode firingMode, final int index)
	{
		if (getView() != null) 
		{
			final SimulationView simulationView = (SimulationView) getView();
			simulationView.setController(this);
			
			final String[] text = new String[] {firingMode.getTransition().getId(), firingMode.toString()};

			getDisplay().asyncExec(new Runnable()
			{
				public void run()
				{
					simulationView.record(text, index);
				}
			});
		}
	}
	
	public void clear()
	{
		if (getView() != null) 
		{
			final SimulationView simulationView = (SimulationView) getView();
			simulationView.setController(this);

			getDisplay().asyncExec(new Runnable()
			{
				public void run()
				{
					simulationView.clear();
				}
			});
		}
	}
	
	public void itemSelected(Object data)
	{
		simulator.show((Integer)data);
	}

	public IViewPart getView()
    {
		if(view == null)
		{
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			view = activePage.findView(SimulationView.ID);
		}
    	return view;
    }

	public Display getDisplay()
    {
		if(display == null)
		{
			display = Display.getCurrent();
		}
    	return display;
    }
}
