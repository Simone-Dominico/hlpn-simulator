/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package productRuntime;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see productRuntime.ProductRuntimePackage
 * @generated
 */
public interface ProductRuntimeFactory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ProductRuntimeFactory eINSTANCE = productRuntime.impl.ProductRuntimeFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Product Value</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Product Value</em>'.
     * @generated
     */
    ProductValue createProductValue();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ProductRuntimePackage getProductRuntimePackage();

} //ProductRuntimeFactory
