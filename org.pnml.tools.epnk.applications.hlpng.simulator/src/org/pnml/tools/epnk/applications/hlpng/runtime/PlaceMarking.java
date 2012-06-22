package org.pnml.tools.epnk.applications.hlpng.runtime;

import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;

public class PlaceMarking extends AbstractMarking
{
	private IMSValue msValue = null;
	private Place place = null;

	public Place getPlace()
    {
    	return place;
    }

	public void setPlace(Place place)
    {
    	this.place = place;
    }

	public IMSValue getMsValue()
    {
    	return msValue;
    }

	public void setMsValue(IMSValue msValue)
    {
    	this.msValue = msValue;
    }
}
