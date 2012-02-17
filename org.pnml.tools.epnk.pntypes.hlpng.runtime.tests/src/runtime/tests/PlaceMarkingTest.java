/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import runtime.PlaceMarking;
import runtime.RuntimeFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Place Marking</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PlaceMarkingTest extends TestCase
{

    /**
     * The fixture for this Place Marking test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PlaceMarking fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(PlaceMarkingTest.class);
    }

    /**
     * Constructs a new Place Marking test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PlaceMarkingTest(String name)
    {
        super(name);
    }

    /**
     * Sets the fixture for this Place Marking test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(PlaceMarking fixture)
    {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this Place Marking test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PlaceMarking getFixture()
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
    protected void setUp() throws Exception
    {
        setFixture(RuntimeFactory.eINSTANCE.createPlaceMarking());
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

} //PlaceMarkingTest
