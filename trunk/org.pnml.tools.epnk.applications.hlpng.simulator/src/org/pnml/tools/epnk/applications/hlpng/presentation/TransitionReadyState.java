package org.pnml.tools.epnk.applications.hlpng.presentation;

import org.eclipse.draw2d.ColorConstants;
import org.pnml.tools.epnk.applications.hlpng.presentation.decorations.AbstractRectangleOverlay;
import org.pnml.tools.epnk.applications.hlpng.presentation.decorations.IState;

public class TransitionReadyState implements IState
{
	protected AbstractRectangleOverlay overlay = null;
	
	public TransitionReadyState(AbstractRectangleOverlay overlay)
	{
		this.overlay = overlay;
	}
	
	@Override
	public void handle()
	{
		overlay.setForegroundColor(ColorConstants.green);
		overlay.setBackgroundColor(ColorConstants.green);
		overlay.setState(new TransitionFiredState(overlay));
	}

}
