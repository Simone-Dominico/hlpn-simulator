/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime;

import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;

import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Place Marking</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link runtime.PlaceMarking#getMsValue <em>Ms Value</em>}</li>
 *   <li>{@link runtime.PlaceMarking#getPlace <em>Place</em>}</li>
 * </ul>
 * </p>
 *
 * @see runtime.RuntimePackage#getPlaceMarking()
 * @model
 * @generated
 */
public interface PlaceMarking extends ObjectAnnotation
{
    /**
     * Returns the value of the '<em><b>Ms Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ms Value</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ms Value</em>' reference.
     * @see #setMsValue(MSValue)
     * @see runtime.RuntimePackage#getPlaceMarking_MsValue()
     * @model required="true"
     * @generated
     */
    MSValue getMsValue();

    /**
     * Sets the value of the '{@link runtime.PlaceMarking#getMsValue <em>Ms Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ms Value</em>' reference.
     * @see #getMsValue()
     * @generated
     */
    void setMsValue(MSValue value);

    /**
     * Returns the value of the '<em><b>Place</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Place</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Place</em>' reference.
     * @see #setPlace(Place)
     * @see runtime.RuntimePackage#getPlaceMarking_Place()
     * @model required="true"
     * @generated
     */
    Place getPlace();

    /**
     * Sets the value of the '{@link runtime.PlaceMarking#getPlace <em>Place</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Place</em>' reference.
     * @see #getPlace()
     * @generated
     */
    void setPlace(Place value);

} // PlaceMarking