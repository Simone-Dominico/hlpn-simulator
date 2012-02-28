package org.pnml.tools.epnk.applications.hlpng.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.pnml.tools.epnk.applications.hlpng.actions.IActionProvider;
import org.pnml.tools.epnk.applications.hlpng.actions.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.actions.IState;
import org.pnml.tools.epnk.applications.hlpng.actions.IStateContext;
import org.pnml.tools.epnk.pnmlcoremodel.Arc;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

import runtime.AbstractValue;
import runtime.MSValue;

public class RectangleOverlay extends RectangleFigure implements IStateContext,
	IActionProvider
{
	final protected IFigure figure;
	protected IState currentState = null;
	final protected Transition transition;
	final protected Map<Place, MSValue> runtimeValues;
	final protected ISimulator simulator;

	public RectangleOverlay(final ISimulator simulator, final IFigure figure, 
			final Transition transition, final Map<Place, MSValue> runtimeValues)
	{
		super();
		this.figure = figure;
		this.transition = transition;
		this.runtimeValues = runtimeValues;
		this.simulator = simulator;
		
		currentState = new GreenOverlay(this);
		currentState.handle();
	}
	
	@Override
	protected void fillShape(Graphics graphics)
	{
		graphics.pushState();
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

	@Override
    public void setState(IState state)
    {
	    this.currentState = state;
    }

	@Override
    public void request()
    {
	    this.currentState.handle();
    }

	@Override
    public List<AbstractMenuItem> getActions()
    {
		List<AbstractMenuItem> actions = new ArrayList<AbstractMenuItem>();
		
		for(Arc arc : transition.getIn())
		{
			Place place = (Place)arc.getSource();
			
			AbstractMenuItem item = getCategory(place, runtimeValues.get(place));
			
			if(item != null)
			{
				actions.add(item);
			}
		}
		return actions;
    }

	@Override
    public void executeAction(AbstractMenuItem action)
    {
	    simulator.fire(transition, action);
    }
	
	private static AbstractMenuItem getCategory(Place place, MSValue value)
	{
		if(value.getValues().size() == 0)
		{
			return null;
		}
		
		String categoryName = null;
		{
			if(place.getName() != null)
			{
				categoryName = place.getName().getText();
			}
			else
			{
				categoryName = "No name";
			}
			categoryName += " (" + place.getId() + ")";
		}
		PopupMenuCategory category = new PopupMenuCategory(categoryName);
		
		for(final AbstractValue aValue : value.getValues().keySet())
		{
			category.getItems().add(new PopupMenuItem(aValue.toString(), 
					aValue, value));
		}
		
		return category;
	}
}