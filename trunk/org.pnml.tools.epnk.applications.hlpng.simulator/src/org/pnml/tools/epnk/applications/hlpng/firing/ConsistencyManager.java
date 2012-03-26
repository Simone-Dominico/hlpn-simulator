package org.pnml.tools.epnk.applications.hlpng.firing;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NatValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;

public class ConsistencyManager
{
	public static boolean check(AbstractValue value)
	{
		if(value instanceof NumberValue)
		{
			NumberValue nValue = (NumberValue)value;
			int n = nValue.getN();
			if(value instanceof PosValue && n <= 0)
			{
				return false;
			}
			if(value instanceof NatValue && n < 0)
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
		
		throw new RuntimeException("Do not know the value: " + value);
	}
}
