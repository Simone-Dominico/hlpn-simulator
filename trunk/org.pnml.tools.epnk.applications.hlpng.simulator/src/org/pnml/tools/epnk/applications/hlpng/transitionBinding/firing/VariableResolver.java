package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AbstractReversibleOperation;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class VariableResolver
{
	private Map<TermWrapper, TermAssignment> termMap = null;
	private ReversibleOperationManager reversibleOperationManager = null;
	
	public VariableResolver(Map<TermWrapper, TermAssignment> termMap,
			ReversibleOperationManager reversibleOperationManager)
	{
		this.termMap = termMap;
		this.reversibleOperationManager = reversibleOperationManager;
	}
	
	public Map<TermWrapper, TermAssignment> solve()
	{
		List<TermAssignment> unresolved = new ArrayList<TermAssignment>();
		for(TermWrapper key : termMap.keySet())
		{
			TermAssignment ve = termMap.get(key);
			if(!(ve.getTermWrapper().getRootTerm() instanceof Variable))
			{
				unresolved.add(ve);
			}
		}
		for(TermAssignment ve : unresolved)
		{
			termMap.remove(ve.getTermWrapper());
		}
		do
		{		
			List<TermAssignment> repeat = new ArrayList<TermAssignment>();
			for(TermAssignment ve : unresolved)
			{
				AbstractReversibleOperation op = ((AbstractReversibleOperation)ve.getTermWrapper());
				
				if(!reversibleOperationManager.resolveAll(ve.getValues(), op, termMap))
				{
					repeat.add(ve);
				}
			}
			unresolved = repeat;
		}
		while(unresolved.size() > 0);
		
		return termMap;
	}
}
