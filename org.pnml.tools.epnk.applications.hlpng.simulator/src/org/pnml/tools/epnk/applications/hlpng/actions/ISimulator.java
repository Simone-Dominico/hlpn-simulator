package org.pnml.tools.epnk.applications.hlpng.actions;

import org.pnml.tools.epnk.applications.hlpng.selection.AbstractMenuItem;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

public interface ISimulator
{
	public void auto();
	public void stop();
	public void next();
	public void previous();
	public void fire(Transition transition, AbstractMenuItem action);
}
