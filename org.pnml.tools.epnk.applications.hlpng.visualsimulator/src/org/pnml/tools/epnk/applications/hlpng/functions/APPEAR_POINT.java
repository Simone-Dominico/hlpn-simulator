package org.pnml.tools.epnk.applications.hlpng.functions;

import geditor.GObject;
import geditor.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;

import Appearence.Shape;

public class APPEAR_POINT extends AbstractFunction
{
	private Map<String, Integer> visibleObjects = null;
	
	public APPEAR_POINT()
    {
	    this.visibleObjects = new HashMap<String, Integer>();
    }

	@Override
	public IValue execute(List<IValue> values)
	{
		// model object comes first
		StringValue modelStr = (StringValue)values.get(0);
		
		// geometry object comes second
		StringValue geoStr = (StringValue)values.get(1);
				
		Shape shape = shapeMap.get(modelStr.getData());
		GObject gObj = geometryMap.get(geoStr.getData());

		int modelId = visualSimulator.getModelId(shape.getId());

		// hides other objects on the point location
		if(gObj instanceof Point)
		{
			if(visibleObjects.containsKey(gObj.getId()))
			{
				animator.appear(visibleObjects.get(gObj.getId()), gObj, false);	
			}
			
			visibleObjects.put(gObj.getId(), modelId);
		}

		animator.appear(modelId, gObj, true);
		
		return null;
	}

}
