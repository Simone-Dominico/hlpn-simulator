/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime;

import runtime.AbstractValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Number Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link numberRuntime.NumberValue#getN <em>N</em>}</li>
 * </ul>
 * </p>
 *
 * @see numberRuntime.NumberRuntimePackage#getNumberValue()
 * @model abstract="true"
 * @generated
 */
public interface NumberValue extends AbstractValue
{
    /**
     * Returns the value of the '<em><b>N</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>N</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>N</em>' attribute.
     * @see #setN(int)
     * @see numberRuntime.NumberRuntimePackage#getNumberValue_N()
     * @model required="true"
     *        extendedMetaData="namespace=''"
     * @generated
     */
    int getN();

    /**
     * Sets the value of the '{@link numberRuntime.NumberValue#getN <em>N</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>N</em>' attribute.
     * @see #getN()
     * @generated
     */
    void setN(int value);

} // NumberValue
