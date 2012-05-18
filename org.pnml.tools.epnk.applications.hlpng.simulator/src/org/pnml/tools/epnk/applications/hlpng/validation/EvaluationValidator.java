package org.pnml.tools.epnk.applications.hlpng.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class EvaluationValidator extends AbstractModelConstraint
{
	public IStatus validate(IValidationContext ctx)
	{
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();

		// In the case of batch mode.
		if(eType == EMFEventType.NULL && eObj instanceof PetriNet)
		{
			return ctx.createFailureStatus(new Object[] { eObj.eClass()
			        .getName() });
		}
		return ctx.createFailureStatus(new Object[] {"Failure mla"});
	}
}
