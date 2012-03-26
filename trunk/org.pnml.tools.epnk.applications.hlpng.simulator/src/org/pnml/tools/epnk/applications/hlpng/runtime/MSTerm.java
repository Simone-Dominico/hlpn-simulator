package org.pnml.tools.epnk.applications.hlpng.runtime;

public class MSTerm extends AbstractValue
{
	private AbstractValue value = null;
	private int multiplicity = 0;
	
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
}
