package org.pnml.tools.epnk.applications.hlpng.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.pnml.tools.epnk.annotations.manager.IPresentationManager;
import org.pnml.tools.epnk.annotations.netannotations.NetAnnotations;
import org.pnml.tools.epnk.applications.IApplicationWithPresentation;
import org.pnml.tools.epnk.applications.hlpng.simulator.ISimulator;

import dk.dtu.imm.se2.group6.interfaces.IAnimator;
import dk.dtu.imm.se2.group6.visual.Animator;

public class VisualSimulator implements simulator.ISimulator, IApplicationWithPresentation, IVisualSimulator
{
	private Action[] actions = null;
	
	private IAnimator animator = null;
	private ISimulator simulator = null;
	private Set<Integer> runningAnimations = new HashSet<Integer>();
	private Map<String, Integer> staticItemMap = new HashMap<String, Integer>();
	private Map<String, Integer> modelMap = new HashMap<String, Integer>();
	

	public void setAnimator(IAnimator animator)
    {
    	this.animator = animator;
    }
	public void setSimulator(ISimulator hlSimulator)
	{
		this.simulator = hlSimulator;
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
			simulator.checkTransitions();
		}
    }

	@Override
    public void reset(IAnimator animator)
    {
		runningAnimations = new HashSet<Integer>();
		animator.setReset(true);
		animator.setInitStart(true);
		simulator.init();
		start(animator);
    }

	@Override
    public void start(IAnimator animator)
    {
		animator.setUpdatePosition(true);
		animator.setInitStart(false); 
    }
	
	@Override
    public void stop(IAnimator animator)
    {
		animator.setUpdatePosition(false);
    }
	
	@Override
    public void initCompleted(IAnimator animator)
    {
		simulator.init();
		start(animator);
    }

	/*
	 * HLPNG methods start here
	 */
	
	@Override
    public IPresentationManager getPresentationManager()
    {
	    return simulator.getPresentationManager();
    }

	@Override
    public void setPresentationManager(IPresentationManager presentationManager)
    {
	    simulator.setPresentationManager(presentationManager);
    }

	@Override
    public String getName()
    {
	    return simulator.getName();
    }

	@Override
    public void setName(String name)
    {
	    simulator.setName(name);
    }

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
					reset(animator);
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
    public String getDescription()
    {
	    return simulator.getDescription();
    }

	@Override
    public String getStatus()
    {
	    return simulator.getStatus();
    }

	@Override
    public NetAnnotations getNetAnnotations()
    {
	    return simulator.getNetAnnotations();
    }

	@Override
    public void dispose()
    {
	    simulator.dispose();
	    ((Animator)animator).getWindow().dispose();
    }

	/*
	 * Visual simulator methods start here
	 */
	
	@Override
    public synchronized void registerAnimation(int id)
    {
		runningAnimations.add(id);
    }
	
	@Override
    public synchronized boolean isReady(int id)
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
}
