package org.pnml.tools.epnk.applications.hlpng.utils;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.pnml.tools.epnk.applications.hlpng.simulator.views.SimulationViewController;

public class VisualSimulationViewController extends SimulationViewController
{
	private Action[] actions = null;
	
	public VisualSimulationViewController()
	{
		super();
	}
	
	@Override
	public Action[] getActions()
	{
		if(actions == null)
		{
			actions = new Action[1];
			
			// clear
			{
				actions[0] = new Action("Clear all")
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
				actions[0].setToolTipText("Clear all");
				actions[0].setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				        .getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));	
			}
		}
		return actions;
	}
}
