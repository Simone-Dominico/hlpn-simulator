package org.pnml.tools.epnk.applications.hlpng.utils;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.pnml.tools.epnk.applications.hlpng.contributors.NetworkExtensionManager;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;
import org.pnml.tools.epnk.applications.hlpng.simulator.HLSimulator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IFiringStrategy;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ReversibleOperationManager;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class NetworkSimulator extends HLSimulator
{
	public NetworkSimulator(PetriNet petrinet,
            EvaluationManager evaluationManager,
            ComparisonManager comparisonManager,
            ReversibleOperationManager reversibleOperationManager, Font font,
            RuntimeValueFactory factory, NetworkExtensionManager extensionManager,
            IFiringStrategy strategy)
    {
	    super(petrinet, evaluationManager, comparisonManager,
	            reversibleOperationManager, font, factory, strategy, false,
	            Display.getCurrent());
	    
	    for(IEvaluator evaluator : extensionManager.getEvaluators())
		{
			AbstractFunction function = (AbstractFunction) evaluator;
			function.setRuntimeValueFactory(factory);
		}
	    
	    init();
    }
}
