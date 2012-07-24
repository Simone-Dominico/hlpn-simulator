package org.pnml.tools.epnk.applications.hlpng.runtime;

import java.util.ArrayList;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;

public class ListValue extends AbstractValue
{
	private List<ITermWrapper> elements = new ArrayList<ITermWrapper>();

	public List<ITermWrapper> getElements()
    {
    	return elements;
    }

	public void setElements(List<ITermWrapper> elements)
    {
    	this.elements = elements;
    }

	@Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result
	            + ((elements == null) ? 0 : elements.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj)
    {
	    if(this == obj)
	    {
		    return true;
	    }
	    if(obj == null)
	    {
		    return false;
	    }
	    if(!(obj instanceof ListValue))
	    {
		    return false;
	    }
	    ListValue other = (ListValue) obj;
	    if(elements == null)
	    {
		    if(other.elements != null)
		    {
			    return false;
		    }
	    }
	    else if(!elements.equals(other.elements))
	    {
		    return false;
	    }
	    return true;
    }

	@Override
    public String toString()
    {
		StringBuffer buffer = new StringBuffer("[");
		if(elements.size() > 0)
		{
			buffer.append(elements.get(0).toString());
			for(int i = 1; i < elements.size(); i++)
			{
				ITermWrapper el = elements.get(i);
				buffer.append("," + el.toString());
			}	
		}
		buffer.append("]");
		
	    return buffer.toString();
    }
}
