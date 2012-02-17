/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Positive;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pos Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link numberRuntime.PosValue#getPosSort <em>Pos Sort</em>}</li>
 * </ul>
 * </p>
 *
 * @see numberRuntime.NumberruntimePackage#getPosValue()
 * @model
 * @generated
 */
public interface PosValue extends NumberValue
{
    /**
     * Returns the value of the '<em><b>Pos Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pos Sort</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pos Sort</em>' reference.
     * @see #setPosSort(Positive)
     * @see numberRuntime.NumberruntimePackage#getPosValue_PosSort()
     * @model required="true"
     * @generated
     */
    Positive getPosSort();

    /**
     * Sets the value of the '{@link numberRuntime.PosValue#getPosSort <em>Pos Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pos Sort</em>' reference.
     * @see #getPosSort()
     * @generated
     */
    void setPosSort(Positive value);

} // PosValue
