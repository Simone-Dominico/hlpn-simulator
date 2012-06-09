package org.pnml.tools.epnk.applications.hlpng.functions;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;

import Appearence.Shape;

public class TRIGGER extends AbstractFunction
{
	@Override
	public AbstractValue execute(List<AbstractValue> values)
	{
		// model object
		StringValue modelStr = (StringValue)values.get(0);

		Shape shape = shapeMap.get(modelStr.getData());
		
		animator.trigger(visualSimulator.getModelId(shape.getId()));
		
		visualSimulator.registerAnimation(visualSimulator.getModelId(shape.getId()));
		
		return null;
	}

}
