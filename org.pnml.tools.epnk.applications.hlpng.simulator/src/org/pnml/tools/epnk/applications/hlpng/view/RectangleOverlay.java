package org.pnml.tools.epnk.applications.hlpng.view;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.widgets.Menu;

public class RectangleOverlay extends RectangleFigure
{
	final protected IFigure figure;

	public RectangleOverlay(AbstractGraphicalEditPart editPart, final Menu menu)
	{
		super();
		this.figure = editPart.getFigure();
		
		this.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent arg0){}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				// left-click
				if(e.button == 1)
				{
					menu.setVisible(true);
				}
			}
			
			@Override
			public void mouseDoubleClicked(MouseEvent arg0)
			{
				System.out.println(RectangleOverlay.class + ": mouseDoubleClicked");
			}
		});

	}
	
	@Override
	protected void fillShape(Graphics graphics)
	{
		graphics.pushState();
		graphics.setForegroundColor(ColorConstants.green);
		graphics.setBackgroundColor(ColorConstants.green);
		graphics.setAlpha(150);
		graphics.setLineWidth(4);
		Rectangle bounds = getBounds();
		graphics.fillRectangle(bounds);
		graphics.popState();
	}

	@Override
	public Rectangle getBounds()
	{
		int shift = 3;
		Rectangle bounds = figure.getBounds();
		int x = bounds.x - shift;
		int y = bounds.y - shift;
		int width = bounds.width + 2 * shift;
		int height = bounds.height + 2 * shift;
		bounds = new Rectangle(x, y, width, height);
		return bounds;
	}

}