package org.pnml.tools.epnk.applications.hlpng.utils;

import runtime.AbstractValue;

public class MSTerm
{
	private int multiplicity;
	private String placeId = null;
	private AbstractValue value = null;
	
	public AbstractValue getValue()
    {
    	return value;
    }
	public void setValue(AbstractValue value)
    {
    	this.value = value;
    }
	public int getMultiplicity()
    {
    	return multiplicity;
    }
	public void setMultiplicity(int multiplicity)
    {
    	this.multiplicity = multiplicity;
    }
	public String getPlaceId()
    {
    	return placeId;
    }
	public void setPlaceId(String placeId)
    {
    	this.placeId = placeId;
    }
}
