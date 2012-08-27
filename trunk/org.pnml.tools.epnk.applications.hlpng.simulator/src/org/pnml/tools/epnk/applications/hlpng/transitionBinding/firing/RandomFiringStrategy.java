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
package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;

public class RandomFiringStrategy implements IFiringStrategy
{
	@Override
	public FiringMode fire(IRuntimeState currentMarking)
	{
		Map<IDWrapper, List<FiringMode>> modes = currentMarking.getModes();
		
		List<IDWrapper> transitions = new ArrayList<IDWrapper>(modes.keySet());
		int tIndex = getIndex(transitions.size());
		
		if(tIndex == -1)
		{
			return null;
		}
		
		List<FiringMode> fModes = modes.get(transitions.get(tIndex));
		int fIndex = getIndex(fModes.size());
		
		if(fIndex == -1)
		{
			return null;
		}
		
		return fModes.get(fIndex);
	}
	
	private static int getIndex(int size)
	{
		if(size == 0)
		{
			return -1;
		}
		if(size == 1)
		{
			return 0;
		}
		
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(size);
	}
}
