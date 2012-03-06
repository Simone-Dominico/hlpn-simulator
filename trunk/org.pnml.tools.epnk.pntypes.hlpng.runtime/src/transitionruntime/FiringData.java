/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime;

import org.eclipse.emf.ecore.EObject;

import runtime.PlaceMarking;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Firing Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link transitionruntime.FiringData#getPlaceMarking <em>Place Marking</em>}</li>
 *   <li>{@link transitionruntime.FiringData#getMsTerm <em>Ms Term</em>}</li>
 * </ul>
 * </p>
 *
 * @see transitionruntime.TransitionruntimePackage#getFiringData()
 * @model
 * @generated
 */
public interface FiringData extends EObject
{
    /**
     * Returns the value of the '<em><b>Place Marking</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Place Marking</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Place Marking</em>' reference.
     * @see #setPlaceMarking(PlaceMarking)
     * @see transitionruntime.TransitionruntimePackage#getFiringData_PlaceMarking()
     * @model required="true"
     * @generated
     */
    PlaceMarking getPlaceMarking();

    /**
     * Sets the value of the '{@link transitionruntime.FiringData#getPlaceMarking <em>Place Marking</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Place Marking</em>' reference.
     * @see #getPlaceMarking()
     * @generated
     */
    void setPlaceMarking(PlaceMarking value);

    /**
     * Returns the value of the '<em><b>Ms Term</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ms Term</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ms Term</em>' reference.
     * @see #setMsTerm(MSTerm)
     * @see transitionruntime.TransitionruntimePackage#getFiringData_MsTerm()
     * @model required="true"
     * @generated
     */
    MSTerm getMsTerm();

    /**
     * Sets the value of the '{@link transitionruntime.FiringData#getMsTerm <em>Ms Term</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ms Term</em>' reference.
     * @see #getMsTerm()
     * @generated
     */
    void setMsTerm(MSTerm value);

} // FiringData
