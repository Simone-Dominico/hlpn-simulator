package org.pnml.tools.epnk.applications.hlpng.runtime;

public class MSTerm
{
	private AbstractValue value = null;
	private String placeId = null;
	private int multiplicity = 0;
	
	public AbstractValue getValue()
    {
    	return value;
    }
	public void setValue(AbstractValue value)
    {
    	this.value = value;
    }
	public String getPlaceId()
    {
    	return placeId;
    }
	public void setPlaceId(String placeId)
    {
    	this.placeId = placeId;
    }
	public int getMultiplicity()
    {
    	return multiplicity;
    }
	public void setMultiplicity(int multiplicity)
    {
    	this.multiplicity = multiplicity;
    }
}
