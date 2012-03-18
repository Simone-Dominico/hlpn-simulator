package org.pnml.tools.epnk.applications.hlpng.runtime;

import java.util.ArrayList;
import java.util.List;

import org.pnml.tools.epnk.annotations.netannotations.impl.NetAnnotationImpl;


public class NetMarking extends NetAnnotationImpl
{
	private List<AbstractMarking> markings = new ArrayList<AbstractMarking>();

	public List<AbstractMarking> getMarkings()
    {
    	return markings;
    }

	public void setMarkings(List<AbstractMarking> markings)
    {
    	this.markings = markings;
    }
}
