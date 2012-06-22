package org.pnml.tools.epnk.applications.hlpng.network.echo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.applications.hlpng.utils.NodeWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class M2Function implements IEvaluator
{
    private Integer[][] graph = null;
    private Map<String, NodeWrapper> nodeMap = null;
    private Map<Integer, NodeWrapper> nodeIdMap = null;

    @Override
    public AbstractValue evaluate(Term term, EvaluationManager evaluationManager,
            Map<TermWrapper, AbstractValue> assignments) throws UnknownVariableException
    {
        Operator operator = (Operator) term;
        List<AbstractValue> values = new ArrayList<AbstractValue>();
        for(Term subterm : operator.getSubterm())
        {
            IEvaluator evaluator = evaluationManager.getHandler(subterm.getClass()); 
            AbstractValue value = evaluator.evaluate(subterm, evaluationManager, assignments);
            values.add(value);
        }
            
        UserOperator uOp = (UserOperator) operator;
        
        MSValue msValue = new MSValue();
        msValue.setSort(uOp.getOutputSort());
        
        List<AbstractValue> list = new ArrayList<AbstractValue>(values);
        for(AbstractValue value : getM2(graph, nodeMap, nodeIdMap, list.get(0)))
        {
            msValue.put(value, 1);
        }
        
        return msValue;
    }
    
    private static List<AbstractValue> getM2(Integer[][] graph, Map<String, NodeWrapper> nodeMap,
            Map<Integer, NodeWrapper> nodeIdMap, AbstractValue receiver)
    {
        List<AbstractValue> messages = new ArrayList<AbstractValue>();
        
        int receiverIndex = nodeMap.get(((StringValue)receiver).getData()).getId();
        
        for(int i = 0; i < graph[receiverIndex].length; i++)
        {
            if(graph[receiverIndex][i] != null)
            {
                StringValue sender = new StringValue();
                sender.setData(nodeIdMap.get(i).getNode().getLabel());
                sender.setSort(TermsFactory.eINSTANCE.createUserSort());
                
                ProductValue pValue = new ProductValue();
                pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
                pValue.getComponents().add(receiver);
                pValue.getComponents().add(sender);
                
                messages.add(pValue);
            }
        }
        
        return messages;
    }
    
	@Override
    public String validate(Object term)
    {
	    return null;
    }

	public Integer[][] getGraph()
    {
    	return graph;
    }

	public void setGraph(Integer[][] graph)
    {
    	this.graph = graph;
    }

	public Map<String, NodeWrapper> getNodeMap()
    {
    	return nodeMap;
    }

	public void setNodeMap(Map<String, NodeWrapper> nodeMap)
    {
    	this.nodeMap = nodeMap;
    }

	public Map<Integer, NodeWrapper> getNodeIdMap()
    {
    	return nodeIdMap;
    }

	public void setNodeIdMap(Map<Integer, NodeWrapper> nodeIdMap)
    {
    	this.nodeIdMap = nodeIdMap;
    }
}
