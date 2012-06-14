package org.pnml.tools.epnk.applications.hlpng.runtimeStates;

import java.util.List;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IDWrapper;
import org.pnml.tools.epnk.pnmlcoremodel.Place;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

public interface IRuntimeState
{
	public FiringMode getFiringMode();
	public void setFiringMode(FiringMode firingMode);
	
	public void addValue(Place place, AbstractValue value);
	public AbstractValue getValue(Place place);
	public AbstractValue getValue(IDWrapper place);
	public Set<IDWrapper> getPlaces();
	
	public void addFiringModes(Transition transition, List<FiringMode> modes);
	public List<FiringMode> getFiringModes(Transition transition);
	public List<FiringMode> getFiringModes(IDWrapper transition);
	public Set<IDWrapper> getTransitions();
	
	public boolean equals(Object obj);
}