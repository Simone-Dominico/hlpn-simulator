package org.pnml.tools.epnk.applications.hlpng.runtime.operations.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IMSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.IntValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.MSValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.PosValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.ProductValue;
import org.pnml.tools.epnk.applications.hlpng.runtime.operations.AbstractValueMath;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;

public class TestAbstractValueMath extends AbstractValueMath
{
	@Test
	public void testLightCopy()
    {
		AbstractValue key = null;
		MSValue initial = null;
		Integer multiplicity = 7;
		
		{
			initial = new MSValue();
			initial.setSort(TermsFactory.eINSTANCE.createMultiSetSort());
			
			key = new MSValue();

			initial.put(key, multiplicity);
		}
		
    	MSValue testSet = AbstractValueMath.lightCopy(initial);
    	
    	assertEquals(testSet.getSort(), initial.getSort());
    	assertEquals(initial.size(), 1);
    	assertEquals(testSet.size(), 1);
  
    	{
        	List<Entry<AbstractValue, Integer>> initListValues = 
        			new ArrayList<Entry<AbstractValue, Integer>>(initial.entrySet());

        	List<Entry<AbstractValue, Integer>> testListValues = 
        			new ArrayList<Entry<AbstractValue, Integer>>(testSet.entrySet());
        	
        	assertEquals(initListValues.get(0), testListValues.get(0));
    	}
    	{
    		initial.clear();
        	assertEquals(initial.size(), 0);
        	
        	assertEquals(testSet.size(), 1);
    	}
    }
	
	

	private MSValue mainMs = null;
	
	private PosValue multiplicity = null;
	private IntValue intValue9 = null;
	
	private MSValue msAppend = null;
	private IntValue intAppend9 = null;
	private IntValue intAppend19 = null;
	
	private IntValue tmpValue10 = null;
	
	@Before
	public void setUp() throws Exception
	{
		mainMs = new MSValue();
        {
        	multiplicity = new PosValue();
    		multiplicity.setN(8);
    		multiplicity.setSort(IntegersFactory.eINSTANCE.createPositive());
    		
        	intValue9 = new IntValue();
            intValue9.setN(9);
            intValue9.setSort(IntegersFactory.eINSTANCE.createInteger());
            
            mainMs = AbstractValueMath.add(mainMs, intValue9, multiplicity.getN());
        }
        
        msAppend = new MSValue();
        {
        	intAppend9 = new IntValue();
        	intAppend9.setN(9);
        	intAppend9.setSort(IntegersFactory.eINSTANCE.createInteger());
        	msAppend = AbstractValueMath.add(msAppend, intAppend9, 1);
        	
        	intAppend19 = new IntValue();
        	intAppend19.setN(19);
        	intAppend19.setSort(IntegersFactory.eINSTANCE.createInteger());
        	
        	msAppend = AbstractValueMath.add(msAppend, intAppend19, 1);
        }
        {
        	tmpValue10 = new IntValue();
        	tmpValue10.setN(10);
        	tmpValue10.setSort(IntegersFactory.eINSTANCE.createInteger());
        }
	}

	@After
	public void tearDown() throws Exception
	{
		multiplicity = null;
		
		intValue9 = null;
    	
    	msAppend = null;
    	intAppend9 = null;
    	intAppend19 = null;
    	
    	tmpValue10 = null;

    	mainMs = null;
	}

	@Test
	public void testGetMultiplicity()
    {
        assertEquals(multiplicity.getN(), getMultiplicity(mainMs, intValue9));
    }
	
