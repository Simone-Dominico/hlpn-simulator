package org.pnml.tools.epnk.applications.hlpng.view;

import org.eclipse.draw2d.ColorConstants;
import org.pnml.tools.epnk.applications.hlpng.actions.IState;

public class GreenOverlay implements IState
{
	protected RectangleOverlay overlay = null;
	
	public GreenOverlay(RectangleOverlay overlay)
	{
		this.overlay = overlay;
	}
	
	@Override
	public void handle()
	{
		overlay.setForegroundColor(ColorConstants.green);
		overlay.setBackgroundColor(ColorConstants.green);
		overlay.setState(new BlueOverlay(overlay));
	}

}
