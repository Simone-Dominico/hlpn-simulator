/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package networkmodel.impl;

import networkmodel.AbstractEdge;
import networkmodel.AbstractNode;
import networkmodel.AlphaNode;
import networkmodel.Network;
import networkmodel.NetworkObject;
import networkmodel.NetworkmodelFactory;
import networkmodel.NetworkmodelPackage;
import networkmodel.OmegaNode;
import networkmodel.UndirectedEdge;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NetworkmodelPackageImpl extends EPackageImpl implements NetworkmodelPackage
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass networkEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass alphaNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass omegaNodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass abstractEdgeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass undirectedEdgeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass networkObjectEClass = null;

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
     * @see networkmodel.NetworkmodelPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private NetworkmodelPackageImpl()
    {
        super(eNS_URI, NetworkmodelFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link NetworkmodelPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static NetworkmodelPackage init()
    {
        if (isInited) return (NetworkmodelPackage)EPackage.Registry.INSTANCE.getEPackage(NetworkmodelPackage.eNS_URI);

        // Obtain or create and register package
        NetworkmodelPackageImpl theNetworkmodelPackage = (NetworkmodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof NetworkmodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new NetworkmodelPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theNetworkmodelPackage.createPackageContents();

        // Initialize created meta-data
        theNetworkmodelPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theNetworkmodelPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(NetworkmodelPackage.eNS_URI, theNetworkmodelPackage);
        return theNetworkmodelPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNetwork()
    {
        return networkEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNetwork_Network()
    {
        return (EReference)networkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAbstractNode()
    {
        return abstractNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractNode_Id()
    {
        return (EAttribute)abstractNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getAbstractNode_Label()
    {
        return (EAttribute)abstractNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractNode_Out()
    {
        return (EReference)abstractNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractNode_In()
    {
        return (EReference)abstractNodeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAlphaNode()
    {
        return alphaNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getOmegaNode()
    {
        return omegaNodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getAbstractEdge()
    {
        return abstractEdgeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractEdge_Source()
    {
        return (EReference)abstractEdgeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getAbstractEdge_Target()
    {
        return (EReference)abstractEdgeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getUndirectedEdge()
    {
        return undirectedEdgeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNetworkObject()
    {
        return networkObjectEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NetworkmodelFactory getNetworkmodelFactory()
    {
        return (NetworkmodelFactory)getEFactoryInstance();
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
        networkEClass = createEClass(NETWORK);
        createEReference(networkEClass, NETWORK__NETWORK);

        abstractNodeEClass = createEClass(ABSTRACT_NODE);
        createEAttribute(abstractNodeEClass, ABSTRACT_NODE__ID);
        createEAttribute(abstractNodeEClass, ABSTRACT_NODE__LABEL);
        createEReference(abstractNodeEClass, ABSTRACT_NODE__OUT);
        createEReference(abstractNodeEClass, ABSTRACT_NODE__IN);

        alphaNodeEClass = createEClass(ALPHA_NODE);

        omegaNodeEClass = createEClass(OMEGA_NODE);

        abstractEdgeEClass = createEClass(ABSTRACT_EDGE);
        createEReference(abstractEdgeEClass, ABSTRACT_EDGE__SOURCE);
        createEReference(abstractEdgeEClass, ABSTRACT_EDGE__TARGET);

        undirectedEdgeEClass = createEClass(UNDIRECTED_EDGE);

        networkObjectEClass = createEClass(NETWORK_OBJECT);
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

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        abstractNodeEClass.getESuperTypes().add(this.getNetworkObject());
        alphaNodeEClass.getESuperTypes().add(this.getAbstractNode());
        omegaNodeEClass.getESuperTypes().add(this.getAbstractNode());
        abstractEdgeEClass.getESuperTypes().add(this.getNetworkObject());
        undirectedEdgeEClass.getESuperTypes().add(this.getAbstractEdge());

        // Initialize classes and features; add operations and parameters
        initEClass(networkEClass, Network.class, "Network", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNetwork_Network(), this.getNetworkObject(), null, "network", null, 0, -1, Network.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractNodeEClass, AbstractNode.class, "AbstractNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractNode_Id(), ecorePackage.getEInt(), "id", null, 0, 1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractNode_Label(), ecorePackage.getEString(), "label", null, 0, 1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractNode_Out(), this.getAbstractEdge(), this.getAbstractEdge_Source(), "out", null, 0, -1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractNode_In(), this.getAbstractEdge(), this.getAbstractEdge_Target(), "in", null, 0, -1, AbstractNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(alphaNodeEClass, AlphaNode.class, "AlphaNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(omegaNodeEClass, OmegaNode.class, "OmegaNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(abstractEdgeEClass, AbstractEdge.class, "AbstractEdge", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbstractEdge_Source(), this.getAbstractNode(), this.getAbstractNode_Out(), "source", null, 1, 1, AbstractEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractEdge_Target(), this.getAbstractNode(), this.getAbstractNode_In(), "target", null, 1, 1, AbstractEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(undirectedEdgeEClass, UndirectedEdge.class, "UndirectedEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(networkObjectEClass, NetworkObject.class, "NetworkObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //NetworkmodelPackageImpl
