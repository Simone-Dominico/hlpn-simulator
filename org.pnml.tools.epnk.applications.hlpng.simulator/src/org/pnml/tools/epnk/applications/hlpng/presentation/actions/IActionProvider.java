package org.pnml.tools.epnk.applications.hlpng.presentation.actions;

import java.util.List;

/*
 * Author: Mindaugas Laganeckas
 * Email: s100972@student.dtu.dk
 */

public interface IActionProvider
{
	public List<IAction> getActions();
    
    public void executeAction(IAction action);
}
