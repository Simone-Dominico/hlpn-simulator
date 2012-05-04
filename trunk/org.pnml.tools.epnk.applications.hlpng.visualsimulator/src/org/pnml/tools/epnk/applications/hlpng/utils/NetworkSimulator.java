package org.pnml.tools.epnk.applications.hlpng.utils;

import org.eclipse.swt.graphics.Font;
import org.pnml.tools.epnk.applications.hlpng.simulator.HLSimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class NetworkSimulator extends HLSimulator
{
	public NetworkSimulator(PetriNet petrinet,
            EvaluationManager evaluationManager,
            ComparisonManager comparisonManager,
            ReversibleOperationManager reversibleOperationManager, Font font)
    {
	    super(petrinet, evaluationManager, comparisonManager,
	            reversibleOperationManager, font, false);
	    System.out.println("Network simulator---------------------------------");
    }
}
