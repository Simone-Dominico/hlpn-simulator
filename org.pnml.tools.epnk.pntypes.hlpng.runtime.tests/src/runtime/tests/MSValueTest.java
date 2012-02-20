/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.tests;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersFactory;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsFactory;

import productRuntime.ProductValue;
import productRuntime.ProductruntimeFactory;
import numberRuntime.IntValue;
import numberRuntime.NumberruntimeFactory;

import junit.textui.TestRunner;

import runtime.MSValue;
import runtime.RuntimeFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>MS Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link runtime.MSValue#getMultiplicity(runtime.AbstractValue) <em>Get Multiplicity</em>}</li>
 *   <li>{@link runtime.MSValue#add(runtime.AbstractValue, int) <em>Add</em>}</li>
 *   <li>{@link runtime.MSValue#append(runtime.MSValue) <em>Append</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class MSValueTest extends AbstractValueTest
{
	private int multiplicity = 8;
	private IntValue intValue9 = null;
	
	private MSValue msAppend = null;
	private IntValue intAppend9 = null;
	private IntValue intAppend19 = null;
	
	private IntValue tmpValue10 = null;
	
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(MSValueTest.class);
    }

    /**
     * Constructs a new MS Value test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MSValueTest(String name)
    {
        super(name);
    }

    /**
     * Returns the fixture for this MS Value test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    protected MSValue getFixture()
    {
        return (MSValue)fixture;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated NOT
     */
    @Override
    protected void setUp() throws Exception
    {
        setFixture(RuntimeFactory.eINSTANCE.createMSValue());
        {
        	intValue9 = NumberruntimeFactory.eINSTANCE.createIntValue();
            intValue9.setN(9);
            intValue9.setSort(IntegersFactory.eINSTANCE.createInteger());
        }
        getFixture().add(intValue9, multiplicity);
        
        {
        	msAppend = RuntimeFactory.eINSTANCE.createMSValue();
        	intAppend9 = NumberruntimeFactory.eINSTANCE.createIntValue();
        	intAppend9.setN(9);
        	intAppend9.setSort(IntegersFactory.eINSTANCE.createInteger());
        	msAppend.add(intAppend9, 1);
        	
        	intAppend19 = NumberruntimeFactory.eINSTANCE.createIntValue();
        	intAppend19.setN(19);
        	intAppend19.setSort(IntegersFactory.eINSTANCE.createInteger());
        	msAppend.add(intAppend19, 1);
        }
        {
        	tmpValue10 = NumberruntimeFactory.eINSTANCE.createIntValue();
        	tmpValue10.setN(10);
        	tmpValue10.setSort(IntegersFactory.eINSTANCE.createInteger());
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#tearDown()
     * @generated NOT
     */
    @Override
    protected void tearDown() throws Exception
    {
    	intValue9 = null;
    	
    	msAppend = null;
    	intAppend9 = null;
    	intAppend19 = null;
    	
    	tmpValue10 = null;

        setFixture(null);
    }

    /**
     * Tests the '{@link runtime.MSValue#getMultiplicity(runtime.AbstractValue) <em>Get Multiplicity</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see runtime.MSValue#getMultiplicity(runtime.AbstractValue)
     * @generated NOT
     */
    public void testGetMultiplicity__AbstractValue()
    {
        assertEquals(multiplicity, getFixture().getMultiplicity(intValue9));
    }

    /**
     * Tests the '{@link runtime.MSValue#add(runtime.AbstractValue, int) <em>Add</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see runtime.MSValue#add(runtime.AbstractValue, int)
     * @generated NOT
     */
    public void testAdd__AbstractValue_int()
    {
    	// simple number
    	{
    		getFixture().add(tmpValue10, 1);
            assertEquals(1, getFixture().getMultiplicity(tmpValue10));
    	}
    	// products
    	{
    		MSValue msValue = RuntimeFactory.eINSTANCE.createMSValue();
    		ProductValue p1 = createProductValue(5, 6);
    		ProductValue p2 = createProductValue(5, 6);
    		ProductValue p3 = createProductValue(7, 8);
    		
    		ProductValue pTest1 = createProductValue(5, 6);
    		ProductValue pTest2 = createProductValue(7, 8);
    		
    		msValue.add(p1, 1);
    		msValue.add(p2, 2);
    		msValue.add(p3, 1);
    		
    		assertEquals(3, msValue.getMultiplicity(pTest1));
    		assertEquals(1, msValue.getMultiplicity(pTest2));
    	}
    	// multisets of products
    	{
    		MSValue msValue = RuntimeFactory.eINSTANCE.createMSValue();
    		{
        		MSValue v1 = createMSValue(6, 5);
        		msValue.add(v1, 1);
        		MSValue v2 = createMSValue(6, 5);
        		msValue.add(v2, 2);
        		MSValue v22 = createMSValue(5, 6);
        		msValue.add(v22, 1);
        		
        		MSValue test1 = createMSValue(6, 5);
        		assertEquals(4, msValue.getMultiplicity(test1));
    		}
    		{
        		MSValue v3 = createMSValue(10, 15);
        		msValue.add(v3, 5);
        		
        		MSValue test2 = createMSValue(10, 15);

        		assertEquals(5, msValue.getMultiplicity(test2));	
    		}
    	}
    	// products of multisets of products
    	{
    		MSValue msValue = RuntimeFactory.eINSTANCE.createMSValue();
    		{
    			ProductValue pValue = ProductruntimeFactory.eINSTANCE.createProductValue();
    			pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
    			pValue.getComponents().add(createMSValue(4, 5));
    			msValue.add(pValue, 1);
    			
    		}
    		{
    			ProductValue pValue = ProductruntimeFactory.eINSTANCE.createProductValue();
    			pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
    			pValue.getComponents().add(createMSValue(4, 5));
    			msValue.add(pValue, 2);
    		}
    		{
    			ProductValue pValue = ProductruntimeFactory.eINSTANCE.createProductValue();
    			pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
    			pValue.getComponents().add(createMSValue(5, 4));
    			
    			assertEquals(3, msValue.getMultiplicity(pValue));
    			assertFalse(3 != msValue.getMultiplicity(pValue));
    		}
    	}
    }
    
    private static MSValue createMSValue(int a, int b)
    {
    	MSValue msValue = RuntimeFactory.eINSTANCE.createMSValue();
    	//msValue.setSort(TermsFactory.eINSTANCE.createMultiSetSort());
    	msValue.add(createProductValue(a, b), 1);
		msValue.add(createProductValue(b, a), 1);

		return msValue;
    }
    
    private static ProductValue createProductValue(int a, int b)
    {
    	ProductValue pValue = ProductruntimeFactory.eINSTANCE.createProductValue();
		pValue.setSort(TermsFactory.eINSTANCE.createProductSort());
		{
			IntValue v = NumberruntimeFactory.eINSTANCE.createIntValue();
			v.setN(a);
			pValue.getComponents().add(v);
		}
		{
			IntValue v = NumberruntimeFactory.eINSTANCE.createIntValue();
			v.setN(b);
			pValue.getComponents().add(v);
		}
		return pValue;
    }

    /**
     * Tests the '{@link runtime.MSValue#append(runtime.MSValue) <em>Append</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see runtime.MSValue#append(runtime.MSValue)
     * @generated NOT
     */
    public void testAppend__MSValue()
    {
    	getFixture().append(msAppend);
  
    	assertTrue(multiplicity + 1 == 
    			getFixture().getValues().get(intAppend9));
    }

} //MSValueTest
