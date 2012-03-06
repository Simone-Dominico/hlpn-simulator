/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package variableruntime.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.TermsPackage;
import runtime.RuntimePackage;

import transitionruntime.TransitionruntimePackage;

import variableruntime.RuntimeVariable;
import variableruntime.VariableruntimeFactory;
import variableruntime.VariableruntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class VariableruntimePackageImpl extends EPackageImpl implements VariableruntimePackage
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass runtimeVariableEClass = null;

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
     * @see variableruntime.VariableruntimePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private VariableruntimePackageImpl()
    {
        super(eNS_URI, VariableruntimeFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link VariableruntimePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static VariableruntimePackage init()
    {
        if (isInited) return (VariableruntimePackage)EPackage.Registry.INSTANCE.getEPackage(VariableruntimePackage.eNS_URI);

        // Obtain or create and register package
        VariableruntimePackageImpl theVariableruntimePackage = (VariableruntimePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof VariableruntimePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new VariableruntimePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        TransitionruntimePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theVariableruntimePackage.createPackageContents();

        // Initialize created meta-data
        theVariableruntimePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theVariableruntimePackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(VariableruntimePackage.eNS_URI, theVariableruntimePackage);
        return theVariableruntimePackage;
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
    public VariableruntimeFactory getVariableruntimeFactory()
    {
        return (VariableruntimeFactory)getEFactoryInstance();
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
        runtimeVariableEClass = createEClass(RUNTIME_VARIABLE);
        createEReference(runtimeVariableEClass, RUNTIME_VARIABLE__VARIABLE);
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
        TermsPackage theTermsPackage = (TermsPackage)EPackage.Registry.INSTANCE.getEPackage(TermsPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        runtimeVariableEClass.getESuperTypes().add(theRuntimePackage.getAbstractValue());

        // Initialize classes and features; add operations and parameters
        initEClass(runtimeVariableEClass, RuntimeVariable.class, "RuntimeVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRuntimeVariable_Variable(), theTermsPackage.getVariable(), null, "variable", null, 1, 1, RuntimeVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //VariableruntimePackageImpl
