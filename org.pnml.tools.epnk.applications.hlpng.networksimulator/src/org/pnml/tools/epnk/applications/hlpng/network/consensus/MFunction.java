package org.pnml.tools.epnk.applications.hlpng.network.consensus;

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

public class MFunction implements IEvaluator
{
    private List<AbstractValue> messages = null;
    private List<NodeWrapper> nodes = null;
    
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
        
        for(AbstractValue value : getMessages())
        {
            msValue.put(value, 1);
        }
        
        return msValue;
    }
    
    public List<AbstractValue> getMessages()
    {
        if(messages == null)
        {
            messages = new ArrayList<AbstractValue>();
            for(NodeWrapper i : nodes)
            {
                for(NodeWrapper j : nodes)
                {
                    StringValue strVal0 = new StringValue();
                    strVal0.setData(i.getNode().getLabel());
                    strVal0.setSort(TermsFactory.eINSTANCE.createUserSort());
                    
                    StringValue strVal1 = new StringValue();
                    strVal1.setData(j.getNode().getLabel());
                    strVal1.setSort(TermsFactory.eINSTANCE.createUserSort());
                    
                    ProductValue pValue = new ProductValue();
                    pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
                    pValue.getComponents().add(strVal0);
                    pValue.getComponents().add(strVal1);
                    
                    messages.add(pValue);   
                }
            }   
        }
        return messages;
    }

	@Override
    public String validate(Object term)
    {
	    return null;
    }

	public List<NodeWrapper> getNodes()
    {
    	return nodes;
    }

	public void setNodes(List<NodeWrapper> nodes)
    {
    	this.nodes = nodes;
    }

	public void setMessages(List<AbstractValue> messages)
    {
    	this.messages = messages;
    }
}
