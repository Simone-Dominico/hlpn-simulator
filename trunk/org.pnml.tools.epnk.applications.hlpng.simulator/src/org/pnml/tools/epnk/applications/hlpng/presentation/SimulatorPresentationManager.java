/*******************************************************************************
 * Copyright (c) 2012 Mindaugas Laganeckas.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Mindaugas Laganeckas - initial API and implementation
 ******************************************************************************/
package org.pnml.tools.epnk.applications.hlpng.presentation;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.graphics.Font;
import org.pnml.tools.epnk.annotations.manager.IPresentationManager;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;
import org.pnml.tools.epnk.applications.hlpng.presentation.decorations.LabelLayer;
import org.pnml.tools.epnk.applications.hlpng.presentation.decorations.TopRightLabel;
import org.pnml.tools.epnk.applications.hlpng.presentation.marking.PlaceMarking;
import org.pnml.tools.epnk.applications.hlpng.presentation.marking.TransitionMarking;
import org.pnml.tools.epnk.applications.hlpng.presentation.popup.SelectionHandler;
import org.pnml.tools.epnk.applications.presentation.IApplicationWithPresentation;

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
	
	@Override
	public IFigure handle(ObjectAnnotation objectAnnotation, 
			AbstractGraphicalEditPart graphicalEditPart)
	{
		IFigure figure = null;
		if(objectAnnotation instanceof TransitionMarking)
		{			
			TransitionMarking marking = (TransitionMarking) objectAnnotation;

			TransitionOverlay coloredMarking = new TransitionOverlay(simulator,
					graphicalEditPart.getFigure(), marking);
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
			org.pnml.tools.epnk.pnmlcoremodel.Object place = marking.getObject();
			
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




