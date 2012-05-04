package org.pnml.tools.epnk.applications.hlpng.functions;

import geditor.GObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.utils.IVisualSimulator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;

import dk.dtu.imm.se2.group6.interfaces.IAnimator;

import Appearence.Shape;

public abstract class AbstractFunction implements IEvaluator
{
	protected Map<String, GObject> geometryMap = null;
	protected Map<String, Shape> shapeMap = null;
	
	protected IAnimator animator = null;
	protected IVisualSimulator visualSimulator = null;
	
	public AbstractFunction(IAnimator animator)
	{
		this.animator = animator;
	}
	
	@Override
	public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
	{
		MSValue ms = new MSValue();
		ms.setSort(TermsFactory.eINSTANCE.createMultiSetSort());
		
		AbstractValue value = execute(new ArrayList<AbstractValue>(values));
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
}
