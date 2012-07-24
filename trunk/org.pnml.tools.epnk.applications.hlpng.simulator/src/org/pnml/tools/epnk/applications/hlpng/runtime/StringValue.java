package org.pnml.tools.epnk.applications.hlpng.runtime;

public class StringValue extends AbstractValue
{
	protected String data = null;

	@Override
    public String toString()
    {
		return "\"" + data + "\"";
    }

    @Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result
	            + ((data == null) ? 0 : data.hashCode());
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
	    if(!(obj instanceof StringValue))
	    {
		    return false;
	    }
	    StringValue other = (StringValue) obj;
	    if(data == null)
	    {
		    if(other.data != null)
		    {
			    return false;
		    }
	    }
	    else if(!data.equals(other.data))
	    {
		    return false;
	    }
	    return true;
    }

	public String getData()
    {
    	return data;
    }

	public void setData(String data)
    {
    	this.data = data;
    }
}
