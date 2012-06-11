package org.pnml.tools.epnk.applications.hlpng.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.pnml.tools.epnk.applications.hlpng.resources.ResourceManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IValidator;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class EvaluationValidator extends AbstractModelConstraint
{
	private EvaluationManager evaluationManager = null;
	
	public EvaluationValidator()
	{
		this.evaluationManager = 
				ResourceManager.createEvaluationManager("org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions");
	}
	public IStatus validate(IValidationContext ctx)
	{
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();

		// In the case of batch mode.
		if(eType == EMFEventType.NULL && eObj instanceof PetriNet)
		{
			List<String> errorMessages = check((PetriNet)eObj, this.evaluationManager);
			if(errorMessages != null && errorMessages.size() > 0)
			{
				StringBuffer buffer = new StringBuffer("\n");
				for(String str : errorMessages)
				{
					buffer.append(str + "\n");
				}
				
				return ctx.createFailureStatus(new Object[] { buffer.toString() + "\n" });
			}
		}
		return ctx.createSuccessStatus();
	}
	
	private static List<String> check(PetriNet petriNet, EvaluationManager evaluationManager)
	{
		List<String> errorMessages = new ArrayList<String>();
		
		FlatAccess flatAccess = new FlatAccess(petriNet);
		
		// check places
		for(org.pnml.tools.epnk.pnmlcoremodel.Place p : flatAccess.getPlaces())
		{
			Place place = (Place)p;
			if(place.getHlinitialMarking() != null && 
					place.getHlinitialMarking().getStructure() != null)
			{
				String err = check(place.getHlinitialMarking().getStructure(), evaluationManager);
				if(err != null)
				{
					errorMessages.add(err);	
				}
			}
		}
		// check transitions
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition t : flatAccess.getTransitions())
		{
			Transition transition = (Transition)t;
			if(transition.getCondition() != null && 
					transition.getCondition().getStructure() != null)
			{
				String err = check(transition.getCondition().getStructure(), evaluationManager);
				if(err != null)
				{
					errorMessages.add(err);	
				}
			}
		}
		// check arc inscriptions
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition t : flatAccess.getTransitions())
		{
			Transition transition = (Transition)t;
			
			List<org.pnml.tools.epnk.pnmlcoremodel.Arc> arcs = 
					new ArrayList<org.pnml.tools.epnk.pnmlcoremodel.Arc>();
			arcs.addAll(transition.getIn());
			arcs.addAll(transition.getOut());
			
			for(org.pnml.tools.epnk.pnmlcoremodel.Arc a : arcs)
			{
				Arc arc = (Arc) a;
				
				if(arc.getHlinscription() != null && 
						arc.getHlinscription().getStructuralFeature() != null)
				{
					String err = check(arc.getHlinscription().getStructure(), evaluationManager);
					if(err != null)
					{
						errorMessages.add(err);	
					}
				}
			}
		}

		return errorMessages;
	}
	
	private static String check(Term term, EvaluationManager evaluationManager)
	{
		IValidator validator = evaluationManager.getHandler(term.getClass());

		if(validator == null)
		{
			return "The term " + term.getClass().toString() + " is not supported!";
		}
		
		String error = validator.validate(term);
		if(error != null)
		{
			return error;
		}
		
		if(term instanceof Operator)
		{
			Operator operator = (Operator)term;
			
			for(Term subTerm : operator.getSubterm())
			{
				String err = check(subTerm, evaluationManager);
				if(err != null)
				{
					return err;
				}
			}
		}
		
		return null;
	}
}