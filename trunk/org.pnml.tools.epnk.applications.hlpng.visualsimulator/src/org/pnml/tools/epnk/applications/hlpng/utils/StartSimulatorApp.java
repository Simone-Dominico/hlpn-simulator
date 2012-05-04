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
			EvaluationManager evaluationManager = createEvaluationManager();
			
			// init the reversible operation manager
			ReversibleOperationManager reversibleOperationManager = 
					createReversibleOperationManager(evaluationManager);
					
			// init the comparison manager
			ComparisonManager comparisonManager = 
					createComparisonManager(evaluationManager, reversibleOperationManager);
			
			// init animator
			Animator animator = createAnimator(filename.replaceFirst(pnfile.getName(), ""));
			
			// init config
			VisualSimulatorConfig config = (VisualSimulatorConfig)resource.getContents().get(0);
			
			// init extension manager
			ExtensionManager extensionManager = createExtensionManager(animator);
			evaluationManager.register(UserOperatorImpl.class, extensionManager);
					
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

	private static EvaluationManager createEvaluationManager()
	{
		EvaluationManager evaluationManager = new EvaluationManager();
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
		
		return evaluationManager;
	}
	
	private static ReversibleOperationManager createReversibleOperationManager(EvaluationManager evaluationManager)
	{
		ReversibleOperationManager reversibleOperationManager = new ReversibleOperationManager(evaluationManager);
		
		reversibleOperationManager.register(AdditionImpl.class, new AdditionEval());
		reversibleOperationManager.register(MultiplicationImpl.class, new MultiplicationEval());
		
		return reversibleOperationManager;
	}
	
	private static ComparisonManager createComparisonManager(EvaluationManager evaluationManager,
			ReversibleOperationManager reversibleOperationManager)
	{
		ComparisonManager comparisonManager = new ComparisonManager();
		
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
		
		return comparisonManager;
	}
	
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
