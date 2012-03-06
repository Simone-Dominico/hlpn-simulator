package org.pnml.tools.epnk.applications.hlpng.actions;

import java.util.List;

public interface IActionProvider
{
	public List<IAction> getActions();
    
    public void executeAction(IAction action);
}
