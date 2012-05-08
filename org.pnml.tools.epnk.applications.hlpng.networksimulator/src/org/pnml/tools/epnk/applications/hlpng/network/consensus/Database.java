package org.pnml.tools.epnk.applications.hlpng.network.consensus;

import java.util.ArrayList;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;

public class Database
{
	private static List<AbstractValue> sites = null;
	private static List<AbstractValue> messages = null;
	
	public static List<AbstractValue> getSites()
	{
		if(sites == null)
		{
			sites = new ArrayList<AbstractValue>();
			
			for(int i = 'A'; i < 'D'; i++)
			{
				StringValue strValue = new StringValue();
				strValue.setData(String.valueOf((char)i));
				strValue.setSort(TermsFactory.eINSTANCE.createUserSort());
				
				sites.add(strValue);
			}	
		}
		
		return sites;
	}
	
	public static List<AbstractValue> getMessages()
	{
		if(messages == null)
		{
			messages = new ArrayList<AbstractValue>();
			for(int i = 'A'; i < 'D'; i++)
			{
				for(int j = 'A'; j < 'D'; j++)
				{
					if(i != j)
					{
						StringValue strVal0 = new StringValue();
						strVal0.setData(String.valueOf((char)i));
						strVal0.setSort(TermsFactory.eINSTANCE.createUserSort());
						
						StringValue strVal1 = new StringValue();
						strVal1.setData(String.valueOf((char)j));
						strVal1.setSort(TermsFactory.eINSTANCE.createUserSort());
						
						ProductValue pValue = new ProductValue();
						pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
						pValue.getComponents().add(strVal0);
						pValue.getComponents().add(strVal1);
						
						messages.add(pValue);	
					}
				}
			}	
		}
		return messages;
	}
	
	public static List<AbstractValue> getMessagesBySender(AbstractValue sender)
	{
		List<AbstractValue> messages = new ArrayList<AbstractValue>();
		
		for(AbstractValue pValue : getMessages())
		{
			StringValue s = (StringValue)((ProductValue)pValue).getComponents().get(0);
			if(s.getData().equals(((StringValue)sender).getData()))
			{
				messages.add(pValue);
			}
		}
		return messages;
	}
	
	public static List<AbstractValue> getMessagesByReceiver(AbstractValue receiver)
	{
		List<AbstractValue> messages = new ArrayList<AbstractValue>();
		
		for(AbstractValue pValue : getMessages())
		{
			StringValue r = (StringValue)((ProductValue)pValue).getComponents().get(1);
			if(r.getData().equals(((StringValue)receiver).getData()))
			{
				messages.add(pValue);
			}
		}
		return messages;
	}
	
}
