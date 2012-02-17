/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MS Element Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link runtime.MSElementValue#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link runtime.MSElementValue#getMsElement <em>Ms Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see runtime.RuntimePackage#getMSElementValue()
 * @model
 * @generated
 */
public interface MSElementValue extends AbstractValue
{
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
     * @see runtime.RuntimePackage#getMSElementValue_Multiplicity()
     * @model required="true"
     * @generated
     */
    int getMultiplicity();

    /**
     * Sets the value of the '{@link runtime.MSElementValue#getMultiplicity <em>Multiplicity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Multiplicity</em>' attribute.
     * @see #getMultiplicity()
     * @generated
     */
    void setMultiplicity(int value);

    /**
     * Returns the value of the '<em><b>Ms Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ms Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ms Element</em>' reference.
     * @see #setMsElement(AbstractValue)
     * @see runtime.RuntimePackage#getMSElementValue_MsElement()
     * @model required="true"
     * @generated
     */
    AbstractValue getMsElement();

    /**
     * Sets the value of the '{@link runtime.MSElementValue#getMsElement <em>Ms Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ms Element</em>' reference.
     * @see #getMsElement()
     * @generated
     */
    void setMsElement(AbstractValue value);

} // MSElementValue
