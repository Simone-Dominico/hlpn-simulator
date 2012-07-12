package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;

public interface IFiringStrategy
{
	public abstract FiringMode fire(Map<IDWrapper, List<FiringMode>> modes, 
			IRuntimeState currentMarking);
}