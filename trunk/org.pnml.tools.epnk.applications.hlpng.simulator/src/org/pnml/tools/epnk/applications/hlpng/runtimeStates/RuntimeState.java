package org.pnml.tools.epnk.applications.hlpng.runtimeStates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IDWrapper;
import org.pnml.tools.epnk.pnmlcoremodel.Place;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

public class RuntimeState implements IRuntimeState
{
	// place <=> runtime value
	private Map<IDWrapper, AbstractValue> values = new HashMap<IDWrapper, AbstractValue>();
	// transition <=> firing modes
	private Map<IDWrapper, List<FiringMode>> modes = new HashMap<IDWrapper, List<FiringMode>>();
	
	private FiringMode firingMode = null;

	@Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((values == null) ? 0 : values.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj)
    {
	    if(this == obj)
	    {
		    return true;
	    }
	    if(obj == null)
	    {
		    return false;
	    }
	    if(!(obj instanceof RuntimeState))
	    {
		    return false;
	    }
	    RuntimeState other = (RuntimeState) obj;
	    if(values == null)
	    {
		    if(other.values != null)
		    {
			    return false;
		    }
	    }
	    else if(!values.equals(other.values))
	    {
		    return false;
	    }
	    return true;
    }

	@Override
	public FiringMode getFiringMode()
    {
    	return firingMode;
    }

	@Override
	public void setFiringMode(FiringMode firingMode)
    {
    	this.firingMode = firingMode;
    }

	@Override
    public Set<IDWrapper> getPlaces()
    {
	    return this.values.keySet();
    }

	@Override
    public void addValue(Place place, AbstractValue value)
    {
		values.put(new IDWrapper(place), value);
    }

	@Override
    public AbstractValue getValue(Place place)
    {
	    return values.get(new IDWrapper(place));
    }
	
	@Override
    public AbstractValue getValue(IDWrapper wrapper)
    {
	    return values.get(wrapper);
    }

	@Override
    public void addFiringModes(Transition transition, List<FiringMode> modeList)
    {
		modes.put(new IDWrapper(transition), modeList);
    }

	@Override
    public List<FiringMode> getFiringModes(Transition transition)
    {
	    return modes.get(new IDWrapper(transition));
    }
	
	@Override
    public List<FiringMode> getFiringModes(IDWrapper wrapper)
    {
	    return modes.get(wrapper);
    }

	@Override
    public Set<IDWrapper> getTransitions()
    {
		return this.modes.keySet();
    }

	public Map<IDWrapper, AbstractValue> getValues()
    {
    	return values;
    }
}
