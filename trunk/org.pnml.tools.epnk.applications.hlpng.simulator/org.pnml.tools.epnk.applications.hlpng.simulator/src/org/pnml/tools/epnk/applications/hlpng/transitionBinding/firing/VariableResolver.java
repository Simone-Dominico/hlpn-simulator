package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.pnml.tools.epnk.applications.hlpng.jobs.InputDialogJob;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AbstractReversibleOperation;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.concretesyntax.HLPNGParser;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

import parserrules.Rules;
import serializer.Serializer;

public class VariableResolver
{
	private Map<TermWrapper, TermAssignment> termMap = null;
	private ReversibleOperationManager reversibleOperationManager = null;
	private EvaluationManager evaluationManager = null;
	
	private List<Pair<Set<TermWrapper>, TermAssignment>> termAssigments = null;
	private List<TermWrapper> resolved = null;
	private final Rules rules;
	private final Display display;
	
	public VariableResolver(Map<TermWrapper, TermAssignment> tm,
			ReversibleOperationManager reversibleOperationManager,
			EvaluationManager evaluationManager, Rules rules,
			Display display)
	{
		this.display = display;
		this.rules = rules;
		this.termMap = tm;
		this.reversibleOperationManager = reversibleOperationManager;
		this.evaluationManager = evaluationManager;
		
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
				findAllVars(ta.getTermWrapper().getRootTerm(), varSet);
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
        	final TermAssignment ve = pair.getValue();
        	final Set<TermWrapper> varSet = pair.getKey();
        	
        	Set<TermWrapper> allVars = null;
        	boolean showDialog = true;
        	
        	while(showDialog)
            {
	            if(ve.getTermWrapper() instanceof AbstractReversibleOperation)
	            {
		            AbstractReversibleOperation op = ((AbstractReversibleOperation) ve
		                    .getTermWrapper());
		            if(reversibleOperationManager.resolveAll(ve.getValues(),
		                    op, termMap))
		            {
			            showDialog = false;
		            }
		            else if(allVars == null)
		            {
		            	allVars = new HashSet<TermWrapper>();
		            	findAllVars(ve.getTermWrapper().getRootTerm(), allVars);
		            }
	            }
	            // a dialog pops up to ask for a partial solution
	            if(showDialog)
	            {
	            	final String termTxt = (new Serializer(null, rules))
		                    .unparse(ve.getTermWrapper().getRootTerm(), "Term");
	            	final Map<String, String> knownValues = new HashMap<String, String>();
	            	for(TermWrapper var : allVars)
	            	{
	            		if(termMap.containsKey(var))
	            		{
	            			varSet.remove(var);
	            			
	            			knownValues.put(var.getName(), 
	            					termMap.get(var).getValues().toString());
	            		}
	            	}
	            	final InputDialogJob inputThread = 
	            			new InputDialogJob(display, knownValues, 
	            					"The Simulator cannot solve the following equation:\n"
	            	                        + termTxt + "=" + ve.getValues() + "\n"
	            	                        + "Please, provide a sufficient part of the solution!",
	            	                "Currently the following variable bindings are known:",
	            	                varSet);
		            display.syncExec(inputThread);
		            
		            if(!inputThread.isCanceled())
	            	{
	            		parseSolution(inputThread.getMapping(), evaluationManager, termMap, varSet);
	            	}
	            	else
	            	{
	            		showDialog = false;
	            	}
	            }
            }
        }
		
		return termMap;
	}
	
	private static void parseSolution(final Map<TermWrapper, String> map, 
			final EvaluationManager evaluationManager, 
			final Map<TermWrapper, TermAssignment> termMap,
			final Set<TermWrapper> varSet)
	{
		for(Entry<TermWrapper, String> entry : map.entrySet())
		{
			try
			{
				Term term = HLPNGParser.getHLPNGParser().parseTerm(entry.getValue());

				if(term != null
				        && isGroundTerm(term)
				        && entry.getKey().getRootTerm().getSort().isSuperSortOf(term.getSort()))
				{
					TermWrapper variable = entry.getKey();

					final Set<IValue> values = evaluationManager.evaluateAll(term, termMap);

					if(termMap.containsKey(variable))
					{
						termMap.get(variable).getValues().addAll(values);
					}
					else
					{
						TermAssignment ta = new TermAssignment();
						ta.getValues().addAll(values);
						ta.setTermWrapper(variable);

						termMap.put(variable, ta);
					}
				}
			}
			catch(Exception e)
			{
				System.err.println("WRN: " + e.getMessage());
			}
		}
	}
	
	private static void findAllVars(Term term, Set<TermWrapper> vars)
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
				findAllVars(subterm, vars);
			}
		}
	}
	
	private static boolean isGroundTerm(Term term)
	{
		final Iterator<EObject> iterator = term.eAllContents();
		while(iterator.hasNext())
		{
			if(iterator.next() instanceof Variable)
			{
				return false;
			}
		}
		return true;
	}
}
