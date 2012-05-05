package org.pnml.tools.epnk.applications.hlpng.functions;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;

import Appearence.Shape;
import dk.dtu.imm.se2.group6.interfaces.IAnimator;

public class TRIGGER extends AbstractFunction
{

	public TRIGGER(IAnimator animator)
    {
	    super(animator);
    }

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
