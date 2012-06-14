package org.pnml.tools.epnk.applications.hlpng.functions;


import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.BooleansFactory;

import Appearence.Shape;

public class READY extends AbstractFunction
{
	@Override
	public AbstractValue execute(List<AbstractValue> values)
	{
		// model object
		StringValue modelStr = (StringValue)values.get(0);

		Shape shape = shapeMap.get(modelStr.getData());
		
		BooleanValue ready = new BooleanValue();
		ready.setValue(visualSimulator.isReady(visualSimulator.getModelId(shape.getId())));
		ready.setSort(BooleansFactory.eINSTANCE.createBool());
				
		return ready;
	}

}