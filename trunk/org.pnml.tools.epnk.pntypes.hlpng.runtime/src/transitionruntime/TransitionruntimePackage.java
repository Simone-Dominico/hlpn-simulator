/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime;

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
     * The feature id for the '<em><b>Values</b></em>' containment reference list.
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
     * The meta object id for the '{@link transitionruntime.impl.FiringDataImpl <em>Firing Data</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see transitionruntime.impl.FiringDataImpl
     * @see transitionruntime.impl.TransitionruntimePackageImpl#getFiringData()
     * @generated
     */
    int FIRING_DATA = 2;

    /**
     * The feature id for the '<em><b>Place Marking</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIRING_DATA__PLACE_MARKING = 0;

    /**
     * The feature id for the '<em><b>Ms Term</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIRING_DATA__MS_TERM = 1;

    /**
     * The number of structural features of the '<em>Firing Data</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FIRING_DATA_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link transitionruntime.impl.MSTermImpl <em>MS Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see transitionruntime.impl.MSTermImpl
     * @see transitionruntime.impl.TransitionruntimePackageImpl#getMSTerm()
     * @generated
     */
    int MS_TERM = 3;

    /**
     * The feature id for the '<em><b>Place Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MS_TERM__PLACE_ID = 0;

    /**
     * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MS_TERM__MULTIPLICITY = 1;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MS_TERM__VALUE = 2;

    /**
     * The number of structural features of the '<em>MS Term</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MS_TERM_FEATURE_COUNT = 3;


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
     * Returns the meta object for the containment reference list '{@link transitionruntime.FiringMode#getValues <em>Values</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Values</em>'.
     * @see transitionruntime.FiringMode#getValues()
     * @see #getFiringMode()
     * @generated
     */
    EReference getFiringMode_Values();

    /**
     * Returns the meta object for class '{@link transitionruntime.FiringData <em>Firing Data</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Firing Data</em>'.
     * @see transitionruntime.FiringData
     * @generated
     */
    EClass getFiringData();

    /**
     * Returns the meta object for the reference '{@link transitionruntime.FiringData#getPlaceMarking <em>Place Marking</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Place Marking</em>'.
     * @see transitionruntime.FiringData#getPlaceMarking()
     * @see #getFiringData()
     * @generated
     */
    EReference getFiringData_PlaceMarking();

    /**
     * Returns the meta object for the reference '{@link transitionruntime.FiringData#getMsTerm <em>Ms Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Ms Term</em>'.
     * @see transitionruntime.FiringData#getMsTerm()
     * @see #getFiringData()
     * @generated
     */
    EReference getFiringData_MsTerm();

    /**
     * Returns the meta object for class '{@link transitionruntime.MSTerm <em>MS Term</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>MS Term</em>'.
     * @see transitionruntime.MSTerm
     * @generated
     */
    EClass getMSTerm();

    /**
     * Returns the meta object for the attribute '{@link transitionruntime.MSTerm#getPlaceId <em>Place Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Place Id</em>'.
     * @see transitionruntime.MSTerm#getPlaceId()
     * @see #getMSTerm()
     * @generated
     */
    EAttribute getMSTerm_PlaceId();

    /**
     * Returns the meta object for the attribute '{@link transitionruntime.MSTerm#getMultiplicity <em>Multiplicity</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Multiplicity</em>'.
     * @see transitionruntime.MSTerm#getMultiplicity()
     * @see #getMSTerm()
     * @generated
     */
    EAttribute getMSTerm_Multiplicity();

    /**
     * Returns the meta object for the reference '{@link transitionruntime.MSTerm#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Value</em>'.
     * @see transitionruntime.MSTerm#getValue()
     * @see #getMSTerm()
     * @generated
     */
    EReference getMSTerm_Value();

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
         * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FIRING_MODE__VALUES = eINSTANCE.getFiringMode_Values();

        /**
         * The meta object literal for the '{@link transitionruntime.impl.FiringDataImpl <em>Firing Data</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see transitionruntime.impl.FiringDataImpl
         * @see transitionruntime.impl.TransitionruntimePackageImpl#getFiringData()
         * @generated
         */
        EClass FIRING_DATA = eINSTANCE.getFiringData();

        /**
         * The meta object literal for the '<em><b>Place Marking</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FIRING_DATA__PLACE_MARKING = eINSTANCE.getFiringData_PlaceMarking();

        /**
         * The meta object literal for the '<em><b>Ms Term</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FIRING_DATA__MS_TERM = eINSTANCE.getFiringData_MsTerm();

        /**
         * The meta object literal for the '{@link transitionruntime.impl.MSTermImpl <em>MS Term</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see transitionruntime.impl.MSTermImpl
         * @see transitionruntime.impl.TransitionruntimePackageImpl#getMSTerm()
         * @generated
         */
        EClass MS_TERM = eINSTANCE.getMSTerm();

        /**
         * The meta object literal for the '<em><b>Place Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MS_TERM__PLACE_ID = eINSTANCE.getMSTerm_PlaceId();

        /**
         * The meta object literal for the '<em><b>Multiplicity</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute MS_TERM__MULTIPLICITY = eINSTANCE.getMSTerm_Multiplicity();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MS_TERM__VALUE = eINSTANCE.getMSTerm_Value();

    }

} //TransitionruntimePackage
