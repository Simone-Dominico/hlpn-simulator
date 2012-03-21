package org.pnml.tools.epnk.applications.hlpng.selection;

import org.pnml.tools.epnk.applications.hlpng.firing.FiringMode;

public class PopupMenuItem extends AbstractMenuItem
{
	private FiringMode mode = null;
	
	public FiringMode getMode()
    {
    	return mode;
    }

	public PopupMenuItem(String name, FiringMode mode)
	{
		super(name);
		this.mode = mode;
	}
	
}
