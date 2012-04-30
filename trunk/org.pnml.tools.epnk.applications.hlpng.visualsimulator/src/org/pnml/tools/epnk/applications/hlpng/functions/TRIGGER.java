package org.pnml.tools.epnk.applications.hlpng.functions;

import geditor.GObject;

import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.utils.IVisualSimulator;

import Appearence.Shape;
import dk.dtu.imm.se2.group6.interfaces.IAnimator;

public class TRIGGER extends AbstractFunction
{

	public TRIGGER(Map<String, GObject> geometryMap,
            Map<String, Shape> shapeMap,
            IAnimator animator, IVisualSimulator visualSimulator)
    {
	    super(geometryMap, shapeMap, animator, visualSimulator);
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
