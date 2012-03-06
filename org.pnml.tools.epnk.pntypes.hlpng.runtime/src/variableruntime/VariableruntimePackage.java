/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package variableruntime;

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
 * @see variableruntime.VariableruntimeFactory
 * @model kind="package"
 * @generated
 */
public interface VariableruntimePackage extends EPackage
{
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "variableruntime";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://variableruntime/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "variableruntime";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    VariableruntimePackage eINSTANCE = variableruntime.impl.VariableruntimePackageImpl.init();

    /**
     * The meta object id for the '{@link variableruntime.impl.RuntimeVariableImpl <em>Runtime Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see variableruntime.impl.RuntimeVariableImpl
     * @see variableruntime.impl.VariableruntimePackageImpl#getRuntimeVariable()
     * @generated
     */
    int RUNTIME_VARIABLE = 0;

    /**
     * The feature id for the '<em><b>Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RUNTIME_VARIABLE__SORT = RuntimePackage.ABSTRACT_VALUE__SORT;

    /**
     * The feature id for the '<em><b>Variable</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RUNTIME_VARIABLE__VARIABLE = RuntimePackage.ABSTRACT_VALUE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Runtime Variable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RUNTIME_VARIABLE_FEATURE_COUNT = RuntimePackage.ABSTRACT_VALUE_FEATURE_COUNT + 1;


    /**
     * Returns the meta object for class '{@link variableruntime.RuntimeVariable <em>Runtime Variable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Runtime Variable</em>'.
     * @see variableruntime.RuntimeVariable
     * @generated
     */
    EClass getRuntimeVariable();

    /**
     * Returns the meta object for the reference '{@link variableruntime.RuntimeVariable#getVariable <em>Variable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Variable</em>'.
     * @see variableruntime.RuntimeVariable#getVariable()
     * @see #getRuntimeVariable()
     * @generated
     */
    EReference getRuntimeVariable_Variable();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    VariableruntimeFactory getVariableruntimeFactory();

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
         * The meta object literal for the '{@link variableruntime.impl.RuntimeVariableImpl <em>Runtime Variable</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see variableruntime.impl.RuntimeVariableImpl
         * @see variableruntime.impl.VariableruntimePackageImpl#getRuntimeVariable()
         * @generated
         */
        EClass RUNTIME_VARIABLE = eINSTANCE.getRuntimeVariable();
        /**
         * The meta object literal for the '<em><b>Variable</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RUNTIME_VARIABLE__VARIABLE = eINSTANCE.getRuntimeVariable_Variable();

    }

} //VariableruntimePackage
