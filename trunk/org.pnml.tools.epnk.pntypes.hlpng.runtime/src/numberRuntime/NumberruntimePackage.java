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
 * @see numberRuntime.NumberruntimeFactory
 * @model kind="package"
 * @generated
 */
public interface NumberruntimePackage extends EPackage
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
    NumberruntimePackage eINSTANCE = numberRuntime.impl.NumberruntimePackageImpl.init();

    /**
     * The meta object id for the '{@link numberRuntime.impl.NumberValueImpl <em>Number Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see numberRuntime.impl.NumberValueImpl
     * @see numberRuntime.impl.NumberruntimePackageImpl#getNumberValue()
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
     * The feature id for the '<em><b>Num Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NUMBER_VALUE__NUM_SORT = RuntimePackage.ABSTRACT_VALUE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Number Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NUMBER_VALUE_FEATURE_COUNT = RuntimePackage.ABSTRACT_VALUE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link numberRuntime.impl.IntValueImpl <em>Int Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see numberRuntime.impl.IntValueImpl
     * @see numberRuntime.impl.NumberruntimePackageImpl#getIntValue()
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
     * The feature id for the '<em><b>Num Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_VALUE__NUM_SORT = NUMBER_VALUE__NUM_SORT;

    /**
     * The feature id for the '<em><b>Int Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_VALUE__INT_SORT = NUMBER_VALUE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Int Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INT_VALUE_FEATURE_COUNT = NUMBER_VALUE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link numberRuntime.impl.PosValueImpl <em>Pos Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see numberRuntime.impl.PosValueImpl
     * @see numberRuntime.impl.NumberruntimePackageImpl#getPosValue()
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
     * The feature id for the '<em><b>Num Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POS_VALUE__NUM_SORT = NUMBER_VALUE__NUM_SORT;

    /**
     * The feature id for the '<em><b>Pos Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POS_VALUE__POS_SORT = NUMBER_VALUE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Pos Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int POS_VALUE_FEATURE_COUNT = NUMBER_VALUE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link numberRuntime.impl.NatValueImpl <em>Nat Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see numberRuntime.impl.NatValueImpl
     * @see numberRuntime.impl.NumberruntimePackageImpl#getNatValue()
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
     * The feature id for the '<em><b>Num Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAT_VALUE__NUM_SORT = NUMBER_VALUE__NUM_SORT;

    /**
     * The feature id for the '<em><b>Nat Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAT_VALUE__NAT_SORT = NUMBER_VALUE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Nat Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAT_VALUE_FEATURE_COUNT = NUMBER_VALUE_FEATURE_COUNT + 1;


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
     * Returns the meta object for the reference '{@link numberRuntime.NumberValue#getNumSort <em>Num Sort</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Num Sort</em>'.
     * @see numberRuntime.NumberValue#getNumSort()
     * @see #getNumberValue()
     * @generated
     */
    EReference getNumberValue_NumSort();

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
     * Returns the meta object for the reference '{@link numberRuntime.IntValue#getIntSort <em>Int Sort</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Int Sort</em>'.
     * @see numberRuntime.IntValue#getIntSort()
     * @see #getIntValue()
     * @generated
     */
    EReference getIntValue_IntSort();

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
     * Returns the meta object for the reference '{@link numberRuntime.PosValue#getPosSort <em>Pos Sort</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Pos Sort</em>'.
     * @see numberRuntime.PosValue#getPosSort()
     * @see #getPosValue()
     * @generated
     */
    EReference getPosValue_PosSort();

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
     * Returns the meta object for the reference '{@link numberRuntime.NatValue#getNatSort <em>Nat Sort</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Nat Sort</em>'.
     * @see numberRuntime.NatValue#getNatSort()
     * @see #getNatValue()
     * @generated
     */
    EReference getNatValue_NatSort();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    NumberruntimeFactory getNumberruntimeFactory();

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
         * @see numberRuntime.impl.NumberruntimePackageImpl#getNumberValue()
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
         * The meta object literal for the '<em><b>Num Sort</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NUMBER_VALUE__NUM_SORT = eINSTANCE.getNumberValue_NumSort();

        /**
         * The meta object literal for the '{@link numberRuntime.impl.IntValueImpl <em>Int Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see numberRuntime.impl.IntValueImpl
         * @see numberRuntime.impl.NumberruntimePackageImpl#getIntValue()
         * @generated
         */
        EClass INT_VALUE = eINSTANCE.getIntValue();

        /**
         * The meta object literal for the '<em><b>Int Sort</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference INT_VALUE__INT_SORT = eINSTANCE.getIntValue_IntSort();

        /**
         * The meta object literal for the '{@link numberRuntime.impl.PosValueImpl <em>Pos Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see numberRuntime.impl.PosValueImpl
         * @see numberRuntime.impl.NumberruntimePackageImpl#getPosValue()
         * @generated
         */
        EClass POS_VALUE = eINSTANCE.getPosValue();

        /**
         * The meta object literal for the '<em><b>Pos Sort</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference POS_VALUE__POS_SORT = eINSTANCE.getPosValue_PosSort();

        /**
         * The meta object literal for the '{@link numberRuntime.impl.NatValueImpl <em>Nat Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see numberRuntime.impl.NatValueImpl
         * @see numberRuntime.impl.NumberruntimePackageImpl#getNatValue()
         * @generated
         */
        EClass NAT_VALUE = eINSTANCE.getNatValue();

        /**
         * The meta object literal for the '<em><b>Nat Sort</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NAT_VALUE__NAT_SORT = eINSTANCE.getNatValue_NatSort();

    }

} //NumberruntimePackage
