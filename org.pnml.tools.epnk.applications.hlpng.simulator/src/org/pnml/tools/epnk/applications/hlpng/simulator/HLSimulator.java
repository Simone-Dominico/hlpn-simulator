package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.annotations.netannotations.NetAnnotation;
import org.pnml.tools.epnk.annotations.netannotations.NetAnnotations;
import org.pnml.tools.epnk.annotations.netannotations.NetannotationsFactory;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;
import org.pnml.tools.epnk.applications.Application;
import org.pnml.tools.epnk.applications.hlpng.operations.AbstractOperator;
import org.pnml.tools.epnk.applications.hlpng.operations.AddOperator;
import org.pnml.tools.epnk.applications.hlpng.operations.DefaultOperator;
import org.pnml.tools.epnk.applications.hlpng.operations.NumberConstantOperator;
import org.pnml.tools.epnk.applications.hlpng.operations.NumberOfOperator;
import org.pnml.tools.epnk.applications.hlpng.operations.TupleOperator;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.AddImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.TupleImpl;

import runtime.AbstractValue;
import runtime.NetMarking;
import runtime.RuntimeFactory;

public class HLSimulator extends Application
{
	private NetMarking netMarking = null;
	
	public HLSimulator(PetriNet petrinet)
    {
	    super(petrinet);
	    
	    this.netMarking = evaluate(petrinet);
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
					System.out.println(value.toString());
				}
				else
				{
					System.out.println(term.getClass());
					System.out.println("Cannot handle term: " + term);
				}
			}
		}
		return netMarking;
	}
}
