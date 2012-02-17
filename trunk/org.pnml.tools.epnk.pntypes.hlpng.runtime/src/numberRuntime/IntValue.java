/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Int Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link numberRuntime.IntValue#getIntSort <em>Int Sort</em>}</li>
 * </ul>
 * </p>
 *
 * @see numberRuntime.NumberruntimePackage#getIntValue()
 * @model
 * @generated
 */
public interface IntValue extends NumberValue
{
    /**
     * Returns the value of the '<em><b>Int Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Int Sort</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Int Sort</em>' reference.
     * @see #setIntSort(org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Integer)
     * @see numberRuntime.NumberruntimePackage#getIntValue_IntSort()
     * @model required="true"
     * @generated
     */
    org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Integer getIntSort();

    /**
     * Sets the value of the '{@link numberRuntime.IntValue#getIntSort <em>Int Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Int Sort</em>' reference.
     * @see #getIntSort()
     * @generated
     */
    void setIntSort(org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Integer value);

} // IntValue
