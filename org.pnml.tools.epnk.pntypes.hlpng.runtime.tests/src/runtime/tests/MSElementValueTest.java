/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.tests;

import junit.textui.TestRunner;

import runtime.MSElementValue;
import runtime.RuntimeFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>MS Element Value</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class MSElementValueTest extends AbstractValueTest
{

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(MSElementValueTest.class);
    }

    /**
     * Constructs a new MS Element Value test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MSElementValueTest(String name)
    {
        super(name);
    }

    /**
     * Returns the fixture for this MS Element Value test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected MSElementValue getFixture()
    {
        return (MSElementValue)fixture;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see junit.framework.TestCase#setUp()
     * @generated
     */
    @Override
    protected void setUp() throws Exception
    {
        setFixture(RuntimeFactory.eINSTANCE.createMSElementValue());
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

} //MSElementValueTest
