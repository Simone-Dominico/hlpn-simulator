/*******************************************************************************
 * Copyright (c) 2012 Mindaugas Laganeckas.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Mindaugas Laganeckas - initial API and implementation
 ******************************************************************************/
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
		overlay.setState(new TransitionSelectedState(overlay));
	}

}
