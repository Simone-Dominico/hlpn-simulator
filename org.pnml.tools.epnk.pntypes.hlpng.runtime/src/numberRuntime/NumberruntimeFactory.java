/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see numberRuntime.NumberruntimePackage
 * @generated
 */
public interface NumberruntimeFactory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    NumberruntimeFactory eINSTANCE = numberRuntime.impl.NumberruntimeFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Int Value</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Int Value</em>'.
     * @generated
     */
    IntValue createIntValue();

    /**
     * Returns a new object of class '<em>Pos Value</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Pos Value</em>'.
     * @generated
     */
    PosValue createPosValue();

    /**
     * Returns a new object of class '<em>Nat Value</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Nat Value</em>'.
     * @generated
     */
    NatValue createNatValue();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    NumberruntimePackage getNumberruntimePackage();

} //NumberruntimeFactory
