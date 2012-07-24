package org.pnml.tools.epnk.applications.hlpng.simulator.views;

public class TableRecord
{
	private String[] text = null;
	private Object data = null;
	
	public String[] getText()
    {
    	return text;
    }
	public void setText(String[] text)
    {
    	this.text = text;
    }
	public Object getData()
    {
    	return data;
    }
	public void setData(Object data)
    {
    	this.data = data;
    }
}
