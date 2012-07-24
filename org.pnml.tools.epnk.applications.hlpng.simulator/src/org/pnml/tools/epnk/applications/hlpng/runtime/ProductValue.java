package org.pnml.tools.epnk.applications.hlpng.runtime;

import java.util.ArrayList;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.ITermWrapper;

public class ProductValue extends AbstractValue
{
	private List<ITermWrapper> components = new ArrayList<ITermWrapper>();

	public List<ITermWrapper> getComponents()
    {
    	return components;
    }

	public void setComponents(List<ITermWrapper> components)
    {
    	this.components = components;
    }

	@Override
    public String toString()
    {
		StringBuffer buffer = new StringBuffer("(");
		for(ITermWrapper value : components)
		{
			buffer.append(value.toString() + ",");
		}
	    return buffer.toString().replaceAll("(.*),\\s*$", "$1") + ")";
    }

    @Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result
	            + ((components == null) ? 0 : components.hashCode());
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
	    if(!(obj instanceof ProductValue))
	    {
		    return false;
	    }
	    ProductValue other = (ProductValue) obj;
	    if(components == null)
	    {
		    if(other.components != null)
		    {
			    return false;
		    }
	    }
	    else if(!components.equals(other.components))
	    {
		    return false;
	    }
	    return true;
    }
}
