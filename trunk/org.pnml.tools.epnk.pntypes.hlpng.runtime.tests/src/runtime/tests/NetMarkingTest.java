/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import runtime.NetMarking;
import runtime.RuntimeFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Net Marking</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class NetMarkingTest extends TestCase
{

    /**
     * The fixture for this Net Marking test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NetMarking fixture = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static void main(String[] args)
    {
        TestRunner.run(NetMarkingTest.class);
    }

    /**
     * Constructs a new Net Marking test case with the given name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NetMarkingTest(String name)
    {
        super(name);
    }

    /**
     * Sets the fixture for this Net Marking test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void setFixture(NetMarking fixture)
    {
        this.fixture = fixture;
    }

    /**
     * Returns the fixture for this Net Marking test case.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NetMarking getFixture()
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
        setFixture(RuntimeFactory.eINSTANCE.createNetMarking());
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

} //NetMarkingTest
