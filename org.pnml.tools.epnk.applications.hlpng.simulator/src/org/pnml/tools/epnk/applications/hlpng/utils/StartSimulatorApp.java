package org.pnml.tools.epnk.applications.hlpng.utils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.pnml.tools.epnk.applications.activator.Activator;
import org.pnml.tools.epnk.applications.hlpng.resources.ResourceManager;
import org.pnml.tools.epnk.applications.hlpng.simulator.HLSimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.DataTypeEvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.MultisetsEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.validation.ValidationDelegateClientSelector;
import org.pnml.tools.epnk.applications.registry.ApplicationRegistry;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.AllImpl;

public class StartSimulatorApp implements IObjectActionDelegate
{

	private PetriNet petrinet;

	@Override
	public void run(IAction action)
	{
		// init the evaluation manager
		EvaluationManager evaluationManager = 
				ResourceManager.createEvaluationManager("org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions");
		DataTypeEvaluationManager dataTypeEvaluationManager =
				ResourceManager.createDataTypeEvaluationManager("org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.userDataTypes");
		MultisetsEval multisetsEval = 
                (MultisetsEval)evaluationManager.getHandler(AllImpl.class);
        multisetsEval.setDataTypeEvaluationManager(dataTypeEvaluationManager);
		
		// init the reversible operation manager
		ReversibleOperationManager reversibleOperationManager = ResourceManager.createReversibleOperationManager(evaluationManager);
				
		// init the comparison manager
		ComparisonManager comparisonManager = ResourceManager.createComparisonManager(evaluationManager, reversibleOperationManager);

		// perform validaion
		ValidationDelegateClientSelector.running = true;
		IBatchValidator validator = (IBatchValidator) ModelValidationService
		        .getInstance().newValidator(EvaluationMode.BATCH);
		validator.setIncludeLiveConstraints(true);
		IStatus status = validator.validate(petrinet);
		ValidationDelegateClientSelector.running = false;
System.out.println(status);
		if(status.isOK())
		{
			// creates a simulator
			HLSimulator application = new HLSimulator(petrinet, evaluationManager, 
					comparisonManager, reversibleOperationManager,
					Display.getDefault().getSystemFont(), true);
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
