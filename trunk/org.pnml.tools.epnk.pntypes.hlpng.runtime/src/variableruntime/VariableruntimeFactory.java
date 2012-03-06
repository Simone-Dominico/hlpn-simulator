/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package variableruntime;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see variableruntime.VariableruntimePackage
 * @generated
 */
public interface VariableruntimeFactory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    VariableruntimeFactory eINSTANCE = variableruntime.impl.VariableruntimeFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Runtime Variable</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Runtime Variable</em>'.
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
    VariableruntimePackage getVariableruntimePackage();

} //VariableruntimeFactory
