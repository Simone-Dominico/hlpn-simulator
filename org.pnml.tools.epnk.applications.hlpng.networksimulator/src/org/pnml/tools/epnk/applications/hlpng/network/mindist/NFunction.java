package org.pnml.tools.epnk.applications.hlpng.network.mindist;


import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.utils.NodeWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class NFunction implements IEvaluator
{
	private Integer[][] graph = null;
	private Map<String, NodeWrapper> nodeMap = null;
	private Map<Integer, NodeWrapper> nodeIdMap = null;
	
	public NFunction(Integer[][] graph, Map<String, NodeWrapper> nodeMap,
			Map<Integer, NodeWrapper> nodeIdMap)
	{
		this.graph = graph;
		this.nodeMap = nodeMap;
		this.nodeIdMap = nodeIdMap;
	}
	
	@Override
    public AbstractValue evaluate(List<AbstractValue> values, Operator operator)
    {
		int nodeId = nodeMap.get(((StringValue)values.get(0)).getData()).getId();
		UserOperator uOp = (UserOperator) operator;
		
		MSValue msValue = new MSValue();
		msValue.setSort(uOp.getOutputSort());
		
		for(int i = 0; i < graph.length; i++)
		{
			if(graph[nodeId][i] != null)
			{
				ProductValue pValue = new ProductValue();
				pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
				{
					StringValue strValue = new StringValue();
					strValue.setData(nodeIdMap.get(i).getNode().getLabel());
					strValue.setSort(TermsFactory.eINSTANCE.createUserSort());
					
					pValue.getComponents().add(strValue);
				}
				pValue.getComponents().add(values.get(1));	
				
				msValue.getValues().put(pValue, 1);
			}
		}
		
	    return msValue;
    }
}
