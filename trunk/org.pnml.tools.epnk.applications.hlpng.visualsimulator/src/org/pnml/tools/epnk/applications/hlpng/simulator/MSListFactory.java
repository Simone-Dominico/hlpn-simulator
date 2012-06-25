package org.pnml.tools.epnk.applications.hlpng.simulator;

import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;

public class MSListFactory extends RuntimeValueFactory
{
	@Override
    public IMSValue createMSValue()
    {
	    return new MSValueList();
    }
}
