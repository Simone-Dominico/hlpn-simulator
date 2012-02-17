/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package productRuntime;

import org.eclipse.emf.common.util.EList;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.ProductSort;

import runtime.AbstractValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link productRuntime.ProductValue#getProdSort <em>Prod Sort</em>}</li>
 *   <li>{@link productRuntime.ProductValue#getComponents <em>Components</em>}</li>
 * </ul>
 * </p>
 *
 * @see productRuntime.ProductruntimePackage#getProductValue()
 * @model
 * @generated
 */
public interface ProductValue extends AbstractValue
{
    /**
     * Returns the value of the '<em><b>Prod Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Prod Sort</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Prod Sort</em>' reference.
     * @see #setProdSort(ProductSort)
     * @see productRuntime.ProductruntimePackage#getProductValue_ProdSort()
     * @model required="true"
     * @generated
     */
    ProductSort getProdSort();

    /**
     * Sets the value of the '{@link productRuntime.ProductValue#getProdSort <em>Prod Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Prod Sort</em>' reference.
     * @see #getProdSort()
     * @generated
     */
    void setProdSort(ProductSort value);

    /**
     * Returns the value of the '<em><b>Components</b></em>' containment reference list.
     * The list contents are of type {@link runtime.AbstractValue}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Components</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Components</em>' containment reference list.
     * @see productRuntime.ProductruntimePackage#getProductValue_Components()
     * @model containment="true"
     * @generated
     */
    EList<AbstractValue> getComponents();

} // ProductValue
