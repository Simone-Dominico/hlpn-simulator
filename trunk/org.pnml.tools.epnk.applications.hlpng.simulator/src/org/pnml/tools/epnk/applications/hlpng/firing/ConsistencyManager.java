package org.pnml.tools.epnk.applications.hlpng.firing;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Natural;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Positive;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public class ConsistencyManager
{
	public static boolean check(AbstractValue value, Sort sort)
	{
		if(sort instanceof org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number)
		{
			NumberValue nValue = (NumberValue)value;
			int n = nValue.getN();
			if(sort instanceof Positive && n <= 0)
			{
				return false;
			}
			if(sort instanceof Natural && n < 0)
			{
				return false;
			}
			return true;
		}
		
		if(value instanceof MSValue)
		{
			for(AbstractValue key : ((MSValue)value).getValues().keySet())
			{
				Integer n = ((MSValue)value).getValues().get(key);
				if(n == null || n <= 0)
				{
					return false;
				}
			}
			return true;
		}
		
		return true;
	}
}
