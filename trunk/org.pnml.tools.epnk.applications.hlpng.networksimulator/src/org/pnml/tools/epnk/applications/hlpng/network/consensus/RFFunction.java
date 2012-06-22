package org.pnml.tools.epnk.applications.hlpng.network.consensus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
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
    private List<IValue> messages = null;

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
        
        MSValue msValue = new MSValue();
        msValue.setSort(uOp.getOutputSort());
        
        List<IValue> list = new ArrayList<IValue>(values);
        for(IValue value : getMessagesBySender(list.get(0), messages))
        {
            msValue.put(value, 1);
        }
        
        return msValue;
    }
    
    private static List<IValue> getMessagesBySender(IValue sender, List<IValue> allMessages)
    {
        List<IValue> messages = new ArrayList<IValue>();
        
        for(IValue pValue : allMessages)
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

	public List<IValue> getMessages()
    {
    	return messages;
    }

	public void setMessages(List<IValue> messages)
    {
    	this.messages = messages;
    }
}
