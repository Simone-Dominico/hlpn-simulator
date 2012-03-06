package org.pnml.tools.epnk.applications.hlpng.selection;

public class AbstractMenuItem
{

	protected String name = null;

	public AbstractMenuItem(String name)
	{
		this.name = name;
	}

	public String getName()
    {
    	return name;
    }

	public void setName(String name)
    {
    	this.name = name;
    }

}