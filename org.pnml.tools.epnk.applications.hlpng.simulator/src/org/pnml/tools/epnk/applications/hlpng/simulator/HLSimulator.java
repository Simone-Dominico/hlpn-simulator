package org.pnml.tools.epnk.applications.hlpng.simulator;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Font;
import org.pnml.tools.epnk.annotations.manager.IPresentationManager;
import org.pnml.tools.epnk.annotations.netannotations.NetAnnotations;
import org.pnml.tools.epnk.applications.Application;
import org.pnml.tools.epnk.applications.IApplicationWithPresentation;
import org.pnml.tools.epnk.applications.hlpng.presentation.SimulatorPresentationManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.NetMarking;
import org.pnml.tools.epnk.applications.hlpng.selection.AbstractMenuItem;
import org.pnml.tools.epnk.applications.hlpng.selection.PopupMenuItem;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

public class HLSimulator extends Application 
	implements IApplicationWithPresentation, ISimulator
{
	protected IPresentationManager presentationManager = null;
	protected NetMarkingManager netMarkingManager = null;
	
	protected Font font = null;
	protected FlatAccess flatAccess = null;
	protected EvaluationManager evaluationManager = null; 
	protected ComparisonManager comparisonManager = null; 
	protected ReversibleOperationManager reversibleOperationManager = null;
	
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
	    this.flatAccess = new FlatAccess(this.petrinet);
		
	    if(init)
	    {
	    	init();	
	    }
    }
	
	@Override
	public void init()
	{
		this.presentationManager = new SimulatorPresentationManager(this, font);
		this.netMarkingManager= new NetMarkingManager(this.petrinet, flatAccess, 
				evaluationManager, comparisonManager, reversibleOperationManager);
		
		NetMarking netMarking = netMarkingManager.createNetMarking();
		NetAnnotations netAnnotations = getNetAnnotations();
		netAnnotations.getNetAnnotations().add(netMarking);
		netAnnotations.setCurrent(netMarking);
		
		updateActionEnabledness();
	}
	
	@Override
    public void fire(Transition transition, AbstractMenuItem abstractAction)
    {
		if(abstractAction instanceof PopupMenuItem)
		{
			PopupMenuItem action = (PopupMenuItem) abstractAction;
			
			FiringMode mode = action.getMode();

			NetMarking prevMarking = (NetMarking)this.getNetAnnotations().getCurrent();
			
			NetMarking netMarking = this.netMarkingManager.createNetMarking(prevMarking, mode);
			
			NetAnnotations netAnnotations = this.getNetAnnotations();
			netAnnotations.getNetAnnotations().add(netMarking);
			netAnnotations.setCurrent(netMarking);
		}
    }
	
	@Override
    public void checkTransitions()
    {
		NetMarking prevMarking = (NetMarking)this.getNetAnnotations().getCurrent();
		
		NetMarking netMarking = this.netMarkingManager.createNetMarking(prevMarking);
		
		NetAnnotations netAnnotations = this.getNetAnnotations();
		netAnnotations.getNetAnnotations().add(netMarking);
		netAnnotations.setCurrent(netMarking);
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
	    	actions = new Action[4];
			
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
	    }
	    return actions;
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
	public void auto(){}
	
	@Override
	public void stop(){}

}
