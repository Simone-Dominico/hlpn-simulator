package org.pnml.tools.epnk.applications.hlpng.runtime.operations.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
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

			initial.getValues().put(key, multiplicity);
		}
		
    	MSValue testSet = AbstractValueMath.lightCopy(initial);
    	
    	assertEquals(testSet.getSort(), initial.getSort());
    	assertEquals(initial.getValues().size(), 1);
    	assertEquals(testSet.getValues().size(), 1);
  
    	{
    		Collection<Integer> initValues = initial.getValues().values();
        	List<Integer> initListValues = new ArrayList<Integer>(initValues);
        	
        	Collection<Integer> testValues = testSet.getValues().values();
        	List<Integer> testListValues = new ArrayList<Integer>(testValues);
        	
        	assertEquals(initListValues.get(0), testListValues.get(0));
    	}
    	{
    		initial.getValues().clear();
        	assertEquals(initial.getValues().size(), 0);
        	
        	assertEquals(testSet.getValues().size(), 1);
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
        assertEquals(multiplicity.getN(), 
        		(int)AbstractValueMath.getMultiplicity(mainMs, intValue9));
    }
	
	@Test
    public void testAdd__AbstractValue_int()
    {
    	// simple number
    	{
    		MSValue test = AbstractValueMath.add(mainMs, tmpValue10, 1);
    		
            assertEquals(1, (int)AbstractValueMath.getMultiplicity(test, tmpValue10));
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
    		

    		assertNotNull(AbstractValueMath.getMultiplicity(msValue, pTest1));
    		assertEquals(3, (int)AbstractValueMath.getMultiplicity(msValue, pTest1));
    		assertEquals(1, (int)AbstractValueMath.getMultiplicity(msValue, pTest2));
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
        		assertNotNull(AbstractValueMath.getMultiplicity(msValue, test1));
        		assertEquals(4, (int)AbstractValueMath.getMultiplicity(msValue, test1));
    		}
    		{
        		MSValue v3 = createMSValue(10, 15);
        		msValue = AbstractValueMath.add(msValue, v3, 5);
        		
        		MSValue test2 = createMSValue(10, 15);

        		assertEquals(5, (int)AbstractValueMath.getMultiplicity(msValue, test2));	
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
    			
    			assertEquals(3, (int)AbstractValueMath.getMultiplicity(msValue, pValue));
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
		
    	MSValue test = AbstractValueMath.append(mainMs, msAppend);
  
    	{
    		IntValue intAppend9 = new IntValue();
        	intAppend9.setN(9);
        	intAppend9.setSort(IntegersFactory.eINSTANCE.createInteger());
        	assertTrue(9 == (int)AbstractValueMath.getMultiplicity(test, intAppend9));
    	}
    	
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
