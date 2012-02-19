package org.pnml.tools.epnk.applications.hlpng.simulator;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.pnml.tools.epnk.applications.activator.Activator;
import org.pnml.tools.epnk.applications.registry.ApplicationRegistry;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class StartSimulatorApp implements IObjectActionDelegate {

	private PetriNet petrinet;

	@Override
	public void run(IAction action) {
		HLSimulator application = new HLSimulator(petrinet);
		Activator activator = Activator.getInstance();
        ApplicationRegistry registry = activator.getApplicationRegistry();
		registry.addApplication(application);
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		petrinet = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1) {
				java.lang.Object selected = structuredSelection.getFirstElement();
                if (selected instanceof PetriNet) {
                	petrinet = (PetriNet) selected;
				}
			}
		}
		action.setEnabled(isEnabled());
	}

	private boolean isEnabled() {
		return petrinet != null;
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {}

}
