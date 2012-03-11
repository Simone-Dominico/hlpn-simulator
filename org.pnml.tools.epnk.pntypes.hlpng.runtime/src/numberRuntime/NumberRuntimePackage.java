/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see numberRuntime.NumberRuntimeFactory
 * @model kind="package"
 * @generated
 */
public interface NumberRuntimePackage extends EPackage
{
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "numberRuntime";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://numberruntime/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "numberruntime";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    NumberRuntimePackage eINSTANCE = numberRuntime.impl.NumberRuntimePackageImpl.init();

    /**
     * The meta object id for the '{@link numberRuntime.impl.NumberValueImpl <em>Number Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see numberRuntime.impl.NumberValueImpl
     * @see numberRuntime.impl.NumberRuntimePackageImpl#getNumberValue()
     * @generated
     */
    int NUMBER_VALUE = 0;

    /**
     * The feature id for the '<em><b>Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NUMBER_VALUE__SORT = RuntimePackage.ABSTRACT_VALUE__SORT;

    /**
     * The feature id for the '<em><b>N</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NUMBER_VALUE__N = RuntimePackage.ABSTRACT_VALUE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Number Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NUMBER_VALUE_FEATURE_COUNT = RuntimePackage.ABSTRACT_VALUE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link numberRuntime.impl.IntValueImpl <em>Int Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see numberRuntime.impl.IntValueImpl
     * @see numberRuntime.impl.NumberRuntimePackageImpl#getIntValue()
     * @generated
     */
    int INT_VALUE = 1;

    /**
     * The feature id for the '<em><b>Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_VALUE__SORT = NUMBER_VALUE__SORT;

    /**
     * The feature id for the '<em><b>N</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_VALUE__N = NUMBER_VALUE__N;

    /**
     * The number of structural features of the '<em>Int Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_VALUE_FEATURE_COUNT = NUMBER_VALUE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link numberRuntime.impl.PosValueImpl <em>Pos Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see numberRuntime.impl.PosValueImpl
     * @see numberRuntime.impl.NumberRuntimePackageImpl#getPosValue()
     * @generated
     */
    int POS_VALUE = 2;

    /**
     * The feature id for the '<em><b>Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POS_VALUE__SORT = NUMBER_VALUE__SORT;

    /**
     * The feature id for the '<em><b>N</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POS_VALUE__N = NUMBER_VALUE__N;

    /**
     * The number of structural features of the '<em>Pos Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POS_VALUE_FEATURE_COUNT = NUMBER_VALUE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link numberRuntime.impl.NatValueImpl <em>Nat Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see numberRuntime.impl.NatValueImpl
     * @see numberRuntime.impl.NumberRuntimePackageImpl#getNatValue()
     * @generated
     */
    int NAT_VALUE = 3;

    /**
     * The feature id for the '<em><b>Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAT_VALUE__SORT = NUMBER_VALUE__SORT;

    /**
     * The feature id for the '<em><b>N</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAT_VALUE__N = NUMBER_VALUE__N;

    /**
     * The number of structural features of the '<em>Nat Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAT_VALUE_FEATURE_COUNT = NUMBER_VALUE_FEATURE_COUNT + 0;


    /**
     * Returns the meta object for class '{@link numberRuntime.NumberValue <em>Number Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Number Value</em>'.
     * @see numberRuntime.NumberValue
     * @generated
     */
    EClass getNumberValue();

    /**
     * Returns the meta object for the attribute '{@link numberRuntime.NumberValue#getN <em>N</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>N</em>'.
     * @see numberRuntime.NumberValue#getN()
     * @see #getNumberValue()
     * @generated
     */
    EAttribute getNumberValue_N();

    /**
     * Returns the meta object for class '{@link numberRuntime.IntValue <em>Int Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Int Value</em>'.
     * @see numberRuntime.IntValue
     * @generated
     */
    EClass getIntValue();

    /**
     * Returns the meta object for class '{@link numberRuntime.PosValue <em>Pos Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Pos Value</em>'.
     * @see numberRuntime.PosValue
     * @generated
     */
    EClass getPosValue();

    /**
     * Returns the meta object for class '{@link numberRuntime.NatValue <em>Nat Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Nat Value</em>'.
     * @see numberRuntime.NatValue
     * @generated
     */
    EClass getNatValue();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    NumberRuntimeFactory getNumberRuntimeFactory();

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
         * The meta object literal for the '{@link numberRuntime.impl.NumberValueImpl <em>Number Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see numberRuntime.impl.NumberValueImpl
         * @see numberRuntime.impl.NumberRuntimePackageImpl#getNumberValue()
         * @generated
         */
        EClass NUMBER_VALUE = eINSTANCE.getNumberValue();

        /**
         * The meta object literal for the '<em><b>N</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute NUMBER_VALUE__N = eINSTANCE.getNumberValue_N();

        /**
         * The meta object literal for the '{@link numberRuntime.impl.IntValueImpl <em>Int Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see numberRuntime.impl.IntValueImpl
         * @see numberRuntime.impl.NumberRuntimePackageImpl#getIntValue()
         * @generated
         */
        EClass INT_VALUE = eINSTANCE.getIntValue();

        /**
         * The meta object literal for the '{@link numberRuntime.impl.PosValueImpl <em>Pos Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see numberRuntime.impl.PosValueImpl
         * @see numberRuntime.impl.NumberRuntimePackageImpl#getPosValue()
         * @generated
         */
        EClass POS_VALUE = eINSTANCE.getPosValue();

        /**
         * The meta object literal for the '{@link numberRuntime.impl.NatValueImpl <em>Nat Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see numberRuntime.impl.NatValueImpl
         * @see numberRuntime.impl.NumberRuntimePackageImpl#getNatValue()
         * @generated
         */
        EClass NAT_VALUE = eINSTANCE.getNatValue();

    }

} //NumberRuntimePackage
