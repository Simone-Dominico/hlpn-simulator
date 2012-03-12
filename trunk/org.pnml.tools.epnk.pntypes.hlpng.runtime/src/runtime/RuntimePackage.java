/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.pnml.tools.epnk.annotations.netannotations.NetannotationsPackage;

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
 * @see runtime.RuntimeFactory
 * @model kind="package"
 * @generated
 */
public interface RuntimePackage extends EPackage
{
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "runtime";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://runtime/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "runtime";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    RuntimePackage eINSTANCE = runtime.impl.RuntimePackageImpl.init();

    /**
     * The meta object id for the '{@link runtime.impl.AbstractValueImpl <em>Abstract Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see runtime.impl.AbstractValueImpl
     * @see runtime.impl.RuntimePackageImpl#getAbstractValue()
     * @generated
     */
    int ABSTRACT_VALUE = 4;

    /**
     * The feature id for the '<em><b>Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_VALUE__SORT = 0;

    /**
     * The number of structural features of the '<em>Abstract Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_VALUE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link runtime.impl.MSValueImpl <em>MS Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see runtime.impl.MSValueImpl
     * @see runtime.impl.RuntimePackageImpl#getMSValue()
     * @generated
     */
    int MS_VALUE = 0;

    /**
     * The feature id for the '<em><b>Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MS_VALUE__SORT = ABSTRACT_VALUE__SORT;

    /**
     * The feature id for the '<em><b>Values</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MS_VALUE__VALUES = ABSTRACT_VALUE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>MS Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MS_VALUE_FEATURE_COUNT = ABSTRACT_VALUE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link runtime.impl.AbstractMarkingImpl <em>Abstract Marking</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see runtime.impl.AbstractMarkingImpl
     * @see runtime.impl.RuntimePackageImpl#getAbstractMarking()
     * @generated
     */
    int ABSTRACT_MARKING = 6;

    /**
     * The feature id for the '<em><b>Net Annotations</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_MARKING__NET_ANNOTATIONS = NetannotationsPackage.OBJECT_ANNOTATION__NET_ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Object</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_MARKING__OBJECT = NetannotationsPackage.OBJECT_ANNOTATION__OBJECT;

    /**
     * The number of structural features of the '<em>Abstract Marking</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_MARKING_FEATURE_COUNT = NetannotationsPackage.OBJECT_ANNOTATION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link runtime.impl.PlaceMarkingImpl <em>Place Marking</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see runtime.impl.PlaceMarkingImpl
     * @see runtime.impl.RuntimePackageImpl#getPlaceMarking()
     * @generated
     */
    int PLACE_MARKING = 1;

    /**
     * The feature id for the '<em><b>Net Annotations</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLACE_MARKING__NET_ANNOTATIONS = ABSTRACT_MARKING__NET_ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Object</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLACE_MARKING__OBJECT = ABSTRACT_MARKING__OBJECT;

    /**
     * The feature id for the '<em><b>Ms Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLACE_MARKING__MS_VALUE = ABSTRACT_MARKING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Place</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLACE_MARKING__PLACE = ABSTRACT_MARKING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Dirty</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLACE_MARKING__DIRTY = ABSTRACT_MARKING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Place Marking</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLACE_MARKING_FEATURE_COUNT = ABSTRACT_MARKING_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link runtime.impl.NetMarkingImpl <em>Net Marking</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see runtime.impl.NetMarkingImpl
     * @see runtime.impl.RuntimePackageImpl#getNetMarking()
     * @generated
     */
    int NET_MARKING = 2;

    /**
     * The feature id for the '<em><b>Net</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NET_MARKING__NET = NetannotationsPackage.NET_ANNOTATION__NET;

    /**
     * The feature id for the '<em><b>Object Annotations</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NET_MARKING__OBJECT_ANNOTATIONS = NetannotationsPackage.NET_ANNOTATION__OBJECT_ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Markings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NET_MARKING__MARKINGS = NetannotationsPackage.NET_ANNOTATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Net Marking</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NET_MARKING_FEATURE_COUNT = NetannotationsPackage.NET_ANNOTATION_FEATURE_COUNT + 1;


    /**
     * The meta object id for the '{@link runtime.impl.ValueToIntegerMapImpl <em>Value To Integer Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see runtime.impl.ValueToIntegerMapImpl
     * @see runtime.impl.RuntimePackageImpl#getValueToIntegerMap()
     * @generated
     */
    int VALUE_TO_INTEGER_MAP = 3;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_TO_INTEGER_MAP__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_TO_INTEGER_MAP__VALUE = 1;

