package org.pnml.tools.epnk.applications.hlpng.runtime;

public class NumberValue extends AbstractValue
{
	protected int n;

	public int getN()
    {
    	return n;
    }

	public void setN(int n)
    {
    	this.n = n;
    }
	
	@Override
    public String toString()
    {
        return String.valueOf(n);
    }

    @Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + n;
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
	    if(!(obj instanceof NumberValue))
	    {
		    return false;
	    }
	    NumberValue other = (NumberValue) obj;
	    if(n != other.n)
	    {
		    return false;
	    }
	    return true;
    }
	
}
