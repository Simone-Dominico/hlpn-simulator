package org.pnml.tools.epnk.applications.hlpng.runtime;

public class DotValue extends AbstractValue
{
	private final String name = "dot";
	
	@Override
    public String toString()
    {
	    return name;
    }

	@Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	    if(!(obj instanceof DotValue))
	    {
		    return false;
	    }
	    DotValue other = (DotValue) obj;
	    if(name == null)
	    {
		    if(other.name != null)
		    {
			    return false;
		    }
	    }
	    else if(!name.equals(other.name))
	    {
		    return false;
	    }
	    return true;
    }
}
