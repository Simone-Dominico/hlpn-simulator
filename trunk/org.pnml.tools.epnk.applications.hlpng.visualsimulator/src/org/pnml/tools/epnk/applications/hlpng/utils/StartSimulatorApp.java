package org.pnml.tools.epnk.applications.hlpng.utils;

import geditor.GObject;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.pnml.tools.epnk.applications.activator.Activator;
import org.pnml.tools.epnk.applications.hlpng.contributors.ExtensionManager;
import org.pnml.tools.epnk.applications.hlpng.functions.APPEAR;
import org.pnml.tools.epnk.applications.hlpng.functions.APPEAR_POINT;
import org.pnml.tools.epnk.applications.hlpng.functions.MOVE;
import org.pnml.tools.epnk.applications.hlpng.functions.READY;
import org.pnml.tools.epnk.applications.hlpng.functions.TRIGGER;
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

import Appearence.Shape;

import visualsimulationconfig.VisualSimulatorConfig;

public class StartSimulatorApp implements IObjectActionDelegate
{
	private PetriNet petrinet;

	@Override
	public void run(IAction action)
	{
		ResourceSet resourceSet = new ResourceSetImpl();
		URI fileUri = URI
				//.createFileURI("/home/mindaugas/Dropbox/DTU/master-project/runtime-simulator/3DTrains/SimpleTrainTraffic.visualsimulationconfig");
				.createFileURI("/home/mindaugas/Dropbox/DTU/master-project/runtime-simulator/super_example/models/TrainTraffic.visualsimulationconfig");
		Resource resource = resourceSet.getResource(fileUri, true);

		if(resource.getContents().size() > 0)
		{
			// init the evaluation manager
			EvaluationManager evaluationManager = createEvaluationManager();
			
			// init the reversible operation manager
			ReversibleOperationManager reversibleOperationManager = createReversibleOperationManager(evaluationManager);
					
			// init the comparison manager
			ComparisonManager comparisonManager = createComparisonManager(evaluationManager, reversibleOperationManager);
			
			// creates a simulator
			VisualSimulator application = new VisualSimulator();
			
			// init animator
			Animator animator = createAnimator();
			animator.setSimulator(application);
			application.setAnimator(animator);
						
			// init config
			VisualSimulatorConfig config = (VisualSimulatorConfig)resource.getContents().get(0);
			
			// init extension manager
			evaluationManager.register(UserOperatorImpl.class, 
					createExtensionManager(config, animator, application));
					
			// init HLPNG simualtor
			HLSimulator hlSimulator = new HLSimulator(petrinet, evaluationManager, 
					comparisonManager, reversibleOperationManager,
					Display.getDefault().getSystemFont(), false);
			application.setSimulator(hlSimulator);
			
//			 registers the simulator
			Activator activator = Activator.getInstance();
			ApplicationRegistry registry = activator.getApplicationRegistry();
			registry.addApplication(application);
			
			// make animator visible
			animator.setVisible(true);
			animator.initRequested();
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
	
	private static ExtensionManager createExtensionManager(VisualSimulatorConfig config, 
			Animator animator, VisualSimulator simulator)
	{
		String globalAppearencepath = config.getGeometry().getGlobalAppearancePath();

		Map<String, GObject> geometryMap = new HashMap<String, GObject>();
		{
			for(GObject g : config.getGeometry().getGeometryObjects())
			{
				if(g.getId() != null)
				{
					geometryMap.put(g.getId(), g);
					try
					{
						int id = animator.createStaticItem(g, null, globalAppearencepath);
						simulator.registerStaticItem(g.getId(), id);
					}
					catch(Exception e)
					{
						System.err.println("WRN: failed to create static item: " + e);
					}
				}
			}
		}
		
		Map<String, Shape> shapeMap = new HashMap<String, Shape>();
		{
			for(Shape s : config.getShapes().getAppearence())
			{
				shapeMap.put(s.getId(), s);
				int id = animator.createModel(s, false);
				simulator.registerModel(s.getId(), id);
			}
		}
		ExtensionManager extensionManager = new ExtensionManager();
		{
			extensionManager.register("APPEAR", 
					new APPEAR(geometryMap, shapeMap, animator, simulator));
			extensionManager.register("APPEAR_POINT", 
					new APPEAR_POINT(geometryMap, shapeMap, animator, simulator));
			extensionManager.register("MOVE", 
					new MOVE(geometryMap, shapeMap, animator, simulator));
			extensionManager.register("READY", 
					new READY(geometryMap, shapeMap, animator, simulator));
			extensionManager.register("TRIGGER", 
					new TRIGGER(geometryMap, shapeMap, animator, simulator));
		}
		return extensionManager;
	}
	
	private static Animator createAnimator()
	{
		String planeTexture = toAbsolutePath("resources/texture/platform_texture.jpg");
		String collisionMarker = toAbsolutePath("resources/collision/exclamation.obj");
		String earthTexture = toAbsolutePath("resources/texture/platform_texture.jpg");
		String[] skyboxTextures = {
				toAbsolutePath("resources/Skybox/Front.png"),
				toAbsolutePath("resources/Skybox/Left.png"),
				toAbsolutePath("resources/Skybox/Back.png"),
				toAbsolutePath("resources/Skybox/Right.png"),
				toAbsolutePath("resources/Skybox/Top.png"),
				toAbsolutePath("resources/Skybox/Bottom.png"),
		};
		
		return new Animator(600, 600, planeTexture, collisionMarker, earthTexture, skyboxTextures);
	}
	
	public static String toAbsolutePath(String relativePath)
	{
		return "/home/mindaugas/Dropbox/DTU/master-project/workspace/org.pnml.tools.epnk.applications.hlpng.visualsimulator/" +
				relativePath;
		// final URL fileURL =
		// Activator.getDefault().getBundle().getEntry(relativePath);
		//
		// URL fullPath = null;
		// try {
		// fullPath = FileLocator.resolve(fileURL);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// return fullPath.getPath();
	}
}
