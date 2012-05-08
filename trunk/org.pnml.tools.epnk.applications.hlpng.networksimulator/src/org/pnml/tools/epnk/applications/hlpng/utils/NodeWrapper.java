package org.pnml.tools.epnk.applications.hlpng.utils;

import networkmodel.Node;

public class NodeWrapper
{
	private int id = 0;
	private Node node = null;
	
	public NodeWrapper(int id, Node node)
	{
		this.id = id;
		this.node = node;
	}

	public int getId()
    {
    	return id;
    }

	public Node getNode()
    {
    	return node;
    }
}
