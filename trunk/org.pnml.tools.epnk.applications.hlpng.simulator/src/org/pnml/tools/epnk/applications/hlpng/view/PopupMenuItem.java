package org.pnml.tools.epnk.applications.hlpng.view;

import runtime.AbstractValue;
import runtime.MSValue;

public class PopupMenuItem extends AbstractMenuItem
{
	private AbstractValue value = null;
	private MSValue msValue = null;
	
	public MSValue getMsValue()
    {
    	return msValue;
    }

	public AbstractValue getValue()
    {
    	return value;
    }

	public PopupMenuItem(String name, AbstractValue value, MSValue msValue)
	{
		super(name);
		this.value = value;
		this.msValue = msValue;
	}
	
}
