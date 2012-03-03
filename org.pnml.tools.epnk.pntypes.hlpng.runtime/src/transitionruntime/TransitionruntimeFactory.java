/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see transitionruntime.TransitionruntimePackage
 * @generated
 */
public interface TransitionruntimeFactory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    TransitionruntimeFactory eINSTANCE = transitionruntime.impl.TransitionruntimeFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Transition Marking</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Transition Marking</em>'.
     * @generated
     */
    TransitionMarking createTransitionMarking();

    /**
     * Returns a new object of class '<em>Firing Mode</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Firing Mode</em>'.
     * @generated
     */
    FiringMode createFiringMode();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    TransitionruntimePackage getTransitionruntimePackage();

} //TransitionruntimeFactory
