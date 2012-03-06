/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime;

import org.eclipse.emf.ecore.EObject;

import runtime.AbstractValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MS Term</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link transitionruntime.MSTerm#getPlaceId <em>Place Id</em>}</li>
 *   <li>{@link transitionruntime.MSTerm#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link transitionruntime.MSTerm#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see transitionruntime.TransitionruntimePackage#getMSTerm()
 * @model
 * @generated
 */
public interface MSTerm extends EObject
{
    /**
     * Returns the value of the '<em><b>Place Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Place Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Place Id</em>' attribute.
     * @see #setPlaceId(String)
     * @see transitionruntime.TransitionruntimePackage#getMSTerm_PlaceId()
     * @model
     * @generated
     */
    String getPlaceId();

    /**
     * Sets the value of the '{@link transitionruntime.MSTerm#getPlaceId <em>Place Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Place Id</em>' attribute.
     * @see #getPlaceId()
     * @generated
     */
    void setPlaceId(String value);

    /**
     * Returns the value of the '<em><b>Multiplicity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Multiplicity</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Multiplicity</em>' attribute.
     * @see #setMultiplicity(int)
     * @see transitionruntime.TransitionruntimePackage#getMSTerm_Multiplicity()
     * @model
     * @generated
     */
    int getMultiplicity();

    /**
     * Sets the value of the '{@link transitionruntime.MSTerm#getMultiplicity <em>Multiplicity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Multiplicity</em>' attribute.
     * @see #getMultiplicity()
     * @generated
     */
    void setMultiplicity(int value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' reference.
     * @see #setValue(AbstractValue)
     * @see transitionruntime.TransitionruntimePackage#getMSTerm_Value()
     * @model required="true"
     * @generated
     */
    AbstractValue getValue();

    /**
     * Sets the value of the '{@link transitionruntime.MSTerm#getValue <em>Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' reference.
     * @see #getValue()
     * @generated
     */
    void setValue(AbstractValue value);

} // MSTerm
