package org.pnml.tools.epnk.applications.hlpng.simulator;

import org.pnml.tools.epnk.applications.IApplicationWithPresentation;
import org.pnml.tools.epnk.applications.hlpng.selection.AbstractMenuItem;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

public interface ISimulator extends IApplicationWithPresentation
{
	public void auto();
	public void stop();
	public void next();
	public void init();
	public void previous();
	public void fire(Transition transition, AbstractMenuItem action);
	public void checkTransitions();
}
