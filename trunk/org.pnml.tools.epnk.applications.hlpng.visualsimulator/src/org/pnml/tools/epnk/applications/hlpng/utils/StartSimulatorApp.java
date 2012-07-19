package org.pnml.tools.epnk.applications.hlpng.utils;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.pnml.tools.epnk.applications.activator.Activator;
import org.pnml.tools.epnk.applications.hlpng.contributors.ExtensionManager;
import org.pnml.tools.epnk.applications.hlpng.functions.AbstractFunction;
import org.pnml.tools.epnk.applications.hlpng.resources.ResourceManager;
import org.pnml.tools.epnk.applications.hlpng.simulator.MSListFactory;
import org.pnml.tools.epnk.applications.hlpng.simulator.VisualSimulator;
import org.pnml.tools.epnk.applications.hlpng.simulator.views.ISimulationViewController;
import org.pnml.tools.epnk.applications.hlpng.simulator.views.SimulationViewController;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UserOperatorEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.reversible.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.validation.ValidationDelegateClientSelector;
import org.pnml.tools.epnk.applications.registry.ApplicationRegistry;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.UserOperatorImpl;

import dk.dtu.imm.se2.group6.visual.Animator;

import visualsimulationconfig.VisualSimulatorConfig;
import visualsimulationconfig.VisualsimulationconfigPackage;

public class StartSimulatorApp implements IObjectActionDelegate
{
	private PetriNet petrinet;
    private String filename = null;
    private IFile pnfile = null;
    private String configExtension = "visualsimulationconfig";
    
	@Override
	public void run(IAction action)
	{
		ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
        VisualsimulationconfigPackage visualsimulationconfigPackage = VisualsimulationconfigPackage.eINSTANCE;
        
        String configFilePath = filename.replaceFirst(pnfile.getFileExtension(), configExtension);
        
        File file = new File(configFilePath);
        if(!file.exists())
        {
        	configFilePath = fileChooser(Display.getCurrent().getActiveShell(), ".");
        }
        
        Resource resource = null;
        try
        {
        	URI fileUri = URI.createFileURI(configFilePath);
	        resource = resourceSet.getResource(fileUri, true);
        }
        catch(Exception e){}

		if(resource != null && resource.getContents().size() > 0)
		{
			// runtime value factory
			MSListFactory factory = new MSListFactory();
			// init the evaluation manager
			EvaluationManager evaluationManager = ResourceManager.
					createEvaluationManager(factory,
							"org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions");

			// init the reversible operation manager
			ReversibleOperationManager reversibleOperationManager = 
					ResourceManager.createReversibleOperationManager(evaluationManager);
					
			// init the comparison manager
			ComparisonManager comparisonManager = 
					ResourceManager.createComparisonManager(evaluationManager, reversibleOperationManager);
			
			// init animator
			Animator animator = createAnimator(filename.replaceFirst(pnfile.getName(), ""));
			
			// init config
			VisualSimulatorConfig config = (VisualSimulatorConfig)resource.getContents().get(0);
			
			// set the Animator to each user defined evaluator
			UserOperatorEval userOperatorEval = 
					(UserOperatorEval)evaluationManager.getHandler(UserOperatorImpl.class);
			ExtensionManager extensionManager = 
					(ExtensionManager)userOperatorEval.getArbitraryOperatorEvaluator();
			for(IEvaluator eval : extensionManager.getEvaluators())
			{
				((AbstractFunction)eval).setAnimator(animator);
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
    			// init HLPNG simualtor
    			VisualSimulator simulator = new VisualSimulator(petrinet, evaluationManager, 
    					comparisonManager, reversibleOperationManager,
    					Display.getCurrent().getSystemFont(), animator, 
    					config.getGeometry().getGlobalAppearancePath(), 
    					config.getGeometry(), config.getShapes(), extensionManager,
    					factory);
    			
    			// creates simulation view controller
    			ISimulationViewController controller = new SimulationViewController();
    			controller.setSimulator(simulator);
    			simulator.setSimulationViewController(controller);
    			
    			// registers the simulator
    			Activator activator = Activator.getInstance();
    			ApplicationRegistry registry = activator.getApplicationRegistry();
    			registry.addApplication(simulator);
			}
		}
		else
		{
			System.err.println("Configuration file is empty!");
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
					IWorkbenchPart workbenchPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
					
					pnfile = (IFile) workbenchPart.getSite().getPage().getActiveEditor().getEditorInput().getAdapter(IFile.class);
                    filename = pnfile.getRawLocation().toOSString();
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
	
	private static Animator createAnimator(String path)
	{
		String planeTexture = path + "resources/texture/platform_texture.jpg";
		String collisionMarker = path + "resources/collision/exclamation.obj";
		String earthTexture = path + "resources/texture/platform_texture.jpg";
		String[] skyboxTextures = {
				path + "resources/Skybox/Front.png",
				path + "resources/Skybox/Left.png",
				path + "resources/Skybox/Back.png",
				path + "resources/Skybox/Right.png",
				path + "resources/Skybox/Top.png",
				path + "resources/Skybox/Bottom.png",
		};
		
		return new Animator(600, 600, planeTexture, collisionMarker, earthTexture, skyboxTextures);
	}
	
	public String fileChooser(Shell shell, String path)
	{
		FileDialog fd = new FileDialog(shell, SWT.OPEN);
		fd.setText("Open");
		fd.setFilterPath(path);
		String[] filterExt = { "*." + configExtension };
		fd.setFilterExtensions(filterExt);
		return fd.open();
	}
}
