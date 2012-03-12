package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.List;

public class InscriptionMatch
{
	private String placeId = null;
	private int multiplicity;
	private List<VariableAssignmnet> assignmnets = null;
	
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
	public List<VariableAssignmnet> getAssignmnets()
    {
    	return assignmnets;
    }
	public void setAssignmnets(List<VariableAssignmnet> assignmnets)
    {
    	this.assignmnets = assignmnets;
    }
}
