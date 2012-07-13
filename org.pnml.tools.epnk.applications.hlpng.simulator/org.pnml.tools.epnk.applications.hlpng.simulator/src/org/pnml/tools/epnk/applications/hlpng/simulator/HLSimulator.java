package org.pnml.tools.epnk.applications.hlpng.simulator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.pnml.tools.epnk.annotations.manager.IPresentationManager;
import org.pnml.tools.epnk.annotations.netannotations.NetAnnotations;
import org.pnml.tools.epnk.applications.Application;
import org.pnml.tools.epnk.applications.hlpng.jobs.IWorker;
import org.pnml.tools.epnk.applications.hlpng.jobs.PeriodicalWorkerJob;
import org.pnml.tools.epnk.applications.hlpng.presentation.SimulatorPresentationManager;
import org.pnml.tools.epnk.applications.hlpng.presentation.marking.NetMarking;
import org.pnml.tools.epnk.applications.hlpng.presentation.marking.NetMarkingManager;
import org.pnml.tools.epnk.applications.hlpng.resources.ResourceManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeStateContainer;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.RuntimeStateList;
import org.pnml.tools.epnk.applications.hlpng.runtimeStates.RuntimeStateManager;
import org.pnml.tools.epnk.applications.hlpng.simulator.views.ISimulationViewController;
import org.pnml.tools.epnk.applications.hlpng.simulator.views.SimulationViewController;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IFiringStrategy;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TransitionManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.reversible.ReversibleOperationManager;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class HLSimulator extends Application implements ISimulator, IWorker
{
	protected IPresentationManager presentationManager = null;
	protected NetMarkingManager netMarkingManager = null;
	
	protected Font font = null;
	protected FlatAccess flatAccess = null;
	protected EvaluationManager evaluationManager = null; 
	protected ComparisonManager comparisonManager = null; 
	protected ReversibleOperationManager reversibleOperationManager = null;
	
	protected TransitionManager transitionManager = null;
	protected RuntimeStateManager transitionFiringManager = null;
	protected RuntimeValueFactory runtimeValueFactory = null;
	
	protected PeriodicalWorkerJob autoMode = null;
	protected ISimulationViewController simulationViewController = null;
	
	protected long simulationPause = 500;
	protected boolean autoModeEnabled;
	
	protected IFiringStrategy firingStrategy = null;
	
	protected IRuntimeStateContainer stateContainer = null; 
	
	private Action[] actions;
	
	protected final Display display;
	
	public HLSimulator(PetriNet petrinet, EvaluationManager evaluationManager, 
			ComparisonManager comparisonManager, 
			ReversibleOperationManager reversibleOperationManager, Font font,
			RuntimeValueFactory runtimeValueFactory, 
			IFiringStrategy firingStrategy, boolean init, Display display)
    {
	    super(petrinet);
	    
	    this.display = display;
	    this.evaluationManager = evaluationManager;
	    this.comparisonManager = comparisonManager;
	    this.reversibleOperationManager = reversibleOperationManager;
	    this.runtimeValueFactory = runtimeValueFactory;
	    this.firingStrategy = firingStrategy;
	    
	    this.font = font;
		
	    this.simulationViewController = new SimulationViewController(this);
	    this.flatAccess = new FlatAccess(this.petrinet);
	    
	    if(init)
	    {
	    	init();	
	    }
    }
	
	@Override
	public void init()
	{
		this.stateContainer = new RuntimeStateList();
	    this.transitionFiringManager = new RuntimeStateManager(this.flatAccess,
	    		this.runtimeValueFactory);
	    this.autoMode = new PeriodicalWorkerJob(Display.getDefault(), 
	    		"Auto transition firing", this);
	    this.transitionManager = new TransitionManager(flatAccess, comparisonManager,
				evaluationManager, reversibleOperationManager, display);
	    
		this.presentationManager = new SimulatorPresentationManager(this, font);
		this.netMarkingManager= new NetMarkingManager(this.petrinet,  
				comparisonManager, reversibleOperationManager);
		
		// computing initial state
		IRuntimeState initialState = this.transitionFiringManager.createInitialState(this.evaluationManager,
                this.flatAccess.getTransitions(), this.transitionManager);
		this.stateContainer.add(initialState);

		// creating an annotation layer
		showAnnotations(initialState, netMarkingManager, this.getNetAnnotations());
		
		updateActionEnabledness();
	}
	
	@Override
    public void fire(FiringMode mode, boolean updateAnnotations)
    {
		// setting the selected firing mode for the state
		stateContainer.getCurrent().setFiringMode(mode);
		// recording
		this.simulationViewController.record(stateContainer.getCurrent());
		// computing the next state
		IRuntimeState runtimeState = this.transitionFiringManager.
				createNextState(stateContainer.getCurrent(), evaluationManager, mode, transitionManager);
		boolean needToClean = stateContainer.add(runtimeState);
		if(needToClean)
		{
			this.simulationViewController.resetRecords(stateContainer);
		}
		
		// creating an annotation layer
		if(updateAnnotations)
		{
			showAnnotations(runtimeState, netMarkingManager, this.getNetAnnotations());	
		}
    }
	
	@Override
    public void updateTransitionBinding(IRuntimeState state)
    {
		// updates state transition binding
		this.transitionFiringManager.updateState(state, transitionManager);
    }
	
	@Override
	public void reset()
	{
		// stops the simulation if any
		stop();
		// clears the view
		simulationViewController.clear();
		// initializes the application
		init();
	}
	
	@Override
    public void show(IRuntimeState state)
    {
		if(!autoModeEnabled)
		{
			stateContainer.setCurrent(state);
			// creating an annotation layer
			showAnnotations(state, netMarkingManager, this.getNetAnnotations());	
		}
    }

	@Override
    public void next()
    {
		IRuntimeState state = stateContainer.next();
		if(state != null)
		{
			// creating an annotation layer
			showAnnotations(state, netMarkingManager, this.getNetAnnotations());
		}
    }
	
	@Override
    public void previous()
    {
		IRuntimeState state = stateContainer.previous();
		if(state != null)
		{
			// creating an annotation layer
			showAnnotations(state, netMarkingManager, this.getNetAnnotations());
		}
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
		stop();
		simulationViewController.clear();
	}

	@Override
    public void work()
    {
		FiringMode mode = firingStrategy.fire(stateContainer.getCurrent().getModes(),
				stateContainer.getCurrent());
		boolean updateAnnotations = !autoModeEnabled || simulationPause > 0;
		
		if(mode != null)
		{
			this.fire(mode, updateAnnotations);	
		}
		else
		{
			stop();
			if(!updateAnnotations)
			{
				showAnnotations(stateContainer.getCurrent(), netMarkingManager, 
						this.getNetAnnotations());
			}
		}
    }

	@Override
	public boolean showPopUpMenu()
    {
    	return !autoModeEnabled;
    }

	public void setAutoModeEnabled(boolean autoModeEnabled)
    {
    	this.autoModeEnabled = autoModeEnabled;
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
			{
				ImageDescriptor desc = ResourceManager.getImageDescriptor("icons/back.png",
						ResourceManager.SIMULATOR_PLUGIN_ID);
				if(desc != null)
				{
					actions[0].setImageDescriptor(desc);	
				}				
			}			
			actions[1] = new Action() {
				public void run() {
					auto();
				}
			};
			actions[1].setId("auto");
			actions[1].setText("Run");
			actions[1].setToolTipText("Automatic mode");
			{
				ImageDescriptor desc = ResourceManager.getImageDescriptor("icons/play.png",
						ResourceManager.SIMULATOR_PLUGIN_ID);
				if(desc != null)
				{
					actions[1].setImageDescriptor(desc);	
				}
			}
			
			actions[2] = new Action() {
				public void run() {
					stop();
				}
			};
			actions[2].setId("stop");
			actions[2].setText("Stop");
			actions[2].setToolTipText("Stop current execution");
			{
				ImageDescriptor desc = ResourceManager.getImageDescriptor("icons/stop.png",
						ResourceManager.SIMULATOR_PLUGIN_ID);
				if(desc != null)
				{
					actions[2].setImageDescriptor(desc);	
				}
			}			
			
			actions[3] = new Action() {
				public void run() {
					next();
				}
			};
			actions[3].setId("next");
			actions[3].setText("Next");
			actions[3].setToolTipText("Show next");
			{
				ImageDescriptor desc = ResourceManager.getImageDescriptor("icons/next.png",
						ResourceManager.SIMULATOR_PLUGIN_ID);
				if(desc != null)
				{
					actions[3].setImageDescriptor(desc);	
				}
			}
			
			actions[4] = new Action() {
				public void run() {
					reset();
				}
			};
			actions[4].setId("reset");
			actions[4].setText("Reset");
			actions[4].setToolTipText("Show reset");
			{
				ImageDescriptor desc = ResourceManager.getImageDescriptor("icons/reset.png",
						ResourceManager.SIMULATOR_PLUGIN_ID);
				if(desc != null)
				{
					actions[4].setImageDescriptor(desc);	
				}
			}
	    }
	    return actions;
    }
	
	protected void showAnnotations(IRuntimeState state, NetMarkingManager netManager,
			NetAnnotations netAnnotations)
	{
		NetMarking netMarking = netManager.createNetMarking(state);
		netAnnotations.setCurrent(netMarking);
	}

	@Override
    public void activate()
    {
	    this.simulationViewController.setCurrent();
    }

	@Override
	public long getSimulationPause()
    {
    	return simulationPause;
    }

	@Override
	public void setSimulationPause(long simulationPause)
    {
    	this.simulationPause = simulationPause;
    }
}
