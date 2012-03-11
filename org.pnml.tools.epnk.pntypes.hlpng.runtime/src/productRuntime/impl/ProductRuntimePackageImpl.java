/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package productRuntime.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import productRuntime.ProductRuntimeFactory;
import productRuntime.ProductRuntimePackage;
import productRuntime.ProductValue;

import runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProductRuntimePackageImpl extends EPackageImpl implements ProductRuntimePackage
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass productValueEClass = null;

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
     * @see productRuntime.ProductRuntimePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ProductRuntimePackageImpl()
    {
        super(eNS_URI, ProductRuntimeFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ProductRuntimePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ProductRuntimePackage init()
    {
        if (isInited) return (ProductRuntimePackage)EPackage.Registry.INSTANCE.getEPackage(ProductRuntimePackage.eNS_URI);

        // Obtain or create and register package
        ProductRuntimePackageImpl theProductRuntimePackage = (ProductRuntimePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ProductRuntimePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ProductRuntimePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        RuntimePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theProductRuntimePackage.createPackageContents();

        // Initialize created meta-data
        theProductRuntimePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theProductRuntimePackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ProductRuntimePackage.eNS_URI, theProductRuntimePackage);
        return theProductRuntimePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getProductValue()
    {
        return productValueEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getProductValue_Components()
    {
        return (EReference)productValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProductRuntimeFactory getProductRuntimeFactory()
    {
        return (ProductRuntimeFactory)getEFactoryInstance();
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
        productValueEClass = createEClass(PRODUCT_VALUE);
        createEReference(productValueEClass, PRODUCT_VALUE__COMPONENTS);
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        productValueEClass.getESuperTypes().add(theRuntimePackage.getAbstractValue());

        // Initialize classes and features; add operations and parameters
        initEClass(productValueEClass, ProductValue.class, "ProductValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getProductValue_Components(), theRuntimePackage.getAbstractValue(), null, "components", null, 0, -1, ProductValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //ProductRuntimePackageImpl
