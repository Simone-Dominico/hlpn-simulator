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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
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
import org.pnml.tools.epnk.applications.hlpng.contributors.NetworkExtensionManager;
import org.pnml.tools.epnk.applications.hlpng.network.consensus.MFunction;
import org.pnml.tools.epnk.applications.hlpng.network.consensus.RBFunction;
import org.pnml.tools.epnk.applications.hlpng.network.consensus.RFFunction;
import org.pnml.tools.epnk.applications.hlpng.network.echo.M1Function;
import org.pnml.tools.epnk.applications.hlpng.network.echo.M2Function;
import org.pnml.tools.epnk.applications.hlpng.network.mindist.NFunction;
import org.pnml.tools.epnk.applications.hlpng.resources.ResourceManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;
import org.pnml.tools.epnk.applications.hlpng.simulator.views.ISimulationViewController;
import org.pnml.tools.epnk.applications.hlpng.simulator.views.SimulationViewController;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IFiringStrategy;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.RandomFiringStrategy;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UserOperatorEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.reversible.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.validation.ValidationDelegateClientSelector;
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
        catch(Exception e)
        {
        	e.printStackTrace();
        }

        if(resource != null && resource.getContents().size() > 0)
        {
        	// runtime value factory
    		RuntimeValueFactory runtimeValueFactory = new RuntimeValueFactory();
    		
            // init the evaluation manager
            EvaluationManager evaluationManager = ResourceManager.
            		createEvaluationManager(runtimeValueFactory, 
            				"org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions");

            // init extension manager
            List<NodeWrapper> nodes = new ArrayList<NodeWrapper>();
            {
                Network network = (Network)resource.getContents().get(0);
                int currentId = 0;
                for(NetworkObject nobj : network.getNetwork())
                {
                    if(nobj instanceof Node)
                    {
                        NodeWrapper wrapper = new NodeWrapper(currentId, (Node)nobj);
                        currentId++;
                        nodes.add(wrapper);
                    }
                }
            }
                 
            UserOperatorEval userOperatorEval = 
                    (UserOperatorEval)evaluationManager.getHandler(UserOperatorImpl.class);
            NetworkExtensionManager extensionManager = null;
            for(IEvaluator eval : userOperatorEval.getArbitraryOperatorEvaluators())
            {
            	if(eval instanceof NetworkExtensionManager)
            	{
            		extensionManager = (NetworkExtensionManager) eval;
            	}
            }
            updateExtensionManager((Network)resource.getContents().get(0), 
    				nodes, extensionManager);

            // init the reversible operation manager
            ReversibleOperationManager reversibleOperationManager = 
                    ResourceManager.createReversibleOperationManager(evaluationManager);
                    
            // init the comparison manager
            ComparisonManager comparisonManager = 
                    ResourceManager.createComparisonManager(evaluationManager, reversibleOperationManager);
            
            // firing strategy
    		IFiringStrategy strategy = ResourceManager.
    				getFiringStrategy("org.pnml.tools.epnk.applications.hlpng.simulator.firingStrategy");
    		if(strategy == null)
    		{
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
                try
                {
	                // init HLPNG simualtor
	                NetworkSimulator simulator = new NetworkSimulator(petrinet, evaluationManager, 
	                        comparisonManager, reversibleOperationManager,
	                        Display.getCurrent().getSystemFont(), runtimeValueFactory,
	                        extensionManager, strategy);
	                
	                // creates simulation view controller
	                ISimulationViewController controller = new SimulationViewController();
	                controller.setSimulator(simulator);
	                simulator.setSimulationViewController(controller);

	                // registers the simulator
	                Activator activator = Activator.getInstance();
	                ApplicationRegistry registry = activator.getApplicationRegistry();
	                registry.addApplication(simulator);
                }
                catch(Exception e)
                {
                	IStatus s = new Status(Status.ERROR, status.getPlugin(), 
    						"Are you running the Simulator on a network scheme model?\n" +
    						"The Simulator is only applicable on network schemes.");
    				ErrorDialog.openError(null, "Error", "Launching the Simulator failed.\n", s);
                }	
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
    
    private static void updateExtensionManager(Network network, 
    		List<NodeWrapper> nodes, NetworkExtensionManager extensionManager)
    {
        Map<String, NodeWrapper> nodeNameMap = new HashMap<String, NodeWrapper>();
        Map<Integer, NodeWrapper> nodeIdMap = new HashMap<Integer, NodeWrapper>();
        
        for(NodeWrapper nw : nodes)
        {
        	nodeNameMap.put(nw.getNode().getLabel(), nw);
            nodeIdMap.put(nw.getId(), nw);
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

        // update the evaluators managed by ExtensionManager
        {
        	// input function
        	extensionManager.getInputFunction().setCategories(network.getCategories());
        	extensionManager.getInputFunction().setNodes(nodes);
        	
            // min dist
        	NFunction nf = (NFunction) extensionManager.getEvaluator("N");
        	nf.setGraph(graph);
        	nf.setNodeIdMap(nodeIdMap);
        	nf.setNodeMap(nodeNameMap);

            // consensus in networks
            MFunction mFunction = (MFunction) extensionManager.getEvaluator("M");
            mFunction.setNodes(nodes);
            
            RFFunction fFunction = (RFFunction) extensionManager.getEvaluator("RF");
            fFunction.setMessages(mFunction.getMessages());
            RBFunction bFunction = (RBFunction) extensionManager.getEvaluator("RB");
            bFunction.setMessages(mFunction.getMessages());
            
            // echo
            {
            	M1Function mf = (M1Function) extensionManager.getEvaluator("M1");
                mf.setGraph(graph);
                mf.setNodeIdMap(nodeIdMap);
                mf.setNodeMap(nodeNameMap);
            }
            {
            	M2Function mf = (M2Function) extensionManager.getEvaluator("M2");
                mf.setGraph(graph);
                mf.setNodeIdMap(nodeIdMap);
                mf.setNodeMap(nodeNameMap);
            }
        }
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
