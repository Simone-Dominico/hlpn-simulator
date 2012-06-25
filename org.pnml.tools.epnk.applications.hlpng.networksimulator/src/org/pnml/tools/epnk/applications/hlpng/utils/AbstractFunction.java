package org.pnml.tools.epnk.applications.hlpng.utils;

import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;

public abstract class AbstractFunction
{
	protected RuntimeValueFactory runtimeValueFactory = null;

	public RuntimeValueFactory getRuntimeValueFactory()
    {
    	return runtimeValueFactory;
    }

	public void setRuntimeValueFactory(RuntimeValueFactory runtimeValueFactory)
    {
    	this.runtimeValueFactory = runtimeValueFactory;
    }
}
