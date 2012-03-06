/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.pnml.tools.epnk.annotations.netannotations.NetannotationsPackage;

import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.HlpngdefinitionPackage;

import runtime.RuntimePackage;

import runtime.impl.RuntimePackageImpl;
import transitionruntime.FiringData;
import transitionruntime.FiringMode;
import transitionruntime.MSTerm;
import transitionruntime.TransitionMarking;
import transitionruntime.TransitionruntimeFactory;
import transitionruntime.TransitionruntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TransitionruntimePackageImpl extends EPackageImpl implements TransitionruntimePackage
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass transitionMarkingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass firingModeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass firingDataEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass msTermEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see transitionruntime.TransitionruntimePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TransitionruntimePackageImpl()
    {
        super(eNS_URI, TransitionruntimeFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link TransitionruntimePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TransitionruntimePackage init()
    {
        if (isInited) return (TransitionruntimePackage)EPackage.Registry.INSTANCE.getEPackage(TransitionruntimePackage.eNS_URI);

        // Obtain or create and register package
        TransitionruntimePackageImpl theTransitionruntimePackage = (TransitionruntimePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TransitionruntimePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TransitionruntimePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        HlpngdefinitionPackage.eINSTANCE.eClass();
        NetannotationsPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        RuntimePackageImpl theRuntimePackage = (RuntimePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RuntimePackage.eNS_URI) instanceof RuntimePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RuntimePackage.eNS_URI) : RuntimePackage.eINSTANCE);

        // Create package meta-data objects
        theTransitionruntimePackage.createPackageContents();
        theRuntimePackage.createPackageContents();

        // Initialize created meta-data
        theTransitionruntimePackage.initializePackageContents();
        theRuntimePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTransitionruntimePackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(TransitionruntimePackage.eNS_URI, theTransitionruntimePackage);
        return theTransitionruntimePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTransitionMarking()
    {
        return transitionMarkingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTransitionMarking_Transition()
    {
        return (EReference)transitionMarkingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTransitionMarking_Modes()
    {
        return (EReference)transitionMarkingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFiringMode()
    {
        return firingModeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFiringMode_Values()
    {
        return (EReference)firingModeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFiringData()
    {
        return firingDataEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFiringData_PlaceMarking()
    {
        return (EReference)firingDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFiringData_MsTerm()
    {
        return (EReference)firingDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMSTerm()
    {
        return msTermEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMSTerm_PlaceId()
    {
        return (EAttribute)msTermEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getMSTerm_Multiplicity()
    {
        return (EAttribute)msTermEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMSTerm_Value()
    {
        return (EReference)msTermEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransitionruntimeFactory getTransitionruntimeFactory()
    {
        return (TransitionruntimeFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents()
    {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        transitionMarkingEClass = createEClass(TRANSITION_MARKING);
        createEReference(transitionMarkingEClass, TRANSITION_MARKING__TRANSITION);
        createEReference(transitionMarkingEClass, TRANSITION_MARKING__MODES);

        firingModeEClass = createEClass(FIRING_MODE);
        createEReference(firingModeEClass, FIRING_MODE__VALUES);

        firingDataEClass = createEClass(FIRING_DATA);
        createEReference(firingDataEClass, FIRING_DATA__PLACE_MARKING);
        createEReference(firingDataEClass, FIRING_DATA__MS_TERM);

        msTermEClass = createEClass(MS_TERM);
        createEAttribute(msTermEClass, MS_TERM__PLACE_ID);
        createEAttribute(msTermEClass, MS_TERM__MULTIPLICITY);
        createEReference(msTermEClass, MS_TERM__VALUE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents()
    {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        NetannotationsPackage theNetannotationsPackage = (NetannotationsPackage)EPackage.Registry.INSTANCE.getEPackage(NetannotationsPackage.eNS_URI);
        HlpngdefinitionPackage theHlpngdefinitionPackage = (HlpngdefinitionPackage)EPackage.Registry.INSTANCE.getEPackage(HlpngdefinitionPackage.eNS_URI);
        RuntimePackage theRuntimePackage = (RuntimePackage)EPackage.Registry.INSTANCE.getEPackage(RuntimePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        transitionMarkingEClass.getESuperTypes().add(theNetannotationsPackage.getObjectAnnotation());

        // Initialize classes and features; add operations and parameters
        initEClass(transitionMarkingEClass, TransitionMarking.class, "TransitionMarking", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTransitionMarking_Transition(), theHlpngdefinitionPackage.getTransition(), null, "transition", null, 1, 1, TransitionMarking.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTransitionMarking_Modes(), this.getFiringMode(), null, "modes", null, 0, -1, TransitionMarking.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(firingModeEClass, FiringMode.class, "FiringMode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFiringMode_Values(), this.getFiringData(), null, "values", null, 0, -1, FiringMode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(firingDataEClass, FiringData.class, "FiringData", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFiringData_PlaceMarking(), theRuntimePackage.getPlaceMarking(), null, "placeMarking", null, 1, 1, FiringData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFiringData_MsTerm(), this.getMSTerm(), null, "msTerm", null, 1, 1, FiringData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(msTermEClass, MSTerm.class, "MSTerm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMSTerm_PlaceId(), ecorePackage.getEString(), "placeId", null, 0, 1, MSTerm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getMSTerm_Multiplicity(), ecorePackage.getEInt(), "multiplicity", null, 0, 1, MSTerm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMSTerm_Value(), theRuntimePackage.getAbstractValue(), null, "value", null, 1, 1, MSTerm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //TransitionruntimePackageImpl
