package org.pnml.tools.epnk.applications.hlpng.runtimeStates;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IDWrapper;
import org.pnml.tools.epnk.pnmlcoremodel.Place;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

public class RuntimeState implements IRuntimeState
{
	private RuntimeState previous = null;
	private RuntimeState next = null;
	
	// place <=> runtime value
	private Map<IDWrapper, IMSValue> values = new HashMap<IDWrapper, IMSValue>();
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
    public void addValue(Place place, IMSValue value)
    {
		values.put(new IDWrapper(place), value);
    }

	@Override
    public IMSValue getValue(Place place)
    {
	    return values.get(new IDWrapper(place));
    }
	
	@Override
    public IMSValue getValue(IDWrapper wrapper)
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

	@Override
	public Map<IDWrapper, IMSValue> getValues()
    {
    	return this.values;
    }
	
	@Override
	public Map<IDWrapper, IMSValue> getClonedValues()
	{
		Map<IDWrapper, IMSValue> clone = new HashMap<IDWrapper, IMSValue>();
		
		for(IDWrapper w : values.keySet())
		{
			clone.put(w, values.get(w));
		}
		
		return clone;
	}
	
	@Override
	public Map<IDWrapper, List<FiringMode>> getModes()
    {
    	return modes;
    }

	public RuntimeState getPrevious()
    {
    	return previous;
    }

	public void setPrevious(RuntimeState previous)
    {
    	this.previous = previous;
    }

	public RuntimeState getNext()
    {
    	return next;
    }

	public void setNext(RuntimeState next)
    {
    	this.next = next;
    }
}