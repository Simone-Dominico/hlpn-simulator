package org.pnml.tools.epnk.applications.hlpng.utils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.pnml.tools.epnk.applications.activator.Activator;
import org.pnml.tools.epnk.applications.hlpng.simulator.HLSimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.BooleanConstantComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.MultisetComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.NumberConstantComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.NumberOfComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ReversibleOperationComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.StringConstantComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.TupleComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.VariableComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AddEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AdditionEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AndEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.BooleanConstantEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ConcatenationEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EqualityEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.GreaterThanEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.InequalityEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.LessThanEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.MultiplicationEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.NumberConstantEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.NumberOfEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.OrEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.StringConstantEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.SubtractEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.TupleEval;
import org.pnml.tools.epnk.applications.registry.ApplicationRegistry;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.impl.AndImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.impl.BooleanConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.impl.EqualityImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.impl.InequalityImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.impl.OrImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.AdditionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.GreaterThanImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.LessThanImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.MultiplicationImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.AddImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.SubtractImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.impl.ConcatenationImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.impl.StringConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.MultiSetOperatorImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.TupleImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.UserOperatorImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.VariableImpl;

public class StartSimulatorApp implements IObjectActionDelegate
{

	private PetriNet petrinet;

	@Override
	public void run(IAction action)
	{
		IConfigurationElement[] config = Platform.getExtensionRegistry()
		        .getConfigurationElementsFor("org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions");

		// init the evaluation manager
		EvaluationManager evaluationManager = new EvaluationManager();
		{
			// user extensions
			for(IConfigurationElement e : config)
			{
				try
				{
					evaluationManager.register(UserOperatorImpl.class,
					        (IEvaluator) e.createExecutableExtension("class"));
				}
				catch(CoreException e1)
				{
					e1.printStackTrace();
				}
			}
			// others
			evaluationManager.register(GreaterThanImpl.class, new GreaterThanEval());
			evaluationManager.register(LessThanImpl.class, new LessThanEval());
			evaluationManager.register(AndImpl.class, new AndEval());
			evaluationManager.register(OrImpl.class, new OrEval());
			evaluationManager.register(ConcatenationImpl.class, new ConcatenationEval());
			evaluationManager.register(InequalityImpl.class, new InequalityEval());
			evaluationManager.register(EqualityImpl.class, new EqualityEval());
			evaluationManager.register(NumberConstantImpl.class, new NumberConstantEval());
			evaluationManager.register(StringConstantImpl.class, new StringConstantEval());
			evaluationManager.register(BooleanConstantImpl.class, new BooleanConstantEval());
			evaluationManager.register(NumberOfImpl.class, new NumberOfEval());
			evaluationManager.register(AddImpl.class, new AddEval());
			evaluationManager.register(SubtractImpl.class, new SubtractEval());
			evaluationManager.register(TupleImpl.class, new TupleEval());
			
			evaluationManager.register(AdditionImpl.class, new AdditionEval());
			evaluationManager.register(MultiplicationImpl.class, new MultiplicationEval());
		}
		
		// init the reversible operation manager
		ReversibleOperationManager reversibleOperationManager = new ReversibleOperationManager(evaluationManager);
		{
			reversibleOperationManager.register(AdditionImpl.class, new AdditionEval());
			reversibleOperationManager.register(MultiplicationImpl.class, new MultiplicationEval());
		}
				
		// init the comparison manager
		ComparisonManager comparisonManager = new ComparisonManager();
		{
			comparisonManager.register(NumberConstantImpl.class, new NumberConstantComparator());
			comparisonManager.register(StringConstantImpl.class, new StringConstantComparator());
			comparisonManager.register(BooleanConstantImpl.class, new BooleanConstantComparator());
			
			comparisonManager.register(TupleImpl.class, new TupleComparator(comparisonManager));
			comparisonManager.register(MultiSetOperatorImpl.class, new MultisetComparator(comparisonManager));
			comparisonManager.register(NumberOfImpl.class, new NumberOfComparator(comparisonManager));
			
			ReversibleOperationComparator binEval = 
					new ReversibleOperationComparator(evaluationManager, reversibleOperationManager);
			comparisonManager.register(AdditionImpl.class, binEval);
			comparisonManager.register(MultiplicationImpl.class, binEval);
			
			comparisonManager.register(VariableImpl.class, new VariableComparator());
		}

		HLSimulator application = new HLSimulator(petrinet, evaluationManager, 
				comparisonManager, reversibleOperationManager);
		Activator activator = Activator.getInstance();
		ApplicationRegistry registry = activator.getApplicationRegistry();
		registry.addApplication(application);
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection)
	{
		petrinet = null;
		if(selection instanceof IStructuredSelection)
		{
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if(structuredSelection.size() == 1)
			{
				java.lang.Object selected = structuredSelection.getFirstElement();
				if(selected instanceof PetriNet)
				{
					petrinet = (PetriNet) selected;
				}
			}
		}
		action.setEnabled(isEnabled());
	}

	private boolean isEnabled()
	{
		return petrinet != null;
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart){}

}
