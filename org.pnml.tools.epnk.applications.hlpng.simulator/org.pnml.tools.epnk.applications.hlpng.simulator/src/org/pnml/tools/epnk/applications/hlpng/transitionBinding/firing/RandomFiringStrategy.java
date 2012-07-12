package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.pnml.tools.epnk.applications.hlpng.runtimeStates.IRuntimeState;

public class RandomFiringStrategy implements IFiringStrategy
{
	@Override
	public FiringMode fire(Map<IDWrapper, List<FiringMode>> modes, 
			IRuntimeState currentMarking)
	{
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
