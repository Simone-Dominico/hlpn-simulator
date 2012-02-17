/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.tests;

import java.util.Map;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import runtime.AbstractValue;
import runtime.RuntimeFactory;
import runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Value To Integer Map</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ValueToIntegerMapTest extends TestCase
{

    /**
     * The fixture for this Value To Integer Map test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Map.Entry<AbstractValue, Integer> fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(ValueToIntegerMapTest.class);
    }

    /**
     * Constructs a new Value To Integer Map test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValueToIntegerMapTest(String name)
    {
        super(name);
    }

    /**
     * Sets the fixture for this Value To Integer Map test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(Map.Entry<AbstractValue, Integer> fixture)
    {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this Value To Integer Map test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Map.Entry<AbstractValue, Integer> getFixture()
    {
        return fixture;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void setUp() throws Exception
    {
        setFixture((Map.Entry<AbstractValue, Integer>)RuntimeFactory.eINSTANCE.create(RuntimePackage.Literals.VALUE_TO_INTEGER_MAP));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#tearDown()
     * @generated
     */
    @Override
    protected void tearDown() throws Exception
    {
        setFixture(null);
    }

} //ValueToIntegerMapTest
