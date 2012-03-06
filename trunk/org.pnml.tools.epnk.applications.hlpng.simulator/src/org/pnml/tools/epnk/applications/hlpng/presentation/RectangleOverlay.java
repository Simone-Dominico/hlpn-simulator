package org.pnml.tools.epnk.applications.hlpng.presentation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.pnml.tools.epnk.applications.hlpng.actions.IActionProvider;
import org.pnml.tools.epnk.applications.hlpng.actions.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.selection.AbstractMenuItem;
import org.pnml.tools.epnk.applications.hlpng.selection.PopupMenuItem;
import org.pnml.tools.epnk.applications.hlpng.states.GreenOverlayState;
import org.pnml.tools.epnk.applications.hlpng.states.IState;
import org.pnml.tools.epnk.applications.hlpng.states.IStateContext;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

import transitionruntime.FiringMode;
import transitionruntime.TransitionMarking;

public class RectangleOverlay extends RectangleFigure implements IStateContext,
	IActionProvider
{
	final protected IFigure figure;
	protected IState currentState = null;
	final protected Transition transition;
	final protected TransitionMarking marking;
	final protected ISimulator simulator;

	public RectangleOverlay(final ISimulator simulator, final IFigure figure, 
			final Transition transition, final TransitionMarking marking)
	{
		super();
		this.figure = figure;
		this.transition = transition;
		this.marking = marking;
		this.simulator = simulator;
		
		currentState = new GreenOverlayState(this);
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
		
		for(FiringMode mode : marking.getModes())
		{
			actions.add(getCategory(mode));
		}
		return actions;
    }

	@Override
    public void executeAction(AbstractMenuItem action)
    {
	    simulator.fire(transition, action);
    }
	
	private static AbstractMenuItem getCategory(FiringMode mode)
	{
		PopupMenuItem item = new PopupMenuItem(mode.toString(), mode);
		
		return item;
	}

	@Override
    public IState getState()
    {
	    return currentState;
    }
}