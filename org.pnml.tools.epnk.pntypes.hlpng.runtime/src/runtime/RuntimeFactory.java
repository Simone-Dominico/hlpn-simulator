/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see runtime.RuntimePackage
 * @generated
 */
public interface RuntimeFactory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    RuntimeFactory eINSTANCE = runtime.impl.RuntimeFactoryImpl.init();

    /**
     * Returns a new object of class '<em>MS Value</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>MS Value</em>'.
     * @generated
     */
    MSValue createMSValue();

    /**
     * Returns a new object of class '<em>Place Marking</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Place Marking</em>'.
     * @generated
     */
    PlaceMarking createPlaceMarking();

    /**
     * Returns a new object of class '<em>Net Marking</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Net Marking</em>'.
     * @generated
     */
    NetMarking createNetMarking();

    /**
     * Returns a new object of class '<em>Variable</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Variable</em>'.
     * @generated
     */
    RuntimeVariable createRuntimeVariable();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    RuntimePackage getRuntimePackage();

} //RuntimeFactory