    /**
     * The number of structural features of the '<em>Value To Integer Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_TO_INTEGER_MAP_FEATURE_COUNT = 2;


    /**
     * The meta object id for the '{@link runtime.impl.RuntimeVariableImpl <em>Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see runtime.impl.RuntimeVariableImpl
     * @see runtime.impl.RuntimePackageImpl#getRuntimeVariable()
     * @generated
     */
    int RUNTIME_VARIABLE = 5;

    /**
     * The feature id for the '<em><b>Sort</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RUNTIME_VARIABLE__SORT = ABSTRACT_VALUE__SORT;

    /**
     * The feature id for the '<em><b>Variable</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RUNTIME_VARIABLE__VARIABLE = ABSTRACT_VALUE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Variable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RUNTIME_VARIABLE_FEATURE_COUNT = ABSTRACT_VALUE_FEATURE_COUNT + 1;


    /**
     * Returns the meta object for class '{@link runtime.MSValue <em>MS Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>MS Value</em>'.
     * @see runtime.MSValue
     * @generated
     */
    EClass getMSValue();

    /**
     * Returns the meta object for the map '{@link runtime.MSValue#getValues <em>Values</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Values</em>'.
     * @see runtime.MSValue#getValues()
     * @see #getMSValue()
     * @generated
     */
    EReference getMSValue_Values();

    /**
     * Returns the meta object for class '{@link runtime.PlaceMarking <em>Place Marking</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Place Marking</em>'.
     * @see runtime.PlaceMarking
     * @generated
     */
    EClass getPlaceMarking();

    /**
     * Returns the meta object for the reference '{@link runtime.PlaceMarking#getMsValue <em>Ms Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Ms Value</em>'.
     * @see runtime.PlaceMarking#getMsValue()
     * @see #getPlaceMarking()
     * @generated
     */
    EReference getPlaceMarking_MsValue();

    /**
     * Returns the meta object for the reference '{@link runtime.PlaceMarking#getPlace <em>Place</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Place</em>'.
     * @see runtime.PlaceMarking#getPlace()
     * @see #getPlaceMarking()
     * @generated
     */
    EReference getPlaceMarking_Place();

    /**
     * Returns the meta object for the attribute '{@link runtime.PlaceMarking#isDirty <em>Dirty</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Dirty</em>'.
     * @see runtime.PlaceMarking#isDirty()
     * @see #getPlaceMarking()
     * @generated
     */
    EAttribute getPlaceMarking_Dirty();

    /**
     * Returns the meta object for class '{@link runtime.NetMarking <em>Net Marking</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Net Marking</em>'.
     * @see runtime.NetMarking
     * @generated
     */
    EClass getNetMarking();

    /**
     * Returns the meta object for the containment reference list '{@link runtime.NetMarking#getMarkings <em>Markings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Markings</em>'.
     * @see runtime.NetMarking#getMarkings()
     * @see #getNetMarking()
     * @generated
     */
    EReference getNetMarking_Markings();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>Value To Integer Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value To Integer Map</em>'.
     * @see java.util.Map.Entry
     * @model keyType="runtime.AbstractValue" keyRequired="true"
     *        valueType="runtime.AbstractValue" valueContainment="true" valueRequired="true" valueMany="true"
     * @generated
     */
    EClass getValueToIntegerMap();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getValueToIntegerMap()
     * @generated
     */
    EReference getValueToIntegerMap_Key();

    /**
     * Returns the meta object for the containment reference list '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getValueToIntegerMap()
     * @generated
     */
    EReference getValueToIntegerMap_Value();

    /**
     * Returns the meta object for class '{@link runtime.AbstractValue <em>Abstract Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Value</em>'.
     * @see runtime.AbstractValue
     * @generated
     */
    EClass getAbstractValue();

    /**
     * Returns the meta object for the reference '{@link runtime.AbstractValue#getSort <em>Sort</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Sort</em>'.
     * @see runtime.AbstractValue#getSort()
     * @see #getAbstractValue()
     * @generated
     */
    EReference getAbstractValue_Sort();

