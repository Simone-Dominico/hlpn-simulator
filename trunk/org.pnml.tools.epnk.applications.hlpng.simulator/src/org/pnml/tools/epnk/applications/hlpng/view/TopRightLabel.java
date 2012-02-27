package org.pnml.tools.epnk.applications.hlpng.view;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;

public class TopRightLabel extends Label
{
	protected IFigure figure = null;
	
	protected int textWidth;
	protected int textHeight;
	
	public TopRightLabel(Font font, String text, IFigure figure)
	{
		super(text);
		this.setFont(font);
		
		this.textWidth = this.getPreferredSize().width;
		this.textHeight = this.getPreferredSize().height;

		this.figure = figure;
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
