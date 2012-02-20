/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package productRuntime;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see productRuntime.ProductruntimeFactory
 * @model kind="package"
 * @generated
 */
public interface ProductruntimePackage extends EPackage
{
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "productRuntime";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://productruntime/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "productruntime";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ProductruntimePackage eINSTANCE = productRuntime.impl.ProductruntimePackageImpl.init();

    /**
     * The meta object id for the '{@link productRuntime.impl.ProductValueImpl <em>Product Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see productRuntime.impl.ProductValueImpl
     * @see productRuntime.impl.ProductruntimePackageImpl#getProductValue()
     * @generated
     */
    int PRODUCT_VALUE = 0;

    /**
     * The feature id for the '<em><b>Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRODUCT_VALUE__SORT = RuntimePackage.ABSTRACT_VALUE__SORT;

    /**
     * The feature id for the '<em><b>Components</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRODUCT_VALUE__COMPONENTS = RuntimePackage.ABSTRACT_VALUE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Product Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PRODUCT_VALUE_FEATURE_COUNT = RuntimePackage.ABSTRACT_VALUE_FEATURE_COUNT + 1;


    /**
     * Returns the meta object for class '{@link productRuntime.ProductValue <em>Product Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Product Value</em>'.
     * @see productRuntime.ProductValue
     * @generated
     */
    EClass getProductValue();

    /**
     * Returns the meta object for the containment reference list '{@link productRuntime.ProductValue#getComponents <em>Components</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Components</em>'.
     * @see productRuntime.ProductValue#getComponents()
     * @see #getProductValue()
     * @generated
     */
    EReference getProductValue_Components();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ProductruntimeFactory getProductruntimeFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals
    {
        /**
         * The meta object literal for the '{@link productRuntime.impl.ProductValueImpl <em>Product Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see productRuntime.impl.ProductValueImpl
         * @see productRuntime.impl.ProductruntimePackageImpl#getProductValue()
         * @generated
         */
        EClass PRODUCT_VALUE = eINSTANCE.getProductValue();

        /**
         * The meta object literal for the '<em><b>Components</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PRODUCT_VALUE__COMPONENTS = eINSTANCE.getProductValue_Components();

    }

} //ProductruntimePackage
