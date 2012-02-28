package org.pnml.tools.epnk.applications.hlpng.view;

import org.eclipse.draw2d.ColorConstants;
import org.pnml.tools.epnk.applications.hlpng.actions.IState;

public class BlueOverlay implements IState
{
	protected RectangleOverlay overlay = null;
	
	public BlueOverlay(RectangleOverlay overlay)
	{
		this.overlay = overlay;
	}
	
	@Override
	public void handle()
	{
		overlay.setForegroundColor(ColorConstants.blue);
		overlay.setBackgroundColor(ColorConstants.blue);
		overlay.setState(new GreenOverlay(overlay));
	}

}
