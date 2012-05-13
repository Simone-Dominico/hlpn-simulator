package org.pnml.tools.epnk.applications.hlpng.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import networkmodel.DirectedEdge;
import networkmodel.Network;
import networkmodel.NetworkObject;
import networkmodel.NetworkmodelPackage;
import networkmodel.Node;
import networkmodel.UndirectedEdge;

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
import org.pnml.tools.epnk.applications.hlpng.network.InputFunction;
import org.pnml.tools.epnk.applications.hlpng.network.consensus.MFunction;
import org.pnml.tools.epnk.applications.hlpng.network.consensus.RBFunction;
import org.pnml.tools.epnk.applications.hlpng.network.consensus.RFFunction;
import org.pnml.tools.epnk.applications.hlpng.network.echo.M1Function;
import org.pnml.tools.epnk.applications.hlpng.network.echo.M2Function;
import org.pnml.tools.epnk.applications.hlpng.network.mindist.NFunction;
import org.pnml.tools.epnk.applications.hlpng.resources.ResourceManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UserOperatorEval;
import org.pnml.tools.epnk.applications.registry.ApplicationRegistry;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.UserOperatorImpl;

public class StartSimulatorApp implements IObjectActionDelegate
{
	private PetriNet petrinet;
    private String filename = null;
    private IFile pnfile = null;
    private static final String extension = "networkmodel";
    
	@Override
	public void run(IAction action)
	{
		ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
        NetworkmodelPackage networkmodelPackage = NetworkmodelPackage.eINSTANCE;
        
        String configFilePath = filename.replaceFirst(pnfile.getFileExtension(), extension);
        
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
        catch(Exception e){e.printStackTrace();}

		if(resource != null && resource.getContents().size() > 0)
		{
			// init the evaluation manager
			EvaluationManager evaluationManager = ResourceManager.createEvaluationManager(null);
			
			// init extension manager
			ExtensionManager extensionManager = 
					createExtensionManager((Network)resource.getContents().get(0));
			UserOperatorEval userOperatorEval = 
					(UserOperatorEval)evaluationManager.getHandler(UserOperatorImpl.class);
			userOperatorEval.setArbitraryOperatorEvaluator(extensionManager);
			
			// init the reversible operation manager
			ReversibleOperationManager reversibleOperationManager = 
					ResourceManager.createReversibleOperationManager(evaluationManager);
					
			// init the comparison manager
			ComparisonManager comparisonManager = 
					ResourceManager.createComparisonManager(evaluationManager, reversibleOperationManager);
					
			// init HLPNG simualtor
			NetworkSimulator simulator = new NetworkSimulator(petrinet, evaluationManager, 
					comparisonManager, reversibleOperationManager,
					Display.getCurrent().getSystemFont());

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
	
	private static ExtensionManager createExtensionManager(Network network)
	{
		Map<String, NodeWrapper> nodeNameMap = new HashMap<String, NodeWrapper>();
		Map<Integer, NodeWrapper> nodeIdMap = new HashMap<Integer, NodeWrapper>();
		List<NodeWrapper> nodes = new ArrayList<NodeWrapper>();
		int currentId = 0;
		for(NetworkObject nobj : network.getNetwork())
		{
			if(nobj instanceof Node)
			{
				NodeWrapper wrapper = new NodeWrapper(currentId, (Node)nobj);
				currentId++;
				nodes.add(wrapper);
				nodeNameMap.put(((Node)nobj).getLabel(), wrapper);
				nodeIdMap.put(wrapper.getId(), wrapper);
			}
		}
		
		Integer[][] graph = new Integer[nodes.size()][nodes.size()];

		for(NodeWrapper nw : nodes)
		{
			for(UndirectedEdge edge : nw.getNode().getOut())
			{
				graph[nw.getId()][nodeNameMap.get(edge.getTarget().getLabel()).getId()] = 1;
				if(!(edge instanceof DirectedEdge))
				{
					graph[nodeNameMap.get(edge.getTarget().getLabel()).getId()][nw.getId()] = 1;
				}
			}
		}

		ExtensionManager extensionManager = new ExtensionManager(new InputFunction(network.getCategories()));
		{
			// min dist
			extensionManager.register("N", new NFunction(graph, nodeNameMap, nodeIdMap));
			
			// consensus in networks
			MFunction mFunction = new MFunction(nodes);
			extensionManager.register("M", mFunction);
			extensionManager.register("RF", new RFFunction(mFunction.getMessages()));
			extensionManager.register("RB", new RBFunction(mFunction.getMessages()));

			// echo
			extensionManager.register("M1", new M1Function(graph, nodeNameMap, nodeIdMap));
			extensionManager.register("M2", new M2Function(graph, nodeNameMap, nodeIdMap));
		}
		return extensionManager;
	}
	

	private static String fileChooser(Shell shell, String path)
	{
		FileDialog fd = new FileDialog(shell, SWT.OPEN);
		fd.setText("Open");
		fd.setFilterPath(path);
		String[] filterExt = { "*." + extension };
		fd.setFilterExtensions(filterExt);
		return fd.open();
	}
}
