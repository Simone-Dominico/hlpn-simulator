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
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.DatatypesComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.MultisetComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.NumberOfComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ReversibleOperationComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.TupleComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.VariableComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.AdditionEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.BooleansEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IntegersEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.MultiplicationEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.MultisetsEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.StringsEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.TermsEval;
import org.pnml.tools.epnk.applications.registry.ApplicationRegistry;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.impl.BooleanConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.AdditionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.MultiplicationImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
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
			// integers package
			evaluationManager.register(NumberConstantImpl.class.getPackage(), new IntegersEval());
			// booleans package
			evaluationManager.register(BooleanConstantImpl.class.getPackage(), new BooleansEval());
			// strings package
			evaluationManager.register(StringConstantImpl.class.getPackage(), new StringsEval());			
			// multisets package
			evaluationManager.register(NumberOfImpl.class.getPackage(), new MultisetsEval());
			// terms package
			evaluationManager.register(TupleImpl.class.getPackage(), new TermsEval());
			
			evaluationManager.register(AdditionImpl.class, new AdditionEval());
			evaluationManager.register(MultiplicationImpl.class, new MultiplicationEval());
			
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
			DatatypesComparator datatypesComparator = new DatatypesComparator();
			comparisonManager.register(NumberConstantImpl.class.getPackage(), datatypesComparator);
			comparisonManager.register(BooleanConstantImpl.class.getPackage(), datatypesComparator);
			comparisonManager.register(StringConstantImpl.class.getPackage(), datatypesComparator);
			
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
