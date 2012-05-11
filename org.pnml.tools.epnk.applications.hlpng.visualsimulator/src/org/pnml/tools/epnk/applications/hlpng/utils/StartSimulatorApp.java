package org.pnml.tools.epnk.applications.hlpng.utils;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.jface.action.IAction;
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
import org.pnml.tools.epnk.applications.hlpng.functions.APPEAR;
import org.pnml.tools.epnk.applications.hlpng.functions.APPEAR_POINT;
import org.pnml.tools.epnk.applications.hlpng.functions.MOVE;
import org.pnml.tools.epnk.applications.hlpng.functions.READY;
import org.pnml.tools.epnk.applications.hlpng.functions.TRIGGER;
import org.pnml.tools.epnk.applications.hlpng.resources.ResourceManager;
import org.pnml.tools.epnk.applications.hlpng.simulator.VisualSimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UserOperatorEval;
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
			// init the evaluation manager
			EvaluationManager evaluationManager = ResourceManager.createEvaluationManager(null);
			
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
			
			// init extension manager
			ExtensionManager extensionManager = createExtensionManager(animator);
			UserOperatorEval userOperatorEval = 
					(UserOperatorEval)evaluationManager.getHandler(UserOperatorImpl.class);
			userOperatorEval.setArbitraryOperatorEvaluator(extensionManager);
					
			// init HLPNG simualtor
			VisualSimulator simulator = new VisualSimulator(petrinet, evaluationManager, 
					comparisonManager, reversibleOperationManager,
					Display.getCurrent().getSystemFont(), animator, 
					config.getGeometry().getGlobalAppearancePath(), 
					config.getGeometry(), config.getShapes(), extensionManager);

//			 registers the simulator
			Activator activator = Activator.getInstance();
			ApplicationRegistry registry = activator.getApplicationRegistry();
			registry.addApplication(simulator);
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

	private static ExtensionManager createExtensionManager(Animator animator)
	{
		ExtensionManager extensionManager = new ExtensionManager();
		{
			extensionManager.register("APPEAR", 
					new APPEAR(animator));
			extensionManager.register("APPEAR_POINT", 
					new APPEAR_POINT(animator));
			extensionManager.register("MOVE", 
					new MOVE(animator));
			extensionManager.register("READY", 
					new READY(animator));
			extensionManager.register("TRIGGER", 
					new TRIGGER(animator));
		}
		return extensionManager;
	}
	
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
