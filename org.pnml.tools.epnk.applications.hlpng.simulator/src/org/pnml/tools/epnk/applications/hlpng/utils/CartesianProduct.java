package org.pnml.tools.epnk.applications.hlpng.utils;

import java.util.ArrayList;
import java.util.List;

public class CartesianProduct<T>
{
	public List<List<T>> pairProduct(List<T> set1, List<T> set2)
	{
		List<List<T>> result = new ArrayList<List<T>>();
		
		for(T item1 : set1)
		{
			for(T item2 : set2)
			{
				List<T> subset = new ArrayList<T>();
				subset.add(item1);
				subset.add(item2);
				result.add(subset);
			}
		}
		
		return result;
	}
	
	public List<List<T>> product(List<List<T>> set1, List<T> set2)
	{
		List<List<T>> result = new ArrayList<List<T>>();
		
		for(List<T> subset1 : set1)
		{
			for(T item2 : set2)
			{
				List<T> subset = new ArrayList<T>(subset1);
				subset.add(item2);
				result.add(subset);
			}
		}
		
		return result;
	}
}
