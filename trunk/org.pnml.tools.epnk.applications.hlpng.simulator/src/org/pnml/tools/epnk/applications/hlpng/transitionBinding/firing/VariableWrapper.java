package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.VariableImpl;


public class VariableWrapper extends TermWrapper
{
	protected Variable variable = null;

	public Variable getVariable()
    {
    	return variable;
    }
	public void setVariable(Variable variable)
    {
    	this.variable = variable;
    }
	
	@Override
	public String getName()
    {
		return variable.getName();
    }
	
	@Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((this.variable.getName() == null) ? 0 : 
	    	this.variable.getName().hashCode());
	    result = prime * result
	            + ((this.variable.getRefvariable() == null) ? 0 : 
	            	this.variable.getRefvariable().hashCode());
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
	    if(!(obj instanceof VariableImpl))
	    {
		    return false;
	    }
	    VariableImpl other = (VariableImpl) obj;
	    if(this.variable.getName() == null)
	    {
		    if(other.getName() != null)
		    {
			    return false;
		    }
	    }
	    else if(!this.variable.getName().equals(other.getName()))
	    {
		    return false;
	    }
	    if(this.variable.getRefvariable() == null)
	    {
		    if(other.getRefvariable() != null)
		    {
			    return false;
		    }
	    }
	    else if(!this.variable.getRefvariable().equals(other.getRefvariable()))
	    {
		    return false;
	    }
	    return true;
    }


}
