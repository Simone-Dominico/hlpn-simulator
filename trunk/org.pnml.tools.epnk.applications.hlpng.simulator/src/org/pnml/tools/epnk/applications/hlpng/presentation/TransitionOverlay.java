package org.pnml.tools.epnk.applications.hlpng.presentation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.pnml.tools.epnk.applications.hlpng.presentation.marking.TransitionMarking;
import org.pnml.tools.epnk.applications.hlpng.simulator.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.presentation.IApplicationWithPresentation;
import org.pnml.tools.epnk.applications.presentation.actions.IAction;
import org.pnml.tools.epnk.applications.presentation.decorations.AbstractRectangleOverlay;
import org.pnml.tools.epnk.applications.presentation.popup.AbstractMenuItem;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

public class TransitionOverlay extends AbstractRectangleOverlay
{
	final protected Transition transition;
	final protected TransitionMarking marking;
	final protected ISimulator simulator;

	public TransitionOverlay(final IApplicationWithPresentation simulator, 
			final IFigure figure, final Transition transition, final TransitionMarking marking)
	{
		super(figure);
		
		this.transition = transition;
		this.marking = marking;
		this.simulator = (ISimulator)simulator;
		
		currentState = new TransitionReadyState(this);
		currentState.handle();
	}

	@Override
    public List<IAction> getActions()
    {
		List<IAction> actions = new ArrayList<IAction>();
		
		for(FiringMode mode : marking.getModes())
		{
			actions.add(getCategory(mode));
		}
		return actions;
    }

	@Override
    public void executeAction(IAction action)
    {
		if(action instanceof FiringModePopupMenuItem)
		{
			simulator.fire(((FiringModePopupMenuItem)action).getMode(), true);	
		}
    }
	
	private static AbstractMenuItem getCategory(FiringMode mode)
	{
		FiringModePopupMenuItem item = new FiringModePopupMenuItem(mode.toString(), mode);
		
		return item;
	}
}