package org.pnml.tools.epnk.applications.hlpng.validation;

import org.eclipse.emf.validation.model.IClientSelector;

public class ValidationDelegateClientSelector implements IClientSelector
{
	public static boolean running = false;

	public boolean selects(Object object)
	{
		return running;
	}
}
