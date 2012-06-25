package org.pnml.tools.epnk.applications.hlpng.functions;

import geditor.GObject;

import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;

import Appearence.Shape;

public class MOVE extends AbstractFunction
{
	@Override
	public IValue execute(List<IValue> values)
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
