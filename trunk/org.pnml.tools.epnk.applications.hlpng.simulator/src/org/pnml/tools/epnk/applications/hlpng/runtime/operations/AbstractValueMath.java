package org.pnml.tools.epnk.applications.hlpng.runtime.operations;

import java.util.Map.Entry;

import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ListValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.NumberValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersFactory;

public class AbstractValueMath
{
	public static ListValue concat(ListValue l1, ListValue l2)
	{
		ListValue listValue = new ListValue();
		
		listValue.getElements().addAll(l1.getElements());
		listValue.getElements().addAll(l2.getElements());
		listValue.setSort(l1.getSort());
		
		return listValue;
	}
	
	public static ListValue append(ListValue l, IValue v)
	{
		ListValue listValue = new ListValue();
		
		listValue.getElements().addAll(l.getElements());
		listValue.getElements().add(v);
		listValue.setSort(l.getSort());
		
		return listValue;
	}
	
	public static NumberValue length(ListValue l)
	{
		NumberValue nv = new NumberValue();
		nv.setN(l.getElements().size());
		nv.setSort(IntegersFactory.eINSTANCE.createNatural());
		
		return nv;
	}
	
	public static ListValue sublist(ListValue l, int start, int length)
	{
		ListValue listValue = new ListValue();
		
		listValue.setSort(l.getSort());
		listValue.getElements().addAll(l.getElements().subList(start, 
				start + length));
		
		return listValue;
	}
	
	public static IValue at(ListValue l, int index)
	{
		return l.getElements().get(index);
	}
	
    public static MSValue add(MSValue msSet, IValue value, Integer multiplicity)
    {
    	MSValue mainSet = lightCopy(msSet);
    	
    	if(mainSet.contains(value))
        {
    		Integer newMultiplicity = mainSet.get(value) + multiplicity;
    		mainSet.put(value, newMultiplicity);
        }
    	else
    	{
    		mainSet.put(value, multiplicity);
    	}
    	
    	return mainSet;
    }
    
    public static MSValue subtract(MSValue msSet, IValue value, int multiplicity)
    {
    	MSValue newMsSet = add(msSet, value, multiplicity * -1);
    	
    	Integer count = newMsSet.get(value);
    	
    	if(count == null || count == 0)
    	{
    		newMsSet.remove(value);
    	}
    	
    	return newMsSet;
    }
    
    public static MSValue subtract(MSValue msSet1, IMSValue msSet2)
    {
    	MSValue newMsSet = lightCopy(msSet1);
    	for(Entry<IValue, Integer> entry : msSet2.entrySet())
    	{
    		newMsSet = subtract(newMsSet, entry.getKey(), entry.getValue());
    	}
    	return newMsSet;
    }
    
    public static MSValue append(MSValue msSet1, IMSValue msSet2)
    {    	
    	MSValue msSet = new MSValue();
    	msSet.setSort(msSet1.getSort());
    	
        for(Entry<IValue, Integer> entry : msSet1.entrySet())
        {
        	msSet = AbstractValueMath.add(msSet, entry.getKey(), entry.getValue());
        }
        
        for(Entry<IValue, Integer> entry : msSet2.entrySet())
        {
        	msSet = AbstractValueMath.add(msSet, entry.getKey(), entry.getValue());
        }
        
        return msSet;
    }
    
    public static MSValue lightCopy(MSValue initial)
    {
    	MSValue msSet = new MSValue();
    	msSet.setSort(initial.getSort());

    	msSet.putAll(initial.entrySet());
    	
    	return msSet;
    }
    
    // msSet1 <= msSet2
    public static boolean lessEqual(IMSValue msSet1, IMSValue msSet2)
    {    	
        for(Entry<IValue, Integer> entry1 : msSet1.entrySet())
        {
        	IValue key = entry1.getKey();
        	Integer value = entry1.getValue();
        	
        	if(!msSet2.contains(key) || value > msSet2.get(key))
        	{
        		return false;
        	}
        }
        
        return true;
    }
}
