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
package org.pnml.tools.epnk.applications.hlpng.presentation.decorations;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

public class TopRightLabel extends Label
{
	protected IFigure figure = null;
	
	protected int textWidth;
	protected int textHeight;
	
	protected Color textColor = null;
	
	public TopRightLabel(Font font, String text, IFigure figure)
	{
		super(text);
		this.setFont(font);
		
		this.textWidth = this.getPreferredSize().width;
		this.textHeight = this.getPreferredSize().height;

		this.figure = figure;
		
		this.textColor = new Color(font.getDevice(), 0, 0, 255);
		
		this.setForegroundColor(textColor);
	}
	
	@Override
	public Rectangle getBounds()
	{
		if(figure == null)
		{
			return super.getBounds();
		}

		int hGap = 0;
		int vGap = 0;
		
		Rectangle bounds = new Rectangle(
				figure.getBounds().x + figure.getBounds().width + hGap, 
				figure.getBounds().y - textHeight - vGap, 
				textWidth, textHeight);

		return bounds;
	}
}