	@Test
    public void testAdd__AbstractValue_int()
    {
    	// simple number
    	{
    		IMSValue test = AbstractValueMath.add(mainMs, tmpValue10, 1);
    		
            assertEquals(1, getMultiplicity(test, tmpValue10));
    	}
    	// products
    	{
    		MSValue msValue = new MSValue();
    		ProductValue p1 = createProductValue(5, 6);
    		ProductValue p2 = createProductValue(5, 6);
    		ProductValue p3 = createProductValue(7, 8);
    		
    		ProductValue pTest1 = createProductValue(5, 6);
    		ProductValue pTest2 = createProductValue(7, 8);
    		
    		msValue = AbstractValueMath.add(msValue, p1, 1);
    		msValue = AbstractValueMath.add(msValue, p2, 2);
    		msValue = AbstractValueMath.add(msValue, p3, 1);
    		

    		assertNotNull(getMultiplicity(msValue, pTest1));
    		assertEquals(3, getMultiplicity(msValue, pTest1));
    		assertEquals(1, getMultiplicity(msValue, pTest2));
    	}
    	// multisets of products
    	{
    		MSValue msValue = new MSValue();
    		{
        		MSValue v1 = createMSValue(6, 5);
        		msValue = AbstractValueMath.add(msValue, v1, 1);
        		MSValue v2 = createMSValue(6, 5);
        		msValue = AbstractValueMath.add(msValue, v2, 2);
        		MSValue v22 = createMSValue(5, 6);
        		msValue = AbstractValueMath.add(msValue, v22, 1);
        		
        		MSValue test1 = createMSValue(6, 5);
        		assertNotNull(getMultiplicity(msValue, test1));
        		assertEquals(4, getMultiplicity(msValue, test1));
    		}
    		{
        		MSValue v3 = createMSValue(10, 15);
        		msValue = AbstractValueMath.add(msValue, v3, 5);
        		
        		MSValue test2 = createMSValue(10, 15);

        		assertEquals(5, getMultiplicity(msValue, test2));	
    		}
    	}
    	// products of multisets of products
    	{
    		MSValue msValue = new MSValue();
    		{
    			ProductValue pValue = new ProductValue();
    			pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
    			pValue.getComponents().add(createMSValue(4, 5));
    			msValue = AbstractValueMath.add(msValue, pValue, 1);
    			
    		}
    		{
    			ProductValue pValue = new ProductValue();
    			pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
    			pValue.getComponents().add(createMSValue(4, 5));
    			msValue = AbstractValueMath.add(msValue, pValue, 2);
    		}
    		{
    			ProductValue pValue = new ProductValue();
    			pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
    			pValue.getComponents().add(createMSValue(5, 4));
    			
    			assertEquals(3, getMultiplicity(msValue, pValue));
    		}
    	}
    }
	@Test
    public void testAppend__MSValue()
    {
		MSValue mainMs = new MSValue();
		{
    		IntValue intValue9 = new IntValue();
            intValue9.setN(9);
            intValue9.setSort(IntegersFactory.eINSTANCE.createInteger());
            
            mainMs = AbstractValueMath.add(mainMs, intValue9, 8);
		}
		
		MSValue msAppend = new MSValue();
        {
        	IntValue intAppend9 = new IntValue();
        	intAppend9.setN(9);
        	intAppend9.setSort(IntegersFactory.eINSTANCE.createInteger());
        	msAppend = AbstractValueMath.add(msAppend, intAppend9, 1);
        	
        	IntValue intAppend19 = new IntValue();
        	intAppend19.setN(19);
        	intAppend19.setSort(IntegersFactory.eINSTANCE.createInteger());
        	msAppend = AbstractValueMath.add(msAppend, intAppend19, 1);
        }
		
    	IMSValue test = AbstractValueMath.append(mainMs, msAppend);
  
    	{
    		IntValue intAppend9 = new IntValue();
        	intAppend9.setN(9);
        	intAppend9.setSort(IntegersFactory.eINSTANCE.createInteger());
        	assertTrue(9 == getMultiplicity(test, intAppend9));
    	}
    	
    }
    
	private static int getMultiplicity(IMSValue set, AbstractValue value)
	{
		return set.get(value);
	}
	
    private static MSValue createMSValue(int a, int b)
    {
    	MSValue msValue = new MSValue();
    	//msValue.setSort(TermsFactory.eINSTANCE.createMultiSetSort());
    	msValue = AbstractValueMath.add(msValue, createProductValue(a, b), 1);
    	msValue = AbstractValueMath.add(msValue, createProductValue(b, a), 1);

		return msValue;
    }
    
    private static ProductValue createProductValue(int a, int b)
    {
    	ProductValue pValue = new ProductValue();
		pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
		{
			IntValue v = new IntValue();
			v.setN(a);
			pValue.getComponents().add(v);
		}
		{
			IntValue v = new IntValue();
			v.setN(b);
			pValue.getComponents().add(v);
		}
		return pValue;
    }

}
