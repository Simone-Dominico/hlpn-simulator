package org.pnml.tools.epnk.applications.hlpng.simulator.views;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public class SimulationViewController
{
	public void record(FiringMode firingMode)
	{
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart view = activePage.findView(SimulationView.ID);

		if (view != null) 
		{
			SimulationView simulationView = (SimulationView) view;
			simulationView.record(firingMode);
		}
	}
}
