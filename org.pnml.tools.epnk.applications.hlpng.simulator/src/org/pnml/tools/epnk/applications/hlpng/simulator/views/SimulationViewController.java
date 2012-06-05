package org.pnml.tools.epnk.applications.hlpng.simulator.views;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.pnml.tools.epnk.applications.hlpng.simulator.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public class SimulationViewController
{
	private ISimulator simulator = null;
	
	public SimulationViewController(ISimulator simulator)
	{
		this.simulator = simulator;
	}

	public void record(FiringMode firingMode, int index)
	{
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart view = activePage.findView(SimulationView.ID);

		if (view != null) 
		{
			SimulationView simulationView = (SimulationView) view;
			simulationView.setController(this);
			
			String[] text = new String[] {firingMode.getTransition().getId(), firingMode.toString()};

			simulationView.record(text, index);
		}
	}
	
	public void itemSelected(Object data)
	{
		simulator.show((Integer)data);
	}
}
