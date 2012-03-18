package org.pnml.tools.epnk.applications.hlpng.runtime;

import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;

public class PlaceMarking extends AbstractMarking
{
	private MSValue msValue = null;
	private Place place = null;

	public Place getPlace()
    {
    	return place;
    }

	public void setPlace(Place place)
    {
    	this.place = place;
    }

	public MSValue getMsValue()
    {
    	return msValue;
    }

	public void setMsValue(MSValue msValue)
    {
    	this.msValue = msValue;
    }
}
