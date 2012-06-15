package org.pnml.tools.epnk.applications.hlpng.runtimeStates;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IDWrapper;
import org.pnml.tools.epnk.pnmlcoremodel.Place;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

public interface IRuntimeState
{
	public FiringMode getFiringMode();
	public void setFiringMode(FiringMode firingMode);
	
	public Map<IDWrapper, MSValue> getValues();
	public Map<IDWrapper, MSValue> getClonedValues();
	public Map<IDWrapper, List<FiringMode>> getModes();
	
	public void addValue(Place place, MSValue value);
	public MSValue getValue(Place place);
	public MSValue getValue(IDWrapper place);
	public Set<IDWrapper> getPlaces();
	
	public void addFiringModes(Transition transition, List<FiringMode> modes);
	public List<FiringMode> getFiringModes(Transition transition);
	public List<FiringMode> getFiringModes(IDWrapper transition);
	public Set<IDWrapper> getTransitions();
	
	public boolean equals(Object obj);
}