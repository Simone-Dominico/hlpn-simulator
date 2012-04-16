package org.pnml.tools.epnk.applications.hlpng.network.mindist;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.StringValue;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IEvaluator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Operator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.UserOperator;

public class NFunction implements IEvaluator
{
	private int[][] graph = null;
	
	public NFunction()
	{
		this.graph = read("/home/mindaugas/Dropbox/DTU/master-project/workspace/org.pnml.tools.epnk.applications.hlpng.simulator/min-dist.graph");
	}
	
	@Override
    public AbstractValue evaluate(Collection<AbstractValue> values, Operator operator)
    {
		List<AbstractValue> list = new ArrayList<AbstractValue>(values);
		int node = ((StringValue)list.get(0)).getData().charAt(0) - 'A';
		
		UserOperator uOp = (UserOperator) operator;
		
		MSValue msValue = new MSValue();
		msValue.setSort(uOp.getOutputSort());
		
		for(int i = 0; i < graph.length; i++)
		{
			if(graph[node][i] != -1)
			{
				ProductValue pValue = new ProductValue();
				pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
				{
					StringValue strValue = new StringValue();
					strValue.setData(String.valueOf((char)(i + 'A')));
					strValue.setSort(TermsFactory.eINSTANCE.createUserSort());
					
					pValue.getComponents().add(strValue);
				}
				pValue.getComponents().add(list.get(1));	
				
				msValue.getValues().put(pValue, 1);
			}
		}
		
	    return msValue;
    }
	
	private int[][] read(String path)
	{
		List<String> lines = new ArrayList<String>();
		try
		{
			FileInputStream fstream = new FileInputStream(path);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = null;
			while((strLine = br.readLine()) != null)
			{
				lines.add(strLine);
			}
			in.close();
		}
		catch(Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
		
		int[][] graph = new int[lines.size()][lines.size()];
		
		for(int i = 0; i < lines.size(); i++)
		{
			String line = lines.get(i);
			String[] parts = line.split("\\s+");
			
			for(int j = 0; j < parts.length; j++)
			{
				graph[i][j] = Integer.valueOf(parts[j]);
			}
		}
		return graph;
	}
}
