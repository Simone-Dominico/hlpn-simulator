package org.pnml.tools.epnk.applications.hlpng.presentation.states;

import org.eclipse.draw2d.ColorConstants;
import org.pnml.tools.epnk.applications.hlpng.presentation.RectangleOverlay;

public class GreenOverlayState implements IState
{
	protected RectangleOverlay overlay = null;
	
	public GreenOverlayState(RectangleOverlay overlay)
	{
		this.overlay = overlay;
	}
	
	@Override
	public void handle()
	{
		overlay.setForegroundColor(ColorConstants.green);
		overlay.setBackgroundColor(ColorConstants.green);
		overlay.setState(new BlueOverlayState(overlay));
	}

}
