/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime.impl;

import numberRuntime.IntValue;
import numberRuntime.NatValue;
import numberRuntime.NumberValue;
import numberRuntime.NumberruntimeFactory;
import numberRuntime.NumberruntimePackage;
import numberRuntime.PosValue;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegersPackage;

import runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NumberruntimePackageImpl extends EPackageImpl implements NumberruntimePackage
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass numberValueEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass intValueEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass posValueEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass natValueEClass = null;

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
     * @see numberRuntime.NumberruntimePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private NumberruntimePackageImpl()
    {
        super(eNS_URI, NumberruntimeFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link NumberruntimePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static NumberruntimePackage init()
    {
        if (isInited) return (NumberruntimePackage)EPackage.Registry.INSTANCE.getEPackage(NumberruntimePackage.eNS_URI);

        // Obtain or create and register package
        NumberruntimePackageImpl theNumberruntimePackage = (NumberruntimePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof NumberruntimePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new NumberruntimePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        RuntimePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theNumberruntimePackage.createPackageContents();

        // Initialize created meta-data
        theNumberruntimePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theNumberruntimePackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(NumberruntimePackage.eNS_URI, theNumberruntimePackage);
        return theNumberruntimePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNumberValue()
    {
        return numberValueEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNumberValue_N()
    {
        return (EAttribute)numberValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNumberValue_NumSort()
    {
        return (EReference)numberValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIntValue()
    {
        return intValueEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntValue_IntSort()
    {
        return (EReference)intValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPosValue()
    {
        return posValueEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPosValue_PosSort()
    {
        return (EReference)posValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNatValue()
    {
        return natValueEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNatValue_NatSort()
    {
        return (EReference)natValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NumberruntimeFactory getNumberruntimeFactory()
    {
        return (NumberruntimeFactory)getEFactoryInstance();
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
        numberValueEClass = createEClass(NUMBER_VALUE);
        createEAttribute(numberValueEClass, NUMBER_VALUE__N);
        createEReference(numberValueEClass, NUMBER_VALUE__NUM_SORT);

        intValueEClass = createEClass(INT_VALUE);
        createEReference(intValueEClass, INT_VALUE__INT_SORT);

        posValueEClass = createEClass(POS_VALUE);
        createEReference(posValueEClass, POS_VALUE__POS_SORT);

        natValueEClass = createEClass(NAT_VALUE);
        createEReference(natValueEClass, NAT_VALUE__NAT_SORT);
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
        RuntimePackage theRuntimePackage = (RuntimePackage)EPackage.Registry.INSTANCE.getEPackage(RuntimePackage.eNS_URI);
        IntegersPackage theIntegersPackage = (IntegersPackage)EPackage.Registry.INSTANCE.getEPackage(IntegersPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        numberValueEClass.getESuperTypes().add(theRuntimePackage.getAbstractValue());
        intValueEClass.getESuperTypes().add(this.getNumberValue());
        posValueEClass.getESuperTypes().add(this.getNumberValue());
        natValueEClass.getESuperTypes().add(this.getNumberValue());

        // Initialize classes and features; add operations and parameters
        initEClass(numberValueEClass, NumberValue.class, "NumberValue", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNumberValue_N(), ecorePackage.getEInt(), "n", null, 1, 1, NumberValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getNumberValue_NumSort(), theIntegersPackage.getNumber(), null, "numSort", null, 1, 1, NumberValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(intValueEClass, IntValue.class, "IntValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIntValue_IntSort(), theIntegersPackage.getInteger(), null, "intSort", null, 1, 1, IntValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(posValueEClass, PosValue.class, "PosValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPosValue_PosSort(), theIntegersPackage.getPositive(), null, "posSort", null, 1, 1, PosValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(natValueEClass, NatValue.class, "NatValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNatValue_NatSort(), theIntegersPackage.getNatural(), null, "natSort", null, 1, 1, NatValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations()
    {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
        addAnnotation
          (getNumberValue_N(), 
           source, 
           new String[] 
           {
             "namespace", ""
           });
    }

} //NumberruntimePackageImpl
