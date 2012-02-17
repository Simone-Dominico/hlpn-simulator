/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime;

import org.eclipse.emf.ecore.EObject;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link runtime.AbstractValue#getSort <em>Sort</em>}</li>
 * </ul>
 * </p>
 *
 * @see runtime.RuntimePackage#getAbstractValue()
 * @model abstract="true"
 * @generated
 */
public interface AbstractValue extends EObject
{
    /**
     * Returns the value of the '<em><b>Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sort</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sort</em>' reference.
     * @see #setSort(Sort)
     * @see runtime.RuntimePackage#getAbstractValue_Sort()
     * @model required="true"
     * @generated
     */
    Sort getSort();

    /**
     * Sets the value of the '{@link runtime.AbstractValue#getSort <em>Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sort</em>' reference.
     * @see #getSort()
     * @generated
     */
    void setSort(Sort value);

} // AbstractValue
