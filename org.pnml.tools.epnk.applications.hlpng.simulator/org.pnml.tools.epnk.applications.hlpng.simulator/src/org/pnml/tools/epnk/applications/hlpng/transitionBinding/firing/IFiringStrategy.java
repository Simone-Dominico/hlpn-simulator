package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;


import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;

public interface IFiringStrategy
{
	public abstract FiringMode fire(IRuntimeState currentMarking);
}