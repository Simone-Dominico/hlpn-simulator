package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.BooleanValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Natural;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Positive;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public class ConsistencyManager
{
	public static boolean check(AbstractValue value, Sort sort)
	{
		if(sort instanceof org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number)
		{
			NumberValue nValue = (NumberValue)value;
			int n = nValue.getN();
			if(sort instanceof Positive && n <= 0)
			{
				return false;
			}
			if(sort instanceof Natural && n < 0)
			{
				return false;
			}
			return true;
		}
		
		if(value instanceof MSValue)
		{
			for(AbstractValue key : ((MSValue)value).getValues().keySet())
			{
				Integer n = ((MSValue)value).getValues().get(key);
				if(n == null || n < 0)
				{
					return false;
				}
			}
			return true;
		}
		
		return true;
	}
	
	public static Map<TermWrapper, TermAssignment> checkParams(Map<TermWrapper, TermAssignment> globalMap)
	{
		Map<TermWrapper, TermAssignment> filtered = new HashMap<TermWrapper, TermAssignment>();
		for(TermWrapper key : globalMap.keySet())
		{
			TermAssignment oldVe = globalMap.get(key);

			Set<AbstractValue> newValues = new HashSet<AbstractValue>();
			for(AbstractValue value : oldVe.getValues())
			{
				if(check(value, key.getRootTerm().getSort()))
				{
					newValues.add(value);
				}
			}
			
			if(newValues.size() > 0)
			{
				TermAssignment newVe = new TermAssignment();
				newVe.setTermWrapper(key);
				newVe.setValues(newValues);
				
				filtered.put(key, newVe);
			}
		}
		return filtered;
	}
	
	public static List<FiringMode> checkSolution(List<Map<TermWrapper, AbstractValue>> varSets,
			Map<String, ArcInscriptionHandler> incomingArcs,
			Map<String, PlaceMarking> runtimeValues, Transition transition,
			EvaluationManager evaluationManager) throws UnknownVariableException
	{
		List<FiringMode> assignemnts = new ArrayList<FiringMode>();
		for(Map<TermWrapper, AbstractValue> params : varSets)
		{
			boolean conditionSatisfied = true;
			
			if(transition.getCondition() != null && 
					transition.getCondition().getStructure() != null)
			{
				try
                {
	                AbstractValue conditionValue = 
	                		evaluationManager.evaluate(
	                				transition.getCondition().getStructure(), params);
	                conditionSatisfied = ((BooleanValue)conditionValue).getValue();
                }
                catch(Exception e)
                {
                	conditionSatisfied = false;
                }
			}
			
			if(conditionSatisfied)
			{
				FiringMode assignment = new FiringMode();
				assignment.setParams(params);
				assignment.setTransition(transition);
				
				boolean matched = true;
				for(String placeId : incomingArcs.keySet())
				{
					if(matched)
					{
						MSValue runtimeValue = runtimeValues.get(placeId).getMsValue();
						// it may be not possible to initialize some of the variables
						MSValue inscriptionValue = null;
	                    try
	                    {
		                    inscriptionValue = (MSValue)evaluationManager
		                    		.evaluate(incomingArcs.get(placeId).getOperator(), params);
		                    
		                    if(ConsistencyManager.check(inscriptionValue, null) && 
		                    		AbstractValueMath.lessEqual(inscriptionValue, runtimeValue))
		                    {
		                    	assignment.getValues().put(placeId, 
										AbstractValueMath.subtract(runtimeValue, inscriptionValue));	
		                    }
		                    else
		                    {
		                    	matched = false;
		                    }
	                    }
	                    catch(Exception e)
	                    {
	                    	matched = false;
	                    }
					}
				}
				if(matched)
				{
					assignemnts.add(assignment);
				}
			}	
		}
		return assignemnts;
	}
}
