/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime;

import org.eclipse.emf.common.util.EList;

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
 *   <li>{@link numberRuntime.NumberValue#getNumSort <em>Num Sort</em>}</li>
 * </ul>
 * </p>
 *
 * @see numberRuntime.NumberruntimePackage#getNumberValue()
 * @model abstract="true"
 * @generated
 */
public interface NumberValue extends AbstractValue
{
    /**
     * Returns the value of the '<em><b>N</b></em>' attribute list.
     * The list contents are of type {@link java.lang.Integer}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>N</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>N</em>' attribute list.
     * @see numberRuntime.NumberruntimePackage#getNumberValue_N()
     * @model extendedMetaData="namespace=''"
     * @generated
     */
    EList<Integer> getN();

    /**
     * Returns the value of the '<em><b>Num Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Num Sort</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Num Sort</em>' reference.
     * @see #setNumSort(org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number)
     * @see numberRuntime.NumberruntimePackage#getNumberValue_NumSort()
     * @model required="true"
     * @generated
     */
    org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number getNumSort();

    /**
     * Sets the value of the '{@link numberRuntime.NumberValue#getNumSort <em>Num Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Num Sort</em>' reference.
     * @see #getNumSort()
     * @generated
     */
    void setNumSort(org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number value);

} // NumberValue
