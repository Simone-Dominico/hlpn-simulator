/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime;

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
 *   <li>{@link runtime.PlaceMarking#isDirty <em>Dirty</em>}</li>
 * </ul>
 * </p>
 *
 * @see runtime.RuntimePackage#getPlaceMarking()
 * @model
 * @generated
 */
public interface PlaceMarking extends AbstractMarking
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

    /**
     * Returns the value of the '<em><b>Dirty</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dirty</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dirty</em>' attribute.
     * @see #setDirty(boolean)
     * @see runtime.RuntimePackage#getPlaceMarking_Dirty()
     * @model required="true"
     * @generated
     */
    boolean isDirty();

    /**
     * Sets the value of the '{@link runtime.PlaceMarking#isDirty <em>Dirty</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dirty</em>' attribute.
     * @see #isDirty()
     * @generated
     */
    void setDirty(boolean value);

} // PlaceMarking
