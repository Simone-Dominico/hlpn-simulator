package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.pnml.tools.epnk.annotations.manager.IPresentationManager;
import org.pnml.tools.epnk.annotations.netannotations.NetAnnotations;
import org.pnml.tools.epnk.applications.Application;
import org.pnml.tools.epnk.applications.IApplicationWithPresentation;
import org.pnml.tools.epnk.applications.hlpng.presentation.SimulatorPresentationManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NetMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.TransitionMarking;
import org.pnml.tools.epnk.applications.hlpng.simulator.views.SimulationViewController;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TransitionManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.utils.Pair;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;

public class HLSimulator extends Application 
	implements IApplicationWithPresentation, ISimulator, IWorker
{
	protected IPresentationManager presentationManager = null;
	protected NetMarkingManager netMarkingManager = null;
	
	protected Font font = null;
	protected FlatAccess flatAccess = null;
	protected EvaluationManager evaluationManager = null; 
	protected ComparisonManager comparisonManager = null; 
	protected ReversibleOperationManager reversibleOperationManager = null;
	
	protected TransitionManager transitionManager = null;
	protected TransitionFiringManager transitionFiringManager = null;
	
	protected AutoModeJob autoMode = null;
	protected SimulationViewController simulationViewController = null;
	
	protected long simulationPause = 500;
	protected boolean autoModeEnabled;
	
	protected List<FiringMode> initialFiringModes = null;
	private IFiringStrategy firingStrategy = new RandomFiringStrategy();
	
	private Action[] actions;
	
	public HLSimulator(PetriNet petrinet, EvaluationManager evaluationManager, 
			ComparisonManager comparisonManager, 
			ReversibleOperationManager reversibleOperationManager, Font font,
			boolean init)
    {
	    super(petrinet);
	    
	    this.evaluationManager = evaluationManager;
	    this.comparisonManager = comparisonManager;
	    this.reversibleOperationManager = reversibleOperationManager;
	    
	    this.font = font;
		
	    if(init)
	    {
	    	init();	
	    }
    }
	
	@Override
	public void init()
	{
		this.simulationViewController = new SimulationViewController();
		this.flatAccess = new FlatAccess(this.petrinet);
	    this.transitionFiringManager = new TransitionFiringManager(this.flatAccess);
	    this.autoMode = new AutoModeJob(Display.getDefault(), 
	    		"Auto transition firing", this, this.simulationPause);
	    this.transitionManager = new TransitionManager(flatAccess, comparisonManager,
				evaluationManager, reversibleOperationManager);
	    
		this.presentationManager = new SimulatorPresentationManager(this, font);
		this.netMarkingManager= new NetMarkingManager(this.petrinet,  
				comparisonManager, reversibleOperationManager);
		
		List<Pair<Place, MSValue>> runtimeValueList = 
				this.transitionFiringManager.createInitialMarking(this.evaluationManager);
		Map<String, MSValue> runtimeValuesMap = this.transitionFiringManager.createRuntimeValueMap(runtimeValueList);
		this.initialFiringModes = this.transitionFiringManager.computeFiringModes(
				this.flatAccess.getTransitions(), runtimeValuesMap, this.transitionManager);
		
		NetMarking netMarking = netMarkingManager.createNetMarking(runtimeValueList, this.initialFiringModes);
		NetAnnotations netAnnotations = getNetAnnotations();
		netAnnotations.getNetAnnotations().add(netMarking);
		netAnnotations.setCurrent(netMarking);
		
		updateActionEnabledness();
	}
	
	@Override
    public List<FiringMode> fire(FiringMode mode)
    {
		NetMarking prevMarking = (NetMarking)this.getNetAnnotations().getCurrent();
		
		// marking as fired
		for(AbstractMarking m : prevMarking.getMarkings())
		{
			if(m instanceof TransitionMarking && 
					((TransitionMarking)m).getTransition().getId().equals(mode.getTransition().getId()))
			{
				((TransitionMarking)m).setFired(true);
			}
		}
		
		// recording
		this.simulationViewController.record(mode);
		
		// computing the following modes
		List<Pair<Place, MSValue>> currentRuntimeValueList = 
				this.transitionFiringManager.copyPrevPlaceMarking(prevMarking);
		Map<String, MSValue> currentValuesMap = this.transitionFiringManager.
				createRuntimeValueMap(currentRuntimeValueList);
		
		Pair<List<Pair<Place, MSValue>>, Map<String, MSValue>> result =
				this.transitionFiringManager.createNextMarking(evaluationManager, currentValuesMap, mode);
		
		List<FiringMode> firingModes =
				this.transitionFiringManager.computeFiringModes(this.flatAccess.getTransitions(), 
						result.getValue(), this.transitionManager);
		
		// creating next annotation layer
		NetMarking netMarking = this.netMarkingManager.
				createNetMarking(result.getKey(), firingModes);
		NetAnnotations netAnnotations = this.getNetAnnotations();
		netAnnotations.getNetAnnotations().add(netMarking);
		netAnnotations.setCurrent(netMarking);
		
		return firingModes;
    }
	
	@Override
    public List<FiringMode> updateTransitionMarking()
    {
		NetMarking prevMarking = (NetMarking)this.getNetAnnotations().getCurrent();
		
		List<Pair<Place, MSValue>> runtimeValuesList = this.transitionFiringManager.copyPrevPlaceMarking(prevMarking);
		Map<String, MSValue> runtimeValuesMap = this.transitionFiringManager.createRuntimeValueMap(runtimeValuesList);
		List<FiringMode> firingModes =
				this.transitionFiringManager.computeFiringModes(this.flatAccess.getTransitions(), runtimeValuesMap, this.transitionManager);
		
		NetMarking netMarking = netMarkingManager.createNetMarking(runtimeValuesList, firingModes);
		
		NetAnnotations netAnnotations = this.getNetAnnotations();
		netAnnotations.getNetAnnotations().add(netMarking);
		netAnnotations.setCurrent(netMarking);
		
		return firingModes;
    }
	
	public IPresentationManager getPresentationManager()
    {
    	return presentationManager;
    }

	public void setPresentationManager(IPresentationManager presentationManager)
    {
    	this.presentationManager = presentationManager;
    }

	@Override
    public Action[] getActions()
    {
		if(actions == null)
		{
	    	actions = new Action[5];
			
			actions[0] = new Action() {
				public void run() {
					previous();
				}
			};
			actions[0].setId("previous");
			actions[0].setText("Previous");
			actions[0].setToolTipText("Show previous");
//			actions[0].setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor("/home/mindaugas/Dropbox/DTU/master-project/workspace/org.pnml.tools.epnk.applications.hlpng.simulator/resources/back.png"));//ISharedImages.IMG_TOOL_BACK));
			
			actions[1] = new Action() {
				public void run() {
					auto();
				}
			};
			actions[1].setId("auto");
			actions[1].setText("Run");
			actions[1].setToolTipText("Automatic mode");
//			actions[1].setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(ISharedImages.IMG_TOOL_UP));
			
			actions[2] = new Action() {
				public void run() {
					stop();
				}
			};
			actions[2].setId("stop");
			actions[2].setText("Stop");
			actions[2].setToolTipText("Stop current execution");
//			actions[2].setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));
			
			
			actions[3] = new Action() {
				public void run() {
					next();
				}
			};
			actions[3].setId("next");
			actions[3].setText("Next");
			actions[3].setToolTipText("Show next");
//			actions[3].setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));
			
			actions[4] = new Action() {
				public void run() {
					reset();
				}
			};
			actions[4].setId("reset");
			actions[4].setText("Reset");
			actions[4].setToolTipText("Show reset");
//			actions[4].setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));
	    }
	    return actions;
    }

	@Override
	public void reset()
	{
		autoMode.setStopped(true);
		init();
	}
	
	@Override
    public void next()
    {
		super.nextAnnotation();
    }

	@Override
    public void previous()
    {
		super.previousAnnotation();
    }
	
	@Override
	public void auto()
	{
		if(autoMode.isStopped())
		{
			autoModeEnabled = true;
			autoMode.setStopped(false);
			autoMode.runInUIThread(null);
		}
	}
	
	@Override
	public void stop()
	{
		autoMode.setStopped(true);
		autoModeEnabled = false;
	}
	
	@Override
	protected void shutDown() 
	{
		autoMode.setStopped(true);
	}

	@Override
    public void work()
    {
		NetMarking currentMarking = (NetMarking)this.getNetAnnotations().getCurrent();
		List<Pair<Place, MSValue>> runtimeValuesList = this.transitionFiringManager.copyPrevPlaceMarking(currentMarking);
		Map<String, MSValue> runtimeValuesMap = this.transitionFiringManager.createRuntimeValueMap(runtimeValuesList);
		List<FiringMode> firingModes =
				this.transitionFiringManager.computeFiringModes(this.flatAccess.getTransitions(), runtimeValuesMap, this.transitionManager);

		FiringMode mode = firingStrategy.fire(firingModes);
		if(mode != null)
		{
			this.fire(mode);	
		}
		else
		{
			autoMode.setStopped(true);
			/*MessageBox messageBox = new MessageBox(new Shell(Display.getCurrent()), SWT.ICON_INFORMATION);
	        messageBox.setText("Information");
	        messageBox.setMessage("There are no more enabled transitions anymore.\n" +
	        		"Turning auto mode off.");
	        messageBox.open();*/
		}
    }

	public boolean isAutoModeEnabled()
    {
    	return autoModeEnabled;
    }

	public void setAutoModeEnabled(boolean autoModeEnabled)
    {
    	this.autoModeEnabled = autoModeEnabled;
    }
}
