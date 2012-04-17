package org.pnml.tools.epnk.applications.hlpng.presentation;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.widgets.Display;
import org.pnml.tools.epnk.annotations.manager.IPresentationManager;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;
import org.pnml.tools.epnk.applications.hlpng.runtime.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.runtime.TransitionMarking;
import org.pnml.tools.epnk.applications.hlpng.selection.SelectionHandler;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.ISimulator;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

public class SimulatorPresentationManager implements IPresentationManager
{
	protected SelectionHandler selectionHandler = null;
	protected ISimulator simulator = null;
	
	public SimulatorPresentationManager(ISimulator simulator)
	{
		this.simulator = simulator;
		
		this.selectionHandler = new SelectionHandler();
	}
	
	public IFigure handle(ObjectAnnotation objectAnnotation, 
			AbstractGraphicalEditPart graphicalEditPart)
	{
		IFigure figure = null;
		if(objectAnnotation instanceof TransitionMarking)
		{			
			TransitionMarking marking = (TransitionMarking) objectAnnotation;
			Transition transition = marking.getTransition();

			IFigure coloredMarking = new RectangleOverlay(simulator,
					graphicalEditPart.getFigure(), transition, marking);
			coloredMarking.addMouseListener(selectionHandler);

			return coloredMarking;
		}
		else if(objectAnnotation instanceof PlaceMarking)
		{
			PlaceMarking marking = (PlaceMarking) objectAnnotation;
			Place place = marking.getPlace();
			
			StringBuffer names = new StringBuffer();
			if(place.getName() != null)
			{
				names.append(place.getName().getText() + "\n");
			}
			
			IFigure mainFigure = graphicalEditPart.getFigure();
			Label label = new TopRightLabel(Display.getCurrent().getSystemFont(), 
					marking.getMsValue().toString(), mainFigure);
			
			return new LabelLayer(mainFigure, label);
		}
		return figure;
	}
}




