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
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class RFFunction implements IEvaluator
{
    private List<AbstractValue> messages = null;

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
        for(AbstractValue value : getMessagesBySender(list.get(0), messages))
        {
            msValue.getValues().put(value, 1);
        }
        
        return msValue;
    }
    
    private static List<AbstractValue> getMessagesBySender(AbstractValue sender, List<AbstractValue> allMessages)
    {
        List<AbstractValue> messages = new ArrayList<AbstractValue>();
        
        for(AbstractValue pValue : allMessages)
        {
            StringValue s = (StringValue)((ProductValue)pValue).getComponents().get(0);
            if(s.getData().equals(((StringValue)sender).getData()))
            {
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

	public List<AbstractValue> getMessages()
    {
    	return messages;
    }

	public void setMessages(List<AbstractValue> messages)
    {
    	this.messages = messages;
    }
}
