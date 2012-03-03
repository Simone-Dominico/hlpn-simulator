/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime;

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
 * @see transitionruntime.TransitionruntimeFactory
 * @model kind="package"
 * @generated
 */
public interface TransitionruntimePackage extends EPackage
{
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "transitionruntime";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://transitionruntime/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "transitionruntime";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    TransitionruntimePackage eINSTANCE = transitionruntime.impl.TransitionruntimePackageImpl.init();

    /**
     * The meta object id for the '{@link transitionruntime.impl.TransitionMarkingImpl <em>Transition Marking</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see transitionruntime.impl.TransitionMarkingImpl
     * @see transitionruntime.impl.TransitionruntimePackageImpl#getTransitionMarking()
     * @generated
     */
    int TRANSITION_MARKING = 0;

    /**
     * The feature id for the '<em><b>Net Annotations</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION_MARKING__NET_ANNOTATIONS = NetannotationsPackage.OBJECT_ANNOTATION__NET_ANNOTATIONS;

    /**
     * The feature id for the '<em><b>Object</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION_MARKING__OBJECT = NetannotationsPackage.OBJECT_ANNOTATION__OBJECT;

    /**
     * The feature id for the '<em><b>Transition</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION_MARKING__TRANSITION = NetannotationsPackage.OBJECT_ANNOTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Modes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION_MARKING__MODES = NetannotationsPackage.OBJECT_ANNOTATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Transition Marking</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TRANSITION_MARKING_FEATURE_COUNT = NetannotationsPackage.OBJECT_ANNOTATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link transitionruntime.impl.FiringModeImpl <em>Firing Mode</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see transitionruntime.impl.FiringModeImpl
     * @see transitionruntime.impl.TransitionruntimePackageImpl#getFiringMode()
     * @generated
     */
    int FIRING_MODE = 1;

    /**
     * The feature id for the '<em><b>Values</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIRING_MODE__VALUES = 0;

    /**
     * The number of structural features of the '<em>Firing Mode</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIRING_MODE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link transitionruntime.impl.PlaceMarkingToValueMapImpl <em>Place Marking To Value Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see transitionruntime.impl.PlaceMarkingToValueMapImpl
     * @see transitionruntime.impl.TransitionruntimePackageImpl#getPlaceMarkingToValueMap()
     * @generated
     */
    int PLACE_MARKING_TO_VALUE_MAP = 2;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLACE_MARKING_TO_VALUE_MAP__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLACE_MARKING_TO_VALUE_MAP__VALUE = 1;

    /**
     * The number of structural features of the '<em>Place Marking To Value Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLACE_MARKING_TO_VALUE_MAP_FEATURE_COUNT = 2;


    /**
     * Returns the meta object for class '{@link transitionruntime.TransitionMarking <em>Transition Marking</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Transition Marking</em>'.
     * @see transitionruntime.TransitionMarking
     * @generated
     */
    EClass getTransitionMarking();

    /**
     * Returns the meta object for the reference '{@link transitionruntime.TransitionMarking#getTransition <em>Transition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Transition</em>'.
     * @see transitionruntime.TransitionMarking#getTransition()
     * @see #getTransitionMarking()
     * @generated
     */
    EReference getTransitionMarking_Transition();

    /**
     * Returns the meta object for the containment reference list '{@link transitionruntime.TransitionMarking#getModes <em>Modes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Modes</em>'.
     * @see transitionruntime.TransitionMarking#getModes()
     * @see #getTransitionMarking()
     * @generated
     */
    EReference getTransitionMarking_Modes();

    /**
     * Returns the meta object for class '{@link transitionruntime.FiringMode <em>Firing Mode</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Firing Mode</em>'.
     * @see transitionruntime.FiringMode
     * @generated
     */
    EClass getFiringMode();

    /**
     * Returns the meta object for the map '{@link transitionruntime.FiringMode#getValues <em>Values</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Values</em>'.
     * @see transitionruntime.FiringMode#getValues()
     * @see #getFiringMode()
     * @generated
     */
    EReference getFiringMode_Values();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>Place Marking To Value Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Place Marking To Value Map</em>'.
     * @see java.util.Map.Entry
     * @model keyType="runtime.PlaceMarking" keyRequired="true"
     *        valueType="runtime.AbstractValue" valueRequired="true"
     * @generated
     */
    EClass getPlaceMarkingToValueMap();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getPlaceMarkingToValueMap()
     * @generated
     */
    EReference getPlaceMarkingToValueMap_Key();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getPlaceMarkingToValueMap()
     * @generated
     */
    EReference getPlaceMarkingToValueMap_Value();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TransitionruntimeFactory getTransitionruntimeFactory();

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
         * The meta object literal for the '{@link transitionruntime.impl.TransitionMarkingImpl <em>Transition Marking</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see transitionruntime.impl.TransitionMarkingImpl
         * @see transitionruntime.impl.TransitionruntimePackageImpl#getTransitionMarking()
         * @generated
         */
        EClass TRANSITION_MARKING = eINSTANCE.getTransitionMarking();

        /**
         * The meta object literal for the '<em><b>Transition</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRANSITION_MARKING__TRANSITION = eINSTANCE.getTransitionMarking_Transition();

        /**
         * The meta object literal for the '<em><b>Modes</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TRANSITION_MARKING__MODES = eINSTANCE.getTransitionMarking_Modes();

        /**
         * The meta object literal for the '{@link transitionruntime.impl.FiringModeImpl <em>Firing Mode</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see transitionruntime.impl.FiringModeImpl
         * @see transitionruntime.impl.TransitionruntimePackageImpl#getFiringMode()
         * @generated
         */
        EClass FIRING_MODE = eINSTANCE.getFiringMode();

        /**
         * The meta object literal for the '<em><b>Values</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FIRING_MODE__VALUES = eINSTANCE.getFiringMode_Values();

        /**
         * The meta object literal for the '{@link transitionruntime.impl.PlaceMarkingToValueMapImpl <em>Place Marking To Value Map</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see transitionruntime.impl.PlaceMarkingToValueMapImpl
         * @see transitionruntime.impl.TransitionruntimePackageImpl#getPlaceMarkingToValueMap()
         * @generated
         */
        EClass PLACE_MARKING_TO_VALUE_MAP = eINSTANCE.getPlaceMarkingToValueMap();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PLACE_MARKING_TO_VALUE_MAP__KEY = eINSTANCE.getPlaceMarkingToValueMap_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PLACE_MARKING_TO_VALUE_MAP__VALUE = eINSTANCE.getPlaceMarkingToValueMap_Value();

    }

} //TransitionruntimePackage
