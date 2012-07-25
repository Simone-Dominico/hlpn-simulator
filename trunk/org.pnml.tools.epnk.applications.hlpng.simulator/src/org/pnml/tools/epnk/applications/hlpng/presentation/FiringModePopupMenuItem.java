package org.pnml.tools.epnk.applications.hlpng.presentation;

import org.pnml.tools.epnk.applications.hlpng.presentation.popup.AbstractMenuItem;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

/*
 * Author: Mindaugas Laganeckas
 * Email: s100972@student.dtu.dk
 */

public class FiringModePopupMenuItem extends AbstractMenuItem
{
	private FiringMode mode = null;
	
	public FiringMode getMode()
    {
    	return mode;
    }

	public FiringModePopupMenuItem(String name, FiringMode mode)
	{
		super(name);
		this.mode = mode;
	}
	
}
