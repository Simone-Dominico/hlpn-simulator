/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime;

import org.eclipse.emf.common.util.EMap;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.MultiSetSort;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MS Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link runtime.MSValue#getMsSort <em>Ms Sort</em>}</li>
 *   <li>{@link runtime.MSValue#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see runtime.RuntimePackage#getMSValue()
 * @model
 * @generated
 */
public interface MSValue extends AbstractValue
{
    /**
     * Returns the value of the '<em><b>Ms Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ms Sort</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ms Sort</em>' reference.
     * @see #setMsSort(MultiSetSort)
     * @see runtime.RuntimePackage#getMSValue_MsSort()
     * @model required="true"
     * @generated
     */
    MultiSetSort getMsSort();

    /**
     * Sets the value of the '{@link runtime.MSValue#getMsSort <em>Ms Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ms Sort</em>' reference.
     * @see #getMsSort()
     * @generated
     */
    void setMsSort(MultiSetSort value);

    /**
     * Returns the value of the '<em><b>Values</b></em>' map.
     * The key is of type {@link runtime.AbstractValue},
     * and the value is of type {@link java.lang.Integer},
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Values</em>' map isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Values</em>' map.
     * @see runtime.RuntimePackage#getMSValue_Values()
     * @model mapType="runtime.ValueToIntegerMap<runtime.AbstractValue, org.eclipse.emf.ecore.EIntegerObject>"
     * @generated
     */
    EMap<AbstractValue, Integer> getValues();

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    int getMultiplicity(AbstractValue value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    void add(AbstractValue value, int multiplicity);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     */
    void append(MSValue ms);

} // MSValue
