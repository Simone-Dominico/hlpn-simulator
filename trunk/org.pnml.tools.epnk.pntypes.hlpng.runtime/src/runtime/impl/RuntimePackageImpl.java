/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.pnml.tools.epnk.annotations.netannotations.NetannotationsPackage;

import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.HlpngdefinitionPackage;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsPackage;

import runtime.AbstractMarking;
import runtime.AbstractValue;
import runtime.MSValue;
import runtime.NetMarking;
import runtime.PlaceMarking;
import runtime.RuntimeFactory;
import runtime.RuntimePackage;
import runtime.RuntimeVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RuntimePackageImpl extends EPackageImpl implements RuntimePackage
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass msValueEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass placeMarkingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass netMarkingEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass valueToIntegerMapEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractValueEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass runtimeVariableEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractMarkingEClass = null;

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
     * @see runtime.RuntimePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private RuntimePackageImpl()
    {
        super(eNS_URI, RuntimeFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link RuntimePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static RuntimePackage init()
    {
        if (isInited) return (RuntimePackage)EPackage.Registry.INSTANCE.getEPackage(RuntimePackage.eNS_URI);

        // Obtain or create and register package
        RuntimePackageImpl theRuntimePackage = (RuntimePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RuntimePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RuntimePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        HlpngdefinitionPackage.eINSTANCE.eClass();
        NetannotationsPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theRuntimePackage.createPackageContents();

        // Initialize created meta-data
        theRuntimePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theRuntimePackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(RuntimePackage.eNS_URI, theRuntimePackage);
        return theRuntimePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getMSValue()
    {
        return msValueEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getMSValue_Values()
    {
        return (EReference)msValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPlaceMarking()
    {
        return placeMarkingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPlaceMarking_MsValue()
    {
        return (EReference)placeMarkingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPlaceMarking_Place()
    {
        return (EReference)placeMarkingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPlaceMarking_Dirty()
    {
        return (EAttribute)placeMarkingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNetMarking()
    {
        return netMarkingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNetMarking_Markings()
    {
        return (EReference)netMarkingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getValueToIntegerMap()
    {
        return valueToIntegerMapEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getValueToIntegerMap_Key()
    {
        return (EReference)valueToIntegerMapEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getValueToIntegerMap_Value()
    {
        return (EReference)valueToIntegerMapEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAbstractValue()
    {
        return abstractValueEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractValue_Sort()
    {
        return (EReference)abstractValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRuntimeVariable()
    {
        return runtimeVariableEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRuntimeVariable_Variable()
    {
        return (EReference)runtimeVariableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAbstractMarking()
    {
        return abstractMarkingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimeFactory getRuntimeFactory()
    {
        return (RuntimeFactory)getEFactoryInstance();
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
        msValueEClass = createEClass(MS_VALUE);
        createEReference(msValueEClass, MS_VALUE__VALUES);

        placeMarkingEClass = createEClass(PLACE_MARKING);
        createEReference(placeMarkingEClass, PLACE_MARKING__MS_VALUE);
        createEReference(placeMarkingEClass, PLACE_MARKING__PLACE);
        createEAttribute(placeMarkingEClass, PLACE_MARKING__DIRTY);

        netMarkingEClass = createEClass(NET_MARKING);
        createEReference(netMarkingEClass, NET_MARKING__MARKINGS);

        valueToIntegerMapEClass = createEClass(VALUE_TO_INTEGER_MAP);
        createEReference(valueToIntegerMapEClass, VALUE_TO_INTEGER_MAP__KEY);
        createEReference(valueToIntegerMapEClass, VALUE_TO_INTEGER_MAP__VALUE);

        abstractValueEClass = createEClass(ABSTRACT_VALUE);
        createEReference(abstractValueEClass, ABSTRACT_VALUE__SORT);

        runtimeVariableEClass = createEClass(RUNTIME_VARIABLE);
        createEReference(runtimeVariableEClass, RUNTIME_VARIABLE__VARIABLE);

        abstractMarkingEClass = createEClass(ABSTRACT_MARKING);
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
        HlpngdefinitionPackage theHlpngdefinitionPackage = (HlpngdefinitionPackage)EPackage.Registry.INSTANCE.getEPackage(HlpngdefinitionPackage.eNS_URI);
        NetannotationsPackage theNetannotationsPackage = (NetannotationsPackage)EPackage.Registry.INSTANCE.getEPackage(NetannotationsPackage.eNS_URI);
        TermsPackage theTermsPackage = (TermsPackage)EPackage.Registry.INSTANCE.getEPackage(TermsPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        msValueEClass.getESuperTypes().add(this.getAbstractValue());
        placeMarkingEClass.getESuperTypes().add(this.getAbstractMarking());
        netMarkingEClass.getESuperTypes().add(theNetannotationsPackage.getNetAnnotation());
        runtimeVariableEClass.getESuperTypes().add(this.getAbstractValue());
        abstractMarkingEClass.getESuperTypes().add(theNetannotationsPackage.getObjectAnnotation());

        // Initialize classes and features; add operations and parameters
        initEClass(msValueEClass, MSValue.class, "MSValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getMSValue_Values(), this.getValueToIntegerMap(), null, "values", null, 0, -1, MSValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(placeMarkingEClass, PlaceMarking.class, "PlaceMarking", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPlaceMarking_MsValue(), this.getMSValue(), null, "msValue", null, 1, 1, PlaceMarking.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPlaceMarking_Place(), theHlpngdefinitionPackage.getPlace(), null, "place", null, 1, 1, PlaceMarking.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPlaceMarking_Dirty(), ecorePackage.getEBoolean(), "dirty", null, 1, 1, PlaceMarking.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(netMarkingEClass, NetMarking.class, "NetMarking", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNetMarking_Markings(), this.getAbstractMarking(), null, "markings", null, 0, -1, NetMarking.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(valueToIntegerMapEClass, Map.Entry.class, "ValueToIntegerMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
        initEReference(getValueToIntegerMap_Key(), this.getAbstractValue(), null, "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getValueToIntegerMap_Value(), this.getAbstractValue(), null, "value", null, 1, -1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractValueEClass, AbstractValue.class, "AbstractValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbstractValue_Sort(), theTermsPackage.getSort(), null, "sort", null, 1, 1, AbstractValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(runtimeVariableEClass, RuntimeVariable.class, "RuntimeVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRuntimeVariable_Variable(), theTermsPackage.getVariable(), null, "variable", null, 1, 1, RuntimeVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractMarkingEClass, AbstractMarking.class, "AbstractMarking", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //RuntimePackageImpl
