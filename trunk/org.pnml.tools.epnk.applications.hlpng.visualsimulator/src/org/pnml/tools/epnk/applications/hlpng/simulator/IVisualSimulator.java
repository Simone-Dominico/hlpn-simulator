package org.pnml.tools.epnk.applications.hlpng.simulator;

public interface IVisualSimulator
{
	public void registerAnimation(int id);
	public boolean isReady(int id);
	public void registerStaticItem(String name, int id);
	public void registerModel(String name, int id);
	public int getModelId(String name);
	public int getStaticItemId(String name);
}
