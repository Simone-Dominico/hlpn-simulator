package org.pnml.tools.epnk.applications.hlpng.presentation.states;

import org.eclipse.draw2d.ColorConstants;
import org.pnml.tools.epnk.applications.hlpng.presentation.RectangleOverlay;

public class BlueOverlayState implements IState
{
	protected RectangleOverlay overlay = null;
	
	public BlueOverlayState(RectangleOverlay overlay)
	{
		this.overlay = overlay;
	}
	
	@Override
	public void handle()
	{
		overlay.setForegroundColor(ColorConstants.blue);
		overlay.setBackgroundColor(ColorConstants.blue);
		overlay.setState(new GreenOverlayState(overlay));
	}

}
