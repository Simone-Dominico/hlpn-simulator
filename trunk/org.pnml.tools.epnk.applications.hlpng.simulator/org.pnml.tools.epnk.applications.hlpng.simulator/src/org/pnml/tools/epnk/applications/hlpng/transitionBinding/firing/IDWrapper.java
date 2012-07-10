package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import org.pnml.tools.epnk.pnmlcoremodel.ID;

public class IDWrapper
{
	private ID id = null;

	private Integer hashCodeCache = null;
	
	public IDWrapper(ID id)
	{
		this.id = id;
	}
	
	@Override
    public int hashCode()
    {
		if(hashCodeCache == null)
		{
			final int prime = 31;
		    int result = 1;
		    result = prime * result + ((id.getId() == null) ? 0 : id.getId().hashCode());
		    hashCodeCache = result;
		}
	    return hashCodeCache;
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
	    if(!(obj instanceof IDWrapper))
	    {
		    return false;
	    }
	    IDWrapper other = (IDWrapper) obj;
	    if(id.getId() == null)
	    {
		    if(other.id.getId() != null)
		    {
			    return false;
		    }
	    }
	    else if(!id.getId().equals(other.id.getId()))
	    {
		    return false;
	    }
	    return true;
    }

	public ID getId()
    {
    	return id;
    }
}
