package org.pnml.tools.epnk.applications.hlpng.presentation.decorations;

/*
 * Author: Mindaugas Laganeckas
 * Email: s100972@student.dtu.dk
 */

public interface IStateContext
{
	public void setState(IState state);
	public IState getState();
	public void request();
}
