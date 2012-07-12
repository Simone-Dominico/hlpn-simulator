package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.HashMap;
import java.util.Iterator;
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
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Variable;

import parserrules.Rules;
import serializer.Serializer;

public class VariableResolver
{   
    private final Map<TermWrapper, TermAssignment> termMap;
    private final ReversibleOperationManager reversibleOperationManager;
    private final EvaluationManager evaluationManager;
    
    private final Rules rules;
    private final Display display;
    
    private final VariableDependencyManager dependencyManager;
    
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
        
        this.dependencyManager = new VariableDependencyManager(tm);
    }
    
    public Map<TermWrapper, TermAssignment> solve()
    {
        while(dependencyManager.hasNext())
        {
            final Pair<Set<TermWrapper>, TermAssignment> pair = 
                    dependencyManager.next();
            final TermAssignment termAssignment = pair.getValue();
            final Set<TermWrapper> varSet = pair.getKey();
            
            Set<TermWrapper> allVars = null;
            boolean showDialog = true;
            
            while(showDialog)
            {
                if(termAssignment.getTermWrapper() instanceof AbstractReversibleOperation)
                {
                    AbstractReversibleOperation op = ((AbstractReversibleOperation) termAssignment
                            .getTermWrapper());
                    if(reversibleOperationManager.resolveAll(termAssignment.getValues(),
                            op, termMap))
                    {
                        showDialog = false;
                    }
                    else if(allVars == null)
                    {
                        allVars = dependencyManager.getVars(termAssignment.getTermWrapper());
                    }
                }
                // a dialog pops up to ask for a sufficient solution
                if(showDialog)
                {
                    final String termTxt = (new Serializer(null, rules))
                            .unparse(termAssignment.getTermWrapper().getRootTerm(), "Term");
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
                                            + termTxt + "=" + termAssignment.getValues() + "\n"
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
