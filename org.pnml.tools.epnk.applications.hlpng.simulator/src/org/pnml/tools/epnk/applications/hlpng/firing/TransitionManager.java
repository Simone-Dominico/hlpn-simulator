package org.pnml.tools.epnk.applications.hlpng.firing;

import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.TransitionMarking;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UnknownVariableException;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

public class TransitionManager
{
	private ArcInscriptionManager arcInscriptionManager = null;
	
	public TransitionManager(ArcInscriptionManager arcInscriptionManager)
	{
		this.arcInscriptionManager = arcInscriptionManager;
	}
	
	public TransitionMarking checkTransition(Transition transition, Map<String, 
			PlaceMarking> runtimeValues) throws DependencyException, UnknownVariableException
	{		
		List<FiringMode> assignments = arcInscriptionManager.assignments(transition, 
				runtimeValues);
		
		TransitionMarking marking = new TransitionMarking();

		marking.getModes().addAll(assignments);
		
		return marking;
	}
}
