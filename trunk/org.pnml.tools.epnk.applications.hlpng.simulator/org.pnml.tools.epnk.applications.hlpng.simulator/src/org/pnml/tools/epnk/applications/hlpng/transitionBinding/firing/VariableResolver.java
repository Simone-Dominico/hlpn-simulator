package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AbstractReversibleOperation;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;

public class VariableResolver
{
	private Map<TermWrapper, TermAssignment> termMap = null;
	private ReversibleOperationManager reversibleOperationManager = null;
	private List<Pair<Set<TermWrapper>, Map<TermWrapper, TermAssignment>>> varsAndMatches = null;
	
	private List<TermAssignment> termAssigments = null;
	private List<TermWrapper> resolved = null;
	
	public VariableResolver(List<Pair<Set<TermWrapper>, Map<TermWrapper, TermAssignment>>> varsAndMatches,
			Map<TermWrapper, TermAssignment> termMap,
			ReversibleOperationManager reversibleOperationManager)
	{
		this.varsAndMatches = varsAndMatches;
		this.termMap = termMap;
		this.reversibleOperationManager = reversibleOperationManager;
		
		// resolved vars and unresolved terms
		this.termAssigments = new ArrayList<TermAssignment>();
		this.resolved = new ArrayList<TermWrapper>();
		for(TermWrapper key : termMap.keySet())
		{
			TermAssignment ve = termMap.get(key);
			if(ve.getTermWrapper() instanceof VariableWrapper)
			{
				resolved.add(ve.getTermWrapper());
			}
			else
			{
				termAssigments.add(ve);
			}
		}
		
		// remove all term assignments
		for(TermAssignment ve : termAssigments)
		{
			termMap.remove(ve.getTermWrapper());
		}
		
		// remove all resolved variables
		for(Pair<Set<TermWrapper>, Map<TermWrapper, TermAssignment>> p : varsAndMatches)
		{
			p.getKey().removeAll(resolved);
		}
		
		// sort by the number of unresolved variables
		Collections.sort(varsAndMatches, new Comparator<Pair<Set<TermWrapper>, Map<TermWrapper, TermAssignment>>>()
		{
			@Override
            public int compare(
                    Pair<Set<TermWrapper>, Map<TermWrapper, TermAssignment>> o1,
                    Pair<Set<TermWrapper>, Map<TermWrapper, TermAssignment>> o2)
            {
	            return o1.getKey().size() - o2.getKey().size();
            }
		});
		
		// remove arcs which assignments are completely resolved
		while(varsAndMatches.size() > 0 && varsAndMatches.get(0).getKey().size() == 0)
		{
			varsAndMatches.remove(0);
		}
	}
	
	public Map<TermWrapper, TermAssignment> solve()
	{
		// there is nothing to resolve
		if(varsAndMatches.size() == 0)
		{
			return termMap;
		}
		/*
		{
			Set<TermWrapper> set = new HashSet<TermWrapper>();
			for(Pair<Set<TermWrapper>, Map<TermWrapper, TermAssignment>> p : varsAndMatches)
			{
				set.addAll(p.getKey());
			}
			new InputDialog(set, Display.getDefault());
		}
		*/
		do
		{		
			List<TermAssignment> repeat = new ArrayList<TermAssignment>();
			for(TermAssignment ve : termAssigments)
			{
				AbstractReversibleOperation op = ((AbstractReversibleOperation)ve.getTermWrapper());
				
				if(!reversibleOperationManager.resolveAll(ve.getValues(), op, termMap))
				{
					repeat.add(ve);
				}
			}
			termAssigments = repeat;
		}
		while(termAssigments.size() > 0);
		
		return termMap;
	}
}
