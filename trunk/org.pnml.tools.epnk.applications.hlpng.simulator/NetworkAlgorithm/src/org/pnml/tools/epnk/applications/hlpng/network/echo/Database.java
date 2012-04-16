package org.pnml.tools.epnk.applications.hlpng.network.echo;

import java.util.ArrayList;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;

public class Database
{
	private static List<AbstractValue> others = null;
	private static List<AbstractValue> initiators = null;
	private static int[][] graph = {{0,1,1,1,0,1},
									{1,0,0,0,1,1},
									{1,0,0,1,0,1},
									{1,0,1,0,1,0},
									{0,1,0,1,0,0},
									{1,1,1,0,0,0}};
	
	public static List<AbstractValue> getOthers()
	{
		if(others == null)
		{
			others = new ArrayList<AbstractValue>();
			
			for(int i = 'A'; i < 'F'; i++)
			{
				StringValue strValue = new StringValue();
				strValue.setData(String.valueOf((char)i));
				strValue.setSort(TermsFactory.eINSTANCE.createUserSort());
				
				others.add(strValue);
			}	
		}
		
		return others;
	}
	
	public static List<AbstractValue> getInitiators()
	{
		if(initiators == null)
		{
			initiators = new ArrayList<AbstractValue>();
			
			StringValue strValue = new StringValue();
			strValue.setData(String.valueOf((char)'F'));
			strValue.setSort(TermsFactory.eINSTANCE.createUserSort());
			
			initiators.add(strValue);	
		}
		
		return initiators;
	}
	
	public static List<AbstractValue> getM1(AbstractValue sender)
	{
		List<AbstractValue> messages = new ArrayList<AbstractValue>();
		
		int senderIndex = ((StringValue)sender).getData().charAt(0) - 'A';
		
		for(int i = 0; i < graph[senderIndex].length; i++)
		{
			if(graph[senderIndex][i] == 1)
			{			
				StringValue receiver = new StringValue();
				receiver.setData(String.valueOf((char)('A' + i)));
				receiver.setSort(TermsFactory.eINSTANCE.createUserSort());
				
				ProductValue pValue = new ProductValue();
				pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
				pValue.getComponents().add(receiver);
				pValue.getComponents().add(sender);
				
				messages.add(pValue);
			}
		}
		return messages;
	}
	
	public static List<AbstractValue> getM2(AbstractValue receiver)
	{
		List<AbstractValue> messages = new ArrayList<AbstractValue>();
		
		int receiverIndex = ((StringValue)receiver).getData().charAt(0) - 'A';
		
		for(int i = 0; i < graph[receiverIndex].length; i++)
		{
			if(graph[receiverIndex][i] == 1)
			{
				StringValue sender = new StringValue();
				sender.setData(String.valueOf((char)('A' + i)));
				sender.setSort(TermsFactory.eINSTANCE.createUserSort());
				
				ProductValue pValue = new ProductValue();
				pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
				pValue.getComponents().add(receiver);
				pValue.getComponents().add(sender);
				
				messages.add(pValue);
			}
		}
		
		return messages;
	}
	
}
