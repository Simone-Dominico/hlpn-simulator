package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.List;
import java.util.Random;

import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.FiringMode;

public class RandomFiringStrategy implements IFiringStrategy
{
	@Override
    public FiringMode fire(List<FiringMode> modes)
	{
		int size = modes.size();
		if(size == 0)
		{
			return null;
		}
		if(size == 1)
		{
			return modes.get(0);
		}
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(size);
		return modes.get(index);
	}
}
