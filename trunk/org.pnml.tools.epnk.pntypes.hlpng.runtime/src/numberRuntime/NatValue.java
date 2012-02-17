/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Natural;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nat Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link numberRuntime.NatValue#getNatSort <em>Nat Sort</em>}</li>
 * </ul>
 * </p>
 *
 * @see numberRuntime.NumberruntimePackage#getNatValue()
 * @model
 * @generated
 */
public interface NatValue extends NumberValue
{
    /**
     * Returns the value of the '<em><b>Nat Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Nat Sort</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Nat Sort</em>' reference.
     * @see #setNatSort(Natural)
     * @see numberRuntime.NumberruntimePackage#getNatValue_NatSort()
     * @model required="true"
     * @generated
     */
    Natural getNatSort();

    /**
     * Sets the value of the '{@link numberRuntime.NatValue#getNatSort <em>Nat Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nat Sort</em>' reference.
     * @see #getNatSort()
     * @generated
     */
    void setNatSort(Natural value);

} // NatValue
