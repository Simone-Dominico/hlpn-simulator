package org.pnml.tools.epnk.applications.hlpng.presentation;

import org.eclipse.draw2d.ColorConstants;
import org.pnml.tools.epnk.applications.presentation.decorations.AbstractRectangleOverlay;
import org.pnml.tools.epnk.applications.presentation.decorations.IState;

public class TransitionFiredState implements IState
{
	protected AbstractRectangleOverlay overlay = null;
	
	public TransitionFiredState(AbstractRectangleOverlay overlay)
	{
		this.overlay = overlay;
	}
	
	@Override
	public void handle()
	{
		overlay.setForegroundColor(ColorConstants.blue);
		overlay.setBackgroundColor(ColorConstants.blue);
		overlay.setState(new TransitionReadyState(overlay));
	}

}
