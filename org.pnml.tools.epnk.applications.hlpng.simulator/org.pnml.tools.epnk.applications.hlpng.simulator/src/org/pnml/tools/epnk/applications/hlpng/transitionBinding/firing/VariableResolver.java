package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.swt.widgets.Display;
import org.pnml.tools.epnk.applications.hlpng.presentation.InputDialog;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AbstractReversibleOperation;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.concretesyntax.HLPNGParser;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

public class VariableResolver
{
	private Map<TermWrapper, TermAssignment> termMap = null;
	private ReversibleOperationManager reversibleOperationManager = null;
	
	private List<Pair<Set<TermWrapper>, TermAssignment>> termAssigments = null;
	private List<TermWrapper> resolved = null;
	
	public VariableResolver(Map<TermWrapper, TermAssignment> tm,
			ReversibleOperationManager reversibleOperationManager)
	{
		this.termMap = tm;
		this.reversibleOperationManager = reversibleOperationManager;
		
		// resolved vars and unresolved terms
		this.termAssigments = new ArrayList<Pair<Set<TermWrapper>, TermAssignment>>();
		this.resolved = new ArrayList<TermWrapper>();
		for(Entry<TermWrapper, TermAssignment> entry : termMap.entrySet())
		{
			TermAssignment ta = entry.getValue();
			if(ta.getTermWrapper() instanceof VariableWrapper)
			{
				resolved.add(ta.getTermWrapper());
			}
			else
			{
				Set<TermWrapper> varSet = new HashSet<TermWrapper>();
				allVars(ta.getTermWrapper().getRootTerm(), varSet);
				termAssigments.add(new Pair<Set<TermWrapper>, TermAssignment>(varSet, ta));
			}
		}
		
		// remove all term assignments from termMap and resolved variables
		for(Pair<Set<TermWrapper>, TermAssignment> pair : termAssigments)
		{
			TermAssignment ta = pair.getValue();
			termMap.remove(ta.getTermWrapper());
			
			pair.getKey().removeAll(resolved);
		}
		
		// sort by the number of unresolved variables
		Collections.sort(termAssigments, 
				new Comparator<Pair<Set<TermWrapper>, TermAssignment>>()
		{
			@Override
            public int compare(
            		Pair<Set<TermWrapper>, TermAssignment> o1,
            		Pair<Set<TermWrapper>, TermAssignment> o2)
            {
	            return o1.getKey().size() - o2.getKey().size();
            }
		});
		
		// remove arcs which assignments are completely resolved
		while(termAssigments.size() > 0 && 
				termAssigments.get(0).getKey().size() == 0)
		{
			termAssigments.remove(0);
		}
	}
	
	public Map<TermWrapper, TermAssignment> solve()
	{
		// there is nothing to resolve
		if(termAssigments.size() == 0)
		{
			return termMap;
		}
		
		for(Pair<Set<TermWrapper>, TermAssignment> pair : termAssigments)
        {
        	TermAssignment ve = pair.getValue();
        	
        	boolean showDialog = true;
        	
        	if(ve.getTermWrapper() instanceof AbstractReversibleOperation)
        	{
        		AbstractReversibleOperation op = 
        				((AbstractReversibleOperation)ve.getTermWrapper());
        		if(reversibleOperationManager.resolveAll(ve.getValues(), op, termMap))
                {
        			showDialog = false;
                }
        	}
            
            // a dialog pops up to ask a partial solution
            if(showDialog)
            {
                final Set<TermWrapper> set = new HashSet<TermWrapper>(pair.getKey());
                final Display display = Display.getCurrent();
                display.syncExec(new Runnable()
                {
                    public void run()
                    {
                        new InputDialog(set, display);
                        
                        Term term = HLPNGParser.getHLPNGParser().parseTerm("1`9++1");
                        System.out.println(term);
                    }
                });
            }
        }
		
		return termMap;
	}
	
	private static void allVars(Term term, Set<TermWrapper> vars)
	{
		if(term instanceof Variable)
		{
			VariableWrapper wrapper = new VariableWrapper();
			wrapper.setRootTerm(term);
			wrapper.setVariable((Variable)term);
			
			vars.add(wrapper);
		}
		else if(term instanceof Operator)
		{
			Operator operator = (Operator) term;
			for(Term subterm : operator.getSubterm())
			{
				allVars(subterm, vars);
			}
		}
	}
}
