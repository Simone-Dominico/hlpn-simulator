package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IDWrapper;

public interface IFiringStrategy
{
	public abstract FiringMode fire(Map<IDWrapper, List<FiringMode>> modes, 
			IRuntimeState currentMarking);
}