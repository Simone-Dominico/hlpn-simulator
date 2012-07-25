package org.pnml.tools.epnk.applications.hlpng.presentation.decorations;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

/*
 * Author: Mindaugas Laganeckas
 * Email: s100972@student.dtu.dk
 */

public class LabelLayer extends Layer
{	
	private IFigure figure = null;
	private Label label = null;
	
	public LabelLayer(IFigure figure, Label label)
	{
		this.label = label;
		this.figure = figure;
		
		XYLayout layout = new XYLayout();
		layout.setConstraint(label, label.getBounds());

		this.setLayoutManager(layout);
		
		this.add(label);
	}

	@Override
    public Rectangle getBounds()
    {
		int margin = 0;
		
		Rectangle lBounds = label.getBounds();
		Rectangle fBounds = figure.getBounds();
		
		int rootX = Math.min(fBounds.x, lBounds.x) - margin;
		int rootY = Math.min(fBounds.y, lBounds.y) - margin;
		
		int rootWidth = Math.max(lBounds.x + lBounds.width,
				fBounds.x + fBounds.width) - rootX + margin;
		int rootHeight = Math.max(lBounds.y + lBounds.height,
				fBounds.y + fBounds.height) - rootY + margin;
		
		return new Rectangle(rootX, rootY, rootWidth, rootHeight);
    }
	
	
}
