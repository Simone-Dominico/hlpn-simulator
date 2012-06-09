package org.pnml.tools.epnk.applications.hlpng.functions;

import geditor.GObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.simulator.IVisualSimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;

import dk.dtu.imm.se2.group6.interfaces.IAnimator;

import Appearence.Shape;

public abstract class AbstractFunction implements IEvaluator
{
	protected Map<String, GObject> geometryMap = null;
	protected Map<String, Shape> shapeMap = null;
	
	protected IAnimator animator = null;
	protected IVisualSimulator visualSimulator = null;
	
	@Override
	public AbstractValue evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, AbstractValue> assignments) throws UnknownVariableException
	{
		Operator operator = (Operator) term;
		List<AbstractValue> values = new ArrayList<AbstractValue>();
		for(Term subterm : operator.getSubterm())
		{
			IEvaluator evaluator = evaluationManager.getHandler(subterm.getClass()); 
			AbstractValue value = evaluator.evaluate(subterm, evaluationManager, assignments);
			values.add(value);
		}
			
		MSValue ms = new MSValue();
		ms.setSort(TermsFactory.eINSTANCE.createMultiSetSort());
		
		AbstractValue value = execute(values);
		if(value != null)
		{
			return value;
		}
		
		return ms;
	}
	
	public abstract AbstractValue execute(List<AbstractValue> values);

	public Map<String, GObject> getGeometryMap()
    {
    	return geometryMap;
    }

	public void setGeometryMap(Map<String, GObject> geometryMap)
    {
    	this.geometryMap = geometryMap;
    }

	public Map<String, Shape> getShapeMap()
    {
    	return shapeMap;
    }

	public void setShapeMap(Map<String, Shape> shapeMap)
    {
    	this.shapeMap = shapeMap;
    }

	public IAnimator getAnimator()
    {
    	return animator;
    }

	public void setAnimator(IAnimator animator)
    {
    	this.animator = animator;
    }

	public IVisualSimulator getVisualSimulator()
    {
    	return visualSimulator;
    }

	public void setVisualSimulator(IVisualSimulator visualSimulator)
    {
    	this.visualSimulator = visualSimulator;
    }
	
	@Override
    public String validate(Object term)
    {
	    return null;
    }
}
