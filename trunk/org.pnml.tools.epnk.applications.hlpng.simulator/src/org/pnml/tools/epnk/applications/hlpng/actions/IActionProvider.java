package org.pnml.tools.epnk.applications.hlpng.actions;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.view.PopupMenuItem;

public interface IActionProvider
{
	public List<PopupMenuItem> getActions();
    
    public void executeAction(PopupMenuItem action);
}
