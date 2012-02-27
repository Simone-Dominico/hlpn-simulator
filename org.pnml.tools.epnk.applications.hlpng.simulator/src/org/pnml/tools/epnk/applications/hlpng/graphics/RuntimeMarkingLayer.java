package org.pnml.tools.epnk.applications.hlpng.graphics;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;


public class RuntimeMarkingLayer extends Layer
{	
	private IFigure coloredMarking = null;
	private Label label = null;
	
	public RuntimeMarkingLayer(IFigure coloredMarking, Label label)
	{
		this.label = label;
		this.coloredMarking = coloredMarking;
		
		XYLayout layout = new XYLayout();
		layout.setConstraint(label, label.getBounds());
		layout.setConstraint(coloredMarking, coloredMarking.getBounds());

		this.setLayoutManager(layout);
		
		this.add(label);
		this.add(coloredMarking);
	}

	@Override
    public Rectangle getBounds()
    {
		int margin = 0;
		
		Rectangle lBounds = label.getBounds();
		Rectangle fBounds = coloredMarking.getBounds();
		
		int rootX = Math.min(fBounds.x, lBounds.x) - margin;
		int rootY = Math.min(fBounds.y, lBounds.y) - margin;
		
		int rootWidth = Math.max(lBounds.x + lBounds.width,
				fBounds.x + fBounds.width) - rootX + margin;
		int rootHeight = Math.max(lBounds.y + lBounds.height,
				fBounds.y + fBounds.height) - rootY + margin;
		
		return new Rectangle(rootX, rootY, rootWidth, rootHeight);
    }
	
	
}
