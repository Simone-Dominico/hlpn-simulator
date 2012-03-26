package org.pnml.tools.epnk.applications.hlpng.firing;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;


public abstract class AbstractVariable extends AbstractValue
{
	public String getName()
	{
		return toString();
	}
}
