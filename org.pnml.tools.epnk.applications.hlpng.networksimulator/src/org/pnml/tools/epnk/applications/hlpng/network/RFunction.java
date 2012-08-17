package org.pnml.tools.epnk.applications.hlpng.network;

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
import org.pnml.tools.epnk.applications.hlpng.utils.NodeWrapper;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class RFunction extends AbstractFunction
{
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
        for(IValue value : getReceiver(graph, nodeMap, nodeIdMap, list.get(0)))
        {
            msValue.put(value, 1);
        }
        
        return msValue;
    }
    
    private static List<IValue> getReceiver(Integer[][] graph, Map<String, NodeWrapper> nodeMap,
            Map<Integer, NodeWrapper> nodeIdMap, IValue receiver)
    {
        List<IValue> messages = new ArrayList<IValue>();
        
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
}
