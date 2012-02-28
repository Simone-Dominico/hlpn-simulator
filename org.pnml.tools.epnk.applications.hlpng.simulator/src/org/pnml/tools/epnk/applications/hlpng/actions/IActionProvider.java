package org.pnml.tools.epnk.applications.hlpng.actions;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.view.AbstractMenuItem;

public interface IActionProvider
{
	public List<AbstractMenuItem> getActions();
    
    public void executeAction(AbstractMenuItem action);
}
