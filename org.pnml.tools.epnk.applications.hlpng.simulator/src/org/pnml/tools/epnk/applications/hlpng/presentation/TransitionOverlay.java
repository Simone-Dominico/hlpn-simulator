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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.pnml.tools.epnk.applications.hlpng.presentation.actions.IAction;
import org.pnml.tools.epnk.applications.hlpng.presentation.decorations.AbstractRectangleOverlay;
import org.pnml.tools.epnk.applications.hlpng.presentation.popup.AbstractMenuItem;
import org.pnml.tools.epnk.applications.hlpng.simulator.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public abstract class TransitionOverlay extends AbstractRectangleOverlay
{
	final protected List<FiringMode> firingModes;
	final protected ISimulator simulator;

	public TransitionOverlay(final ISimulator simulator, 
			final IFigure figure, final List<FiringMode> firingModes)
	{
		super(figure);
		
		this.firingModes = firingModes;
		this.simulator = simulator;
	}

	@Override
    public List<IAction> getActions()
    {
		List<IAction> actions = new ArrayList<IAction>();
		
		for(FiringMode mode : firingModes)
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
		FiringModePopupMenuItem item = 
				new FiringModePopupMenuItem(mode.toString(), mode);
		
		return item;
	}

	@Override
    public void executeAction(){}
}
