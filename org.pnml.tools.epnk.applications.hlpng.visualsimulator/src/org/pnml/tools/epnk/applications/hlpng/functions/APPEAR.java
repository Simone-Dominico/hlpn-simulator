package org.pnml.tools.epnk.applications.hlpng.functions;

import geditor.GObject;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;

import Appearence.Shape;
import dk.dtu.imm.se2.group6.interfaces.IAnimator;

public class APPEAR extends AbstractFunction
{

	public APPEAR(IAnimator animator)
    {
	    super(animator);
    }

	@Override
	public AbstractValue execute(List<AbstractValue> values)
	{
		// model object comes first
		StringValue modelStr = (StringValue)values.get(0);
		
		// geometry object comes second
		StringValue geoStr = (StringValue)values.get(1);
		
		Shape shape = shapeMap.get(modelStr.getData());
		GObject gObj = geometryMap.get(geoStr.getData());

		int modelId = visualSimulator.getModelId(shape.getId());
		int staticItemId = visualSimulator.getStaticItemId(gObj.getId());

		animator.appear(modelId, staticItemId, true);
		
		return null;
	}

}
