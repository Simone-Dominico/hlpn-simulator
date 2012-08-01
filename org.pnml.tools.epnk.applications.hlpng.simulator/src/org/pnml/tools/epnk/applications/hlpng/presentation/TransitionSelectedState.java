package org.pnml.tools.epnk.applications.hlpng.presentation;

import org.eclipse.draw2d.ColorConstants;
import org.pnml.tools.epnk.applications.hlpng.presentation.decorations.AbstractRectangleOverlay;
import org.pnml.tools.epnk.applications.hlpng.presentation.decorations.IState;

public class TransitionSelectedState implements IState
{
	protected AbstractRectangleOverlay overlay = null;
	
	public TransitionSelectedState(AbstractRectangleOverlay overlay)
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
