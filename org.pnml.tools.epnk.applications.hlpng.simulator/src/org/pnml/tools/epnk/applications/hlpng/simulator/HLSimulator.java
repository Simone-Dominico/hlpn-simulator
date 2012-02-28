package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.pnml.tools.epnk.annotations.manager.IPresentationManager;
import org.pnml.tools.epnk.annotations.netannotations.NetAnnotation;
import org.pnml.tools.epnk.annotations.netannotations.NetAnnotations;
import org.pnml.tools.epnk.annotations.netannotations.NetannotationsFactory;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;
import org.pnml.tools.epnk.applications.Application;
import org.pnml.tools.epnk.applications.IApplicationWithPresentation;
import org.pnml.tools.epnk.applications.hlpng.actions.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.operations.AbstractOperator;
import org.pnml.tools.epnk.applications.hlpng.operations.AddOperator;
import org.pnml.tools.epnk.applications.hlpng.operations.DefaultOperator;
import org.pnml.tools.epnk.applications.hlpng.operations.NumberConstantOperator;
import org.pnml.tools.epnk.applications.hlpng.operations.NumberOfOperator;
import org.pnml.tools.epnk.applications.hlpng.operations.TupleOperator;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.Arc;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.AddImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.TupleImpl;

import runtime.AbstractValue;
import runtime.MSValue;
import runtime.NetMarking;
import runtime.PlaceMarking;
import runtime.RuntimeFactory;

public class HLSimulator extends Application 
	implements IApplicationWithPresentation, ISimulator
{
	protected NetMarking netMarking = null;
	protected PetriNet petrinet = null;
	protected IPresentationManager presentationManager = null;
	protected Map<Place, MSValue> runtimeValues = null;
	private Action[] actions;
	
	public IPresentationManager getPresentationManager()
    {
    	return presentationManager;
    }

	public void setPresentationManager(IPresentationManager presentationManager)
    {
    	this.presentationManager = presentationManager;
    }

	public HLSimulator(PetriNet petrinet)
    {
	    super(petrinet);
	    
	    this.petrinet = petrinet;
	    
	    this.netMarking = evaluate(petrinet);
	    
	    this.runtimeValues = new HashMap<Place, MSValue>();
	    for(PlaceMarking marking : netMarking.getMarkings())
    	{
    		runtimeValues.put(marking.getPlace(), marking.getMsValue());
    	}
	    
	    this.presentationManager = new SimulatorPresentationManager(runtimeValues,
	    		netMarking, this);
    }
	
	@Override
	public void initializeContents()
	{

		NetAnnotations netAnnotations = this.getNetAnnotations();
		PetriNet petrinet = this.getPetrinet();

		for(Transition transition : (new FlatAccess(petrinet)).getTransitions())
		{
			NetAnnotation netAnnotation = NetannotationsFactory.eINSTANCE
			        .createNetAnnotation();
			netAnnotation.setNet(petrinet);
			ObjectAnnotation objectAnnotation = NetannotationsFactory.eINSTANCE
			        .createObjectAnnotation();
			objectAnnotation.setObject(transition);
			netAnnotation.getObjectAnnotations().add(objectAnnotation);

			for(Arc arc : transition.getIn())
			{
				objectAnnotation = NetannotationsFactory.eINSTANCE
				        .createObjectAnnotation();
				objectAnnotation.setObject(arc);
				netAnnotation.getObjectAnnotations().add(objectAnnotation);

				objectAnnotation = NetannotationsFactory.eINSTANCE
				        .createObjectAnnotation();
				objectAnnotation.setObject(arc.getSource());
				netAnnotation.getObjectAnnotations().add(objectAnnotation);
			}

			for(Arc arc : transition.getOut())
			{
				objectAnnotation = NetannotationsFactory.eINSTANCE
				        .createObjectAnnotation();
				objectAnnotation.setObject(arc);
				netAnnotation.getObjectAnnotations().add(objectAnnotation);

				objectAnnotation = NetannotationsFactory.eINSTANCE
				        .createObjectAnnotation();
				objectAnnotation.setObject(arc.getTarget());
				netAnnotation.getObjectAnnotations().add(objectAnnotation);
			}

			netAnnotations.getNetAnnotations().add(netAnnotation);
		}

		if(netAnnotations.getNetAnnotations().size() > 0)
		{
			netAnnotations
			        .setCurrent(netAnnotations.getNetAnnotations().get(0));
		}

	}
	
	private static NetMarking evaluate(PetriNet petrinet)
	{
		Map<Class, AbstractOperator> handlers = new HashMap<Class, AbstractOperator>();
		{
			AbstractOperator defaultOp = new DefaultOperator(handlers, null);
			handlers.put(NumberConstantImpl.class, new NumberConstantOperator(handlers, defaultOp));
			handlers.put(NumberOfImpl.class, new NumberOfOperator(handlers, defaultOp));
			handlers.put(AddImpl.class, new AddOperator(handlers, defaultOp));
			handlers.put(TupleImpl.class, new TupleOperator(handlers, defaultOp));
		}
		
		NetMarking netMarking = RuntimeFactory.eINSTANCE.createNetMarking();
		
		FlatAccess flatAccess = new FlatAccess(petrinet);
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Place place : flatAccess.getPlaces())
		{
			Place hlPlace = (Place)place;

			/* 
			 * Can hlPlace.getHlinitialMarking() be not null and 
			 * hlPlace.getHlinitialMarking().getStructure() set to null?
			 */
			if(hlPlace.getHlinitialMarking() != null &&
					hlPlace.getHlinitialMarking().getStructure() != null)
			{
				Term term = hlPlace.getHlinitialMarking().getStructure();
				AbstractOperator handler = handlers.get(term.getClass());
				if(handler != null)
				{
					AbstractValue value = handlers.get(term.getClass()).handle(term);
					
					if(value instanceof MSValue)
					{
						PlaceMarking marking  = RuntimeFactory.eINSTANCE.createPlaceMarking();
						marking.setPlace(hlPlace);
						marking.setMsValue((MSValue)value);
						
						netMarking.getMarkings().add(marking);
					}
					else
					{
						System.err.println("ERR: " + HLSimulator.class + ": returned " +
								"value is not an instance of " + MSValue.class);
					}
				}
			}
		}
		return netMarking;
	}
	
	@Override
	public String getName() 
	{
		return "HLSimulator";
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
		System.out.println(HLSimulator.class + ": next");
		super.nextAnnotation();
    }

	@Override
    public void previous()
    {
		System.out.println(HLSimulator.class + ": previous");
		super.previousAnnotation();
    }
	
	public void auto()
	{
		System.out.println(HLSimulator.class + ": auto");
	}
	
	public void stop()
	{
		System.out.println(HLSimulator.class + ": stop");
	}
	

}