    /**
     * Returns the meta object for class '{@link runtime.RuntimeVariable <em>Variable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Variable</em>'.
     * @see runtime.RuntimeVariable
     * @generated
     */
    EClass getRuntimeVariable();

    /**
     * Returns the meta object for the reference '{@link runtime.RuntimeVariable#getVariable <em>Variable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Variable</em>'.
     * @see runtime.RuntimeVariable#getVariable()
     * @see #getRuntimeVariable()
     * @generated
     */
    EReference getRuntimeVariable_Variable();

    /**
     * Returns the meta object for class '{@link runtime.AbstractMarking <em>Abstract Marking</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Marking</em>'.
     * @see runtime.AbstractMarking
     * @generated
     */
    EClass getAbstractMarking();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    RuntimeFactory getRuntimeFactory();

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
         * The meta object literal for the '{@link runtime.impl.MSValueImpl <em>MS Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see runtime.impl.MSValueImpl
         * @see runtime.impl.RuntimePackageImpl#getMSValue()
         * @generated
         */
        EClass MS_VALUE = eINSTANCE.getMSValue();

        /**
         * The meta object literal for the '<em><b>Values</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MS_VALUE__VALUES = eINSTANCE.getMSValue_Values();

        /**
         * The meta object literal for the '{@link runtime.impl.PlaceMarkingImpl <em>Place Marking</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see runtime.impl.PlaceMarkingImpl
         * @see runtime.impl.RuntimePackageImpl#getPlaceMarking()
         * @generated
         */
        EClass PLACE_MARKING = eINSTANCE.getPlaceMarking();

        /**
         * The meta object literal for the '<em><b>Ms Value</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PLACE_MARKING__MS_VALUE = eINSTANCE.getPlaceMarking_MsValue();

        /**
         * The meta object literal for the '<em><b>Place</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PLACE_MARKING__PLACE = eINSTANCE.getPlaceMarking_Place();

        /**
         * The meta object literal for the '<em><b>Dirty</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute PLACE_MARKING__DIRTY = eINSTANCE.getPlaceMarking_Dirty();

        /**
         * The meta object literal for the '{@link runtime.impl.NetMarkingImpl <em>Net Marking</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see runtime.impl.NetMarkingImpl
         * @see runtime.impl.RuntimePackageImpl#getNetMarking()
         * @generated
         */
        EClass NET_MARKING = eINSTANCE.getNetMarking();

        /**
         * The meta object literal for the '<em><b>Markings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NET_MARKING__MARKINGS = eINSTANCE.getNetMarking_Markings();

        /**
         * The meta object literal for the '{@link runtime.impl.ValueToIntegerMapImpl <em>Value To Integer Map</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see runtime.impl.ValueToIntegerMapImpl
         * @see runtime.impl.RuntimePackageImpl#getValueToIntegerMap()
         * @generated
         */
        EClass VALUE_TO_INTEGER_MAP = eINSTANCE.getValueToIntegerMap();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VALUE_TO_INTEGER_MAP__KEY = eINSTANCE.getValueToIntegerMap_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VALUE_TO_INTEGER_MAP__VALUE = eINSTANCE.getValueToIntegerMap_Value();

        /**
         * The meta object literal for the '{@link runtime.impl.AbstractValueImpl <em>Abstract Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see runtime.impl.AbstractValueImpl
         * @see runtime.impl.RuntimePackageImpl#getAbstractValue()
         * @generated
         */
        EClass ABSTRACT_VALUE = eINSTANCE.getAbstractValue();

        /**
         * The meta object literal for the '<em><b>Sort</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_VALUE__SORT = eINSTANCE.getAbstractValue_Sort();

        /**
         * The meta object literal for the '{@link runtime.impl.RuntimeVariableImpl <em>Variable</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see runtime.impl.RuntimeVariableImpl
         * @see runtime.impl.RuntimePackageImpl#getRuntimeVariable()
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

        /**
         * The meta object literal for the '{@link runtime.impl.AbstractMarkingImpl <em>Abstract Marking</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see runtime.impl.AbstractMarkingImpl
         * @see runtime.impl.RuntimePackageImpl#getAbstractMarking()
         * @generated
         */
        EClass ABSTRACT_MARKING = eINSTANCE.getAbstractMarking();

    }

} //RuntimePackage
