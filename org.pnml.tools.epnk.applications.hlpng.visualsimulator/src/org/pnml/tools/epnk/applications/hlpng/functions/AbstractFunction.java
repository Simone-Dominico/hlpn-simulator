package org.pnml.tools.epnk.applications.hlpng.functions;

import geditor.GObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;
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
	
	protected RuntimeValueFactory factory = null;
	
	@Override
	public IValue evaluate(Term term, EvaluationManager evaluationManager,
			Map<TermWrapper, IValue> assignments) throws UnknownVariableException
	{
		Operator operator = (Operator) term;
		List<IValue> values = new ArrayList<IValue>();
		for(Term subterm : operator.getSubterm())
		{
			IEvaluator evaluator = evaluationManager.getHandler(subterm.getClass()); 
			IValue value = evaluator.evaluate(subterm, evaluationManager, assignments);
			values.add(value);
		}
			
		IMSValue ms = factory.createMSValue();
		ms.setSort(TermsFactory.eINSTANCE.createMultiSetSort());
		
		IValue value = execute(values);
		if(value != null)
		{
			return value;
		}
		
		return ms;
	}
	
	public abstract IValue execute(List<IValue> values);

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

	public RuntimeValueFactory getFactory()
    {
    	return factory;
    }

	public void setFactory(RuntimeValueFactory factory)
    {
    	this.factory = factory;
    }
}
