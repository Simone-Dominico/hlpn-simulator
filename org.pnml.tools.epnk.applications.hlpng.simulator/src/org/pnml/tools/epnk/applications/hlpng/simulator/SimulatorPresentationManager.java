package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.pnml.tools.epnk.annotations.manager.IPresentationManager;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;
import org.pnml.tools.epnk.applications.hlpng.actions.ISimulator;
import org.pnml.tools.epnk.applications.hlpng.actions.SelectionHandler;
import org.pnml.tools.epnk.applications.hlpng.view.LabelLayer;
import org.pnml.tools.epnk.applications.hlpng.view.RectangleOverlay;
import org.pnml.tools.epnk.applications.hlpng.view.TopRightLabel;
import org.pnml.tools.epnk.pnmlcoremodel.Object;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

import runtime.MSValue;
import runtime.NetMarking;

public class SimulatorPresentationManager implements IPresentationManager
{
	protected Map<Place, MSValue> runtimeValues = null;
	protected NetMarking netMarking = null;
	
	protected SelectionHandler selectionHandler = null;
	protected ISimulator simulator = null;
	
	public SimulatorPresentationManager(Map<Place, MSValue> runtimeValues,
			NetMarking netMarking, ISimulator simulator)
	{
		this.runtimeValues = runtimeValues;
		this.netMarking = netMarking;
		this.simulator = simulator;
		
		this.selectionHandler = new SelectionHandler();
	}
	
	public IFigure handle(Object owner, ObjectAnnotation objectAnnotation, 
			AbstractGraphicalEditPart graphicalEditPart)
	{
		IFigure figure = null;
		if(owner instanceof Transition)
		{			
			Transition transition = (Transition)owner;

			IFigure coloredMarking = new RectangleOverlay(simulator,
					graphicalEditPart.getFigure(), transition, runtimeValues);
			coloredMarking.addMouseListener(selectionHandler);

			return coloredMarking;
		}
		else if(owner instanceof Place)
		{
			Place place = (Place) owner;
			
			StringBuffer names = new StringBuffer();
			if(place.getName() != null)
			{
				names.append(place.getName().getText() + "\n");
			}
			
			IFigure mainFigure = graphicalEditPart.getFigure();
			Font font = new Font(Display.getCurrent(), "Courier New", 8, SWT.NORMAL);
			Label label = new TopRightLabel(font, currentMarking(runtimeValues, place), mainFigure);
			return new LabelLayer(mainFigure, label);
		}
		return figure;
	}
	
	private static String currentMarking(Map<Place, MSValue> runtimeValues, Place place)
	{
		MSValue value = runtimeValues.get(place);
		
		return value.toString();
	}
}




