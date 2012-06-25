package org.pnml.tools.epnk.applications.hlpng.network.echo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.applications.hlpng.utils.AbstractFunction;
import org.pnml.tools.epnk.applications.hlpng.utils.NodeWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class M1Function extends AbstractFunction implements IEvaluator
{
    private Integer[][] graph = null;
    private Map<String, NodeWrapper> nodeMap = null;
    private Map<Integer, NodeWrapper> nodeIdMap = null;
    
    @Override
    public IValue evaluate(Term term, EvaluationManager evaluationManager,
            Map<TermWrapper, IValue> assignments) throws UnknownVariableException
    {
        Operator operator = (Operator) term;
        List<IValue> values = new ArrayList<IValue>();
        for(Term subterm : operator.getSubterm())
        {
            IEvaluator evaluator = evaluationManager.getHandler(subterm.getClass()); 
            IValue value = evaluator.evaluate(subterm, evaluationManager, assignments);
            values.add(value);
        }
            
        UserOperator uOp = (UserOperator) operator;
        
        IMSValue msValue = runtimeValueFactory.createMSValue();
        msValue.setSort(uOp.getOutputSort());
        
        List<IValue> list = new ArrayList<IValue>(values);
        for(IValue value : getM1(graph, nodeMap, nodeIdMap, list.get(0)))
        {
            msValue.put(value, 1);
        }
        
        return msValue;
    }
    
    private static List<IValue> getM1(Integer[][] graph, Map<String, NodeWrapper> nodeMap,
            Map<Integer, NodeWrapper> nodeIdMap, IValue sender)
    {
        List<IValue> messages = new ArrayList<IValue>();
        
        int senderIndex = nodeMap.get(((StringValue)sender).getData()).getId();
        
        for(int i = 0; i < graph[senderIndex].length; i++)
        {
            if(graph[senderIndex][i] != null)
            {           
                StringValue receiver = new StringValue();
                receiver.setData(nodeIdMap.get(i).getNode().getLabel());
                receiver.setSort(TermsFactory.eINSTANCE.createUserSort());
                
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
