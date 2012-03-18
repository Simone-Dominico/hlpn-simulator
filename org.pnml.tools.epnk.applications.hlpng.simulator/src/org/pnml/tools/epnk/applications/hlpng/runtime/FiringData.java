package org.pnml.tools.epnk.applications.hlpng.runtime;

public class FiringData
{
	private PlaceMarking placeMarking = null;
	private MSTerm msTerm = null;
	private RuntimeVariable variable = null;
	private AbstractValue varValue = null;
	
	public PlaceMarking getPlaceMarking()
    {
    	return placeMarking;
    }
	public void setPlaceMarking(PlaceMarking placeMarking)
    {
    	this.placeMarking = placeMarking;
    }
	public MSTerm getMsTerm()
    {
    	return msTerm;
    }
	public void setMsTerm(MSTerm msTerm)
    {
    	this.msTerm = msTerm;
    }
	public RuntimeVariable getVariable()
    {
    	return variable;
    }
	public void setVariable(RuntimeVariable variable)
    {
    	this.variable = variable;
    }
	public AbstractValue getVarValue()
    {
    	return varValue;
    }
	public void setVarValue(AbstractValue varValue)
    {
    	this.varValue = varValue;
    }
	
	
}
