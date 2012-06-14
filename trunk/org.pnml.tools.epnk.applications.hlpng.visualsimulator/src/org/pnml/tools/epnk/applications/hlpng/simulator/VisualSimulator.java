package org.pnml.tools.epnk.applications.hlpng.simulator;

import geditor.GObject;
import geditor.Geometry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Font;
import org.pnml.tools.epnk.applications.hlpng.contributors.ExtensionManager;
import org.pnml.tools.epnk.applications.hlpng.functions.AbstractFunction;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NetMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.applications.hlpng.simulator.HLSimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserSort;

import Appearence.Shape;
import Appearence.Shapes;

import dk.dtu.imm.se2.group6.interfaces.IAnimator;
import dk.dtu.imm.se2.group6.visual.Animator;

public class VisualSimulator extends HLSimulator implements IVisualSimulator
{
	private Action[] actions = null;
	
	private IAnimator animator = null;
	private Set<Integer> runningAnimations = new HashSet<Integer>();
	private Map<String, Integer> staticItemMap = new HashMap<String, Integer>();
	private Map<String, Integer> modelMap = new HashMap<String, Integer>();
	private ExtensionManager extensionManager = null;

	public VisualSimulator(PetriNet petrinet,
            EvaluationManager evaluationManager,
            ComparisonManager comparisonManager,
            ReversibleOperationManager reversibleOperationManager, Font font,
            Animator animator, String globalAppearancepath, Geometry geometry,
            Shapes appearance, ExtensionManager extensionManager)
    {
	    super(petrinet, evaluationManager, comparisonManager,
	            reversibleOperationManager, font, false);
	    
	    this.animator = animator;
	    this.extensionManager = extensionManager;
	    
		Map<String, GObject> geometryMap = new HashMap<String, GObject>();
		{
			for(GObject g : geometry.getGeometryObjects())
			{
				if(g.getId() != null)
				{
					geometryMap.put(g.getId(), g);
					try
					{
						int id = animator.createStaticItem(g, null, globalAppearancepath);
						registerStaticItem(g.getId(), id);
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
			for(Shape s : appearance.getAppearence())
			{
				shapeMap.put(s.getId(), s);
				int id = animator.createModel(s, false);
				registerModel(s.getId(), id);
			}
		}
		
		for(IEvaluator evaluator : extensionManager.getEvaluators())
		{
			AbstractFunction function = (AbstractFunction) evaluator;
			function.setGeometryMap(geometryMap);
			function.setShapeMap(shapeMap);
			function.setVisualSimulator(this);
		}
		
		// make animator visible
		this.animator.setWindow(new Window(animator));
		this.animator.setSimulator(this);
		this.animator.setVisible(true);
		this.animator.initRequested();
    }

	@Override
	public void reset()
    {
		simulationViewController.clear();
		animator.setReset(true);
    }
	
	/*
	 * 3D engine simulation methods start here
	 */
	
	@Override
    public void animationFinished(int ItemID, IAnimator animator)
    {
		if(runningAnimations.contains(ItemID))
		{
			runningAnimations.remove(ItemID);
			List<FiringMode> modes = updateTransitionMarking();
			
			while(modes.size() > 0)
			{
				modes = fire(modes.get(0));
			}
		}
    }

	@Override
    public void reset(IAnimator animator)
    {
		runningAnimations = new HashSet<Integer>();		
		go((NetMarking)this.getNetAnnotations().getCurrent(), extensionManager, animator);
    }

	@Override
    public void start(IAnimator animator)
    {
		animator.setUpdatePosition(true);
		
		List<FiringMode> modes = updateTransitionMarking();
		
		while(modes.size() > 0)
		{
			modes = fire(modes.get(0));
		}
    }
	
	@Override
    public void stop(IAnimator animator)
    {
		animator.setUpdatePosition(false);
    }
	
	@Override
    public void initCompleted(IAnimator animator)
    {
		go((NetMarking)this.getNetAnnotations().getCurrent(), extensionManager, animator);
    }

	private void go(NetMarking netMarking, 
			ExtensionManager extensionManager, IAnimator animator)
	{
		init();
		init((NetMarking)this.getNetAnnotations().getCurrent(), extensionManager);
	}
	private static void init(NetMarking netMarking, ExtensionManager extensionManager)
	{
		for(AbstractMarking marking : netMarking.getMarkings())
		{
			if(marking instanceof PlaceMarking)
			{
				PlaceMarking placeMarking = (PlaceMarking) marking;
				Place place = placeMarking.getPlace();
				
				if(place.getHlinitialMarking() != null && 
						place.getHlinitialMarking().getStructure() != null)
				{
					Sort sort = place.getType().getStructure();
					if(sort instanceof UserSort)
					{
						UserSort userSort = (UserSort)sort;
						if(userSort.getName().equals("DYNAMIC_MODEL"))
						{
							IEvaluator appearEvaluator = extensionManager.getHandlers().get("APPEAR");
							AbstractFunction appearFunction = (AbstractFunction) appearEvaluator;
							
							for(AbstractValue value : placeMarking.getMsValue().getValues().keySet())
							{
								appearFunction.execute(((ProductValue)value).getComponents());	
							}
						}
						else if(userSort.getName().equals("STATIC_MODEL"))
						{
							IEvaluator appearEvaluator = extensionManager.getHandlers().get("APPEAR_POINT");
							AbstractFunction appearFunction = (AbstractFunction) appearEvaluator;

							for(AbstractValue value : placeMarking.getMsValue().getValues().keySet())
							{
								appearFunction.execute(((ProductValue)value).getComponents());
							}
						}
					}	
				}
			}
		}
	}
	
	/*
	 * Visual simulator methods start here
	 */
	
	@Override
    public void registerAnimation(int id)
    {
		runningAnimations.add(id);
    }
	
	@Override
    public boolean isReady(int id)
    {
		if(runningAnimations.contains(id))
		{
			return false;
		}
		return true;
    }
	
	@Override
    public void registerStaticItem(String name, int id)
    {
	    staticItemMap.put(name, id);
    }
	
	@Override
    public void registerModel(String name, int id)
    {
		modelMap.put(name, id);
    }
	
	@Override
    public int getModelId(String name)
    {
	    return modelMap.get(name);
    }
	
	@Override
    public int getStaticItemId(String name)
    {
	    return staticItemMap.get(name);
    }
	
	/*
	 * HLPNG methods start here
	 */

	@Override
    public Action[] getActions()
    {
		if(actions == null)
		{
	    	actions = new Action[3];
			
			actions[0] = new Action() {
				public void run() {
					start(animator);
				}
			};
			actions[0].setId("start");
			actions[0].setText("Start");
			actions[0].setToolTipText("Start the simulation");
//			actions[0].setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(ISharedImages.IMG_TOOL_UP));
			
			actions[1] = new Action() {
				public void run() {
					stop(animator);
				}
			};
			actions[1].setId("stop");
			actions[1].setText("Stop");
			actions[1].setToolTipText("Stop the simulation");
//			actions[1].setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));
			
			
			actions[2] = new Action() {
				public void run() {
					reset();
				}
			};
			actions[2].setId("reset");
			actions[2].setText("Reset");
			actions[2].setToolTipText("Reset the simulation");
//			actions[2].setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));
	    }
	    return actions;
    }

	@Override
    public void shutDown()
    {
	    super.shutDown();
	    ((Animator)animator).getWindow().dispose();
    }

}
