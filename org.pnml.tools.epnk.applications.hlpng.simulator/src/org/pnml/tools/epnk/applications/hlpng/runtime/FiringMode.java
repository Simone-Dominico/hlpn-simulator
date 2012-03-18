package org.pnml.tools.epnk.applications.hlpng.runtime;

import java.util.ArrayList;
import java.util.List;

public class FiringMode
{
	private List<FiringData> values = new ArrayList<FiringData>();

	public List<FiringData> getValues()
    {
    	return values;
    }

	public void setValues(List<FiringData> values)
    {
    	this.values = values;
    }
	
	@Override
    public String toString()
    {
		StringBuffer buffer = new StringBuffer();
		for(FiringData data : values)
		{
			buffer.append(data.getVariable().getVariable().getName() + "=" + data.getVarValue() + "\n");
		}
		
	    return buffer.toString().replaceAll("\\s+$", "");
    }
}
