package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public interface IFiringStrategy
{

	public abstract FiringMode fire(List<FiringMode> modes);

}