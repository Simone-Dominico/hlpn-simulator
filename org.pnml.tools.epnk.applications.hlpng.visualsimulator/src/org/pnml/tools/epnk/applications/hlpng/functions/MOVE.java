package org.pnml.tools.epnk.applications.hlpng.functions;

import geditor.GObject;

import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.utils.IVisualSimulator;

import Appearence.Shape;
import dk.dtu.imm.se2.group6.interfaces.IAnimator;

public class MOVE extends AbstractFunction
{

	public MOVE(Map<String, GObject> geometryMap,
            Map<String, Shape> shapeMap,
            IAnimator animator, IVisualSimulator visualSimulator)
    {
	    super(geometryMap, shapeMap, animator, visualSimulator);
    }

	@Override
	public synchronized AbstractValue execute(List<AbstractValue> values)
	{
		// model object comes first
		StringValue modelStr = (StringValue)values.get(0);
		
		// geometry object comes second
		StringValue geoStr = (StringValue)values.get(1);
		
		// speed comes third
		NumberValue speed = (NumberValue)values.get(2);
		
		Shape shape = shapeMap.get(modelStr.getData());
		GObject gObj = geometryMap.get(geoStr.getData());
		
		animator.move(visualSimulator.getModelId(shape.getId()), 
				visualSimulator.getStaticItemId(gObj.getId()), speed.getN());
		
		visualSimulator.registerAnimation(visualSimulator.getModelId(shape.getId()));
		
		return null;
	}

}
