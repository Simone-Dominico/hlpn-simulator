/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Firing Mode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link transitionruntime.FiringMode#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see transitionruntime.TransitionruntimePackage#getFiringMode()
 * @model
 * @generated
 */
public interface FiringMode extends EObject
{
    /**
     * Returns the value of the '<em><b>Values</b></em>' containment reference list.
     * The list contents are of type {@link transitionruntime.FiringData}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Values</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Values</em>' containment reference list.
     * @see transitionruntime.TransitionruntimePackage#getFiringMode_Values()
     * @model containment="true"
     * @generated
     */
    EList<FiringData> getValues();

} // FiringMode
