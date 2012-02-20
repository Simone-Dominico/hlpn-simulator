/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package productRuntime;

import org.eclipse.emf.common.util.EList;

import runtime.AbstractValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
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
