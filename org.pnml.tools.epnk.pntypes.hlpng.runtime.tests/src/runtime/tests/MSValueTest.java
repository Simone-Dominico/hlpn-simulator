/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.tests;

import numberRuntime.IntValue;
import numberRuntime.NumberruntimeFactory;

import junit.textui.TestRunner;

import runtime.MSElementValue;
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
 *   <li>{@link runtime.MSValue#add(runtime.AbstractValue) <em>Add</em>}</li>
 *   <li>{@link runtime.MSValue#append(runtime.MSValue) <em>Append</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class MSValueTest extends AbstractValueTest
{
	private MSElementValue msElementValue = null;
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
        	msElementValue = RuntimeFactory.eINSTANCE.createMSElementValue();
        	msElementValue.setMultiplicity(8);
        	{
        		intValue9 = NumberruntimeFactory.eINSTANCE.createIntValue();
                intValue9.setN(9);
        	}
        	msElementValue.setMsElement(intValue9);
        }
        getFixture().add(msElementValue);
        
        {
        	msAppend = RuntimeFactory.eINSTANCE.createMSValue();
        	intAppend9 = NumberruntimeFactory.eINSTANCE.createIntValue();
        	intAppend9.setN(9);
        	msAppend.add(intAppend9);
        	
        	intAppend19 = NumberruntimeFactory.eINSTANCE.createIntValue();
        	intAppend19.setN(19);
        	msAppend.add(intAppend19);
        }
        {
        	tmpValue10 = NumberruntimeFactory.eINSTANCE.createIntValue();
        	tmpValue10.setN(10);
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
    	msElementValue = null;
    	
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
        assertEquals(msElementValue.getMultiplicity(),
        		getFixture().getMultiplicity(intValue9));
    }

    /**
     * Tests the '{@link runtime.MSValue#add(runtime.AbstractValue) <em>Add</em>}' operation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see runtime.MSValue#add(runtime.AbstractValue)
     * @generated NOT
     */
    public void testAdd__AbstractValue()
    {
        getFixture().add(tmpValue10);
        
        assertEquals(1, getFixture().getMultiplicity(tmpValue10));
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
    	
    	assertTrue(msElementValue.getMultiplicity() + 1 == 
    			getFixture().getValues().get(intAppend9));
    	
    	assertTrue(1 == getFixture().getValues().get(intAppend19));
    	System.err.println(getFixture().getValues().keySet().size());
    }

} //MSValueTest
