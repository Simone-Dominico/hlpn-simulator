package org.pnml.tools.epnk.applications.hlpng.presentation;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.pnml.tools.epnk.annotations.manager.IPresentationManager;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;
import org.pnml.tools.epnk.applications.hlpng.actions.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.selection.SelectionHandler;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

import runtime.PlaceMarking;
import transitionruntime.TransitionMarking;

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
			Font font = new Font(Display.getCurrent(), "Courier New", 8, SWT.NORMAL);
			Label label = new TopRightLabel(font, marking.getMsValue().toString(), mainFigure);
			return new LabelLayer(mainFigure, label);
		}
		return figure;
	}
}




