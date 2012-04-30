package org.pnml.tools.epnk.applications.hlpng.functions;

import geditor.GObject;

import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.utils.IVisualSimulator;

import Appearence.Shape;
import dk.dtu.imm.se2.group6.interfaces.IAnimator;

public class APPEAR extends AbstractFunction
{

	public APPEAR(Map<String, GObject> geometryMap,
            Map<String, Shape> shapeMap,
            IAnimator animator, IVisualSimulator visualSimulator)
    {
	    super(geometryMap, shapeMap, animator, visualSimulator);
    }

	@Override
	public AbstractValue execute(List<AbstractValue> values)
	{
		// model object comes first
		StringValue modelStr = (StringValue)values.get(0);
		
		// geometry object comes second
		StringValue geoStr = (StringValue)values.get(1);
		
		// visibility flag comes last
		BooleanValue visibility = (BooleanValue)values.get(2);
		
		Shape shape = shapeMap.get(modelStr.getData());
		GObject gObj = geometryMap.get(geoStr.getData());

		int modelId = visualSimulator.getModelId(shape.getId());
		int staticItemId = visualSimulator.getStaticItemId(gObj.getId());

		animator.appear(modelId, staticItemId, visibility.getValue());
		
		return null;
	}

}
