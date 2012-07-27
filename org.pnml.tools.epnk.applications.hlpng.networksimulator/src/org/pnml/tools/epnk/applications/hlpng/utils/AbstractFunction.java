package org.pnml.tools.epnk.applications.hlpng.utils;

import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;

public abstract class AbstractFunction implements IEvaluator
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
