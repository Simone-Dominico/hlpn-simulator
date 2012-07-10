package org.pnml.tools.epnk.applications.hlpng.utils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.pnml.tools.epnk.applications.activator.Activator;
import org.pnml.tools.epnk.applications.hlpng.resources.ResourceManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;
import org.pnml.tools.epnk.applications.hlpng.simulator.HLSimulator;
import org.pnml.tools.epnk.applications.hlpng.simulator.IFiringStrategy;
import org.pnml.tools.epnk.applications.hlpng.simulator.RandomFiringStrategy;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.validation.ValidationDelegateClientSelector;
import org.pnml.tools.epnk.applications.registry.ApplicationRegistry;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class StartSimulatorApp implements IObjectActionDelegate
{

	private PetriNet petrinet;

	@Override
	public void run(IAction action)
	{
		// runtime value factory
		RuntimeValueFactory runtimeValueFactory = new RuntimeValueFactory();
		// init the evaluation manager
		EvaluationManager evaluationManager = 
				ResourceManager.createEvaluationManager(runtimeValueFactory,
						"org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions");
		
		// init the reversible operation manager
		ReversibleOperationManager reversibleOperationManager = ResourceManager.createReversibleOperationManager(evaluationManager);
				
		// init the comparison manager
		ComparisonManager comparisonManager = ResourceManager.createComparisonManager(evaluationManager, reversibleOperationManager);
		
		// firing strategy
		IFiringStrategy strategy = ResourceManager.
				getFiringStrategy("org.pnml.tools.epnk.applications.hlpng.simulator.firingStrategy");
		if(strategy == null)
		{
			System.err.println("INFO: loading default firing strategy");
			strategy = new RandomFiringStrategy();
		}

		// perform validation
		ValidationDelegateClientSelector.running = true;
		IBatchValidator validator = (IBatchValidator) ModelValidationService
		        .getInstance().newValidator(EvaluationMode.BATCH);
		validator.setIncludeLiveConstraints(true);
		IStatus status = validator.validate(petrinet);
		ValidationDelegateClientSelector.running = false;

		if (!status.isOK()) 
		{
            ErrorDialog.openError(null, "Validation", "Validation Failed", status);
        }
		else
		{
			// creates a simulator
			HLSimulator application = new HLSimulator(petrinet, evaluationManager, 
					comparisonManager, reversibleOperationManager,
					Display.getDefault().getSystemFont(), runtimeValueFactory, 
					strategy, true);
			// registers the simulator
			Activator activator = Activator.getInstance();
			ApplicationRegistry registry = activator.getApplicationRegistry();
			registry.addApplication(application);	
		}
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