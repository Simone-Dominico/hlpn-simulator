/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import runtime.AbstractValue;
import runtime.PlaceMarking;

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
     * Returns the value of the '<em><b>Values</b></em>' map.
     * The key is of type {@link runtime.PlaceMarking},
     * and the value is of type {@link runtime.AbstractValue},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Values</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Values</em>' map.
     * @see transitionruntime.TransitionruntimePackage#getFiringMode_Values()
     * @model mapType="transitionruntime.PlaceMarkingToValueMap<runtime.PlaceMarking, runtime.AbstractValue>"
     * @generated
     */
    EMap<PlaceMarking, AbstractValue> getValues();

} // FiringMode
