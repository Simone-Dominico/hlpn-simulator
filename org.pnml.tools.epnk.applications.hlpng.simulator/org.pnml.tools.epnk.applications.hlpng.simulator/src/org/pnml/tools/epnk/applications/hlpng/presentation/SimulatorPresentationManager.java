package org.pnml.tools.epnk.applications.hlpng.presentation;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.graphics.Font;
import org.pnml.tools.epnk.annotations.manager.IPresentationManager;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;
import org.pnml.tools.epnk.applications.IApplicationWithPresentation;
import org.pnml.tools.epnk.applications.hlpng.presentation.marking.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.presentation.marking.TransitionMarking;
import org.pnml.tools.epnk.applications.hlpng.presentation.selection.SelectionHandler;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

public class SimulatorPresentationManager implements IPresentationManager
{
	protected SelectionHandler selectionHandler = null;
	protected IApplicationWithPresentation simulator = null;
	protected Font font = null;
	
	public SimulatorPresentationManager(IApplicationWithPresentation simulator, Font font)
	{
		this.simulator = simulator;
		
		this.selectionHandler = new SelectionHandler(simulator);
		
		this.font = font;
	}
	
	public IFigure handle(ObjectAnnotation objectAnnotation, 
			AbstractGraphicalEditPart graphicalEditPart)
	{
		IFigure figure = null;
		if(objectAnnotation instanceof TransitionMarking)
		{			
			TransitionMarking marking = (TransitionMarking) objectAnnotation;
			Transition transition = marking.getTransition();

			RectangleOverlay coloredMarking = new RectangleOverlay(simulator,
					graphicalEditPart.getFigure(), transition, marking);
			coloredMarking.addMouseListener(selectionHandler);
			
			if(marking.isFired())
			{
				coloredMarking.request();	
			}
			
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

			Label label = new TopRightLabel(font, marking.getMsValue().toString(), mainFigure);
			
			return new LabelLayer(mainFigure, label);
		}
		return figure;
	}

	@Override
    public void notifyOwner()
    {
	    simulator.activate();
    }
}




