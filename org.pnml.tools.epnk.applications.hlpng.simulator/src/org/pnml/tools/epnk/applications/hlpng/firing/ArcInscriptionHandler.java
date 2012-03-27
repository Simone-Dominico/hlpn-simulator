package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.firing.resolvers.IAssignable;
import org.pnml.tools.epnk.applications.hlpng.firing.resolvers.ResolutionManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.NumberConstant;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.NumberOf;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.MultiSetOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class ArcInscriptionHandler
{
	private MultiSetOperator multiSetOperator = null;
	private ResolutionManager evaluationManager = null;
	
	public ArcInscriptionHandler(MultiSetOperator multiSetOperator, ResolutionManager evaluationManager)
	{
		this.multiSetOperator = multiSetOperator;
		this.evaluationManager = evaluationManager;
	}
	
	public List<List<Map<String, VariableEvaluation>>> match(MSValue value)
	{
		// each inscription term compared to all multiset terms
		List<List<Map<String, VariableEvaluation>>> list = null;
		{
			list = new ArrayList<List<Map<String,VariableEvaluation>>>();
			if(multiSetOperator instanceof NumberOf)
			{
				List<Map<String, VariableEvaluation>> assignments = contains(value, 
						evaluationManager, (NumberOf)multiSetOperator);
				
				list.add(assignments);
			}
			else
			{
				for(Term refValue : multiSetOperator.getSubterm())
				{
					List<Map<String, VariableEvaluation>> assignments = contains(value, 
							evaluationManager, ((NumberOf)refValue));
					
					list.add(assignments);
				}
			}
		}
		return list;
	}

	private static List<Map<String, VariableEvaluation>> contains(
			MSValue multiset, ResolutionManager resolutionManager, NumberOf numberOf)
	{
		Term refMul = numberOf.getSubterm().get(0);
		Term refValue = numberOf.getSubterm().get(1);
		
		List<Map<String, VariableEvaluation>> list = new ArrayList<Map<String,VariableEvaluation>>();

		for(AbstractValue testValue : multiset.getValues().keySet())
		{
			Map<String, VariableEvaluation> assignments = new HashMap<String, VariableEvaluation>();
			
			IAssignable valueEvaluator = resolutionManager.getComparator(refValue.getClass());
			boolean madeAssignment = false;
			if(valueEvaluator.compare(resolutionManager, refValue, testValue, assignments))
			{
				Integer multiplicity = multiset.getValues().get(testValue);
				
				if(refMul instanceof NumberConstant && ((NumberConstant)refMul).getValue() <= multiplicity)
				{
					madeAssignment = true;
				}
				else if(!(refMul instanceof NumberConstant))
				{
					IAssignable mulEvaluator = resolutionManager.getComparator(refMul.getClass());
					List<Map<String, VariableEvaluation>> copies = new ArrayList<Map<String,VariableEvaluation>>();
					for(int i = 1; i <= multiplicity; i++)
					{
						Map<String, VariableEvaluation> copy = copyMap(assignments);
						copies.add(copy);
						
						PosValue testMul = new PosValue();
						testMul.setN(i);
						testMul.setSort(IntegersFactory.eINSTANCE.createPositive());
						
						mulEvaluator.compare(resolutionManager, refMul, testMul, copy);
					}
					for(Map<String, VariableEvaluation> copy : copies)
					{
						assignments = mergeMap(assignments, copy);
					}
					madeAssignment = true;
				}
			}
			if(madeAssignment)
			{
				list.add(assignments);
			}
		}
		return list;
	}

	public MultiSetOperator getMultiSetOperator()
    {
    	return multiSetOperator;
    }
	
	private static Map<String, VariableEvaluation> copyMap(Map<String, VariableEvaluation> map)
	{
		Map<String, VariableEvaluation> copy = new HashMap<String, VariableEvaluation>();
		
		for(String key : map.keySet())
		{
			VariableEvaluation ve = map.get(key);
			VariableEvaluation copyVe = new VariableEvaluation();
			copyVe.setVariable(ve.getVariable());
			copyVe.setVariableName(ve.getVariableName());
			copyVe.getValues().addAll(ve.getValues());
			
			copy.put(ve.getVariableName(), copyVe);
		}
		
		return copy;
	}
	
	private static Map<String, VariableEvaluation> mergeMap(Map<String, VariableEvaluation> map1,
			Map<String, VariableEvaluation> map2)
	{
		Map<String, VariableEvaluation> merge = copyMap(map1);
		
		for(String key : map2.keySet())
		{
			if(merge.containsKey(key))
			{
				merge.get(key).getValues().addAll(map2.get(key).getValues());
			}
			else
			{
				VariableEvaluation ve = map2.get(key);
				VariableEvaluation copyVe = new VariableEvaluation();
				copyVe.setVariable(ve.getVariable());
				copyVe.setVariableName(ve.getVariableName());
				copyVe.getValues().addAll(ve.getValues());
				
				merge.put(ve.getVariableName(), copyVe);		
			}
		}
		return merge;
	}
}
