package org.pnml.tools.epnk.applications.hlpng.presentation.marking;

import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;

public class PlaceMarking extends AbstractMarking
{
	private IMSValue msValue = null;

	public IMSValue getMsValue()
    {
    	return msValue;
    }

	public void setMsValue(IMSValue msValue)
    {
    	this.msValue = msValue;
    }
}
