package org.pnml.tools.epnk.applications.hlpng.utils;

import simulator.ISimulator;

public interface IVisualSimulator extends ISimulator
{
	public void registerAnimation(int id);
	public boolean isReady(int id);
	public void registerStaticItem(String name, int id);
	public void registerModel(String name, int id);
	public int getModelId(String name);
	public int getStaticItemId(String name);
}
