/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package networkmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see networkmodel.NetworkmodelFactory
 * @model kind="package"
 * @generated
 */
public interface NetworkmodelPackage extends EPackage
{
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "networkmodel";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://org.pnml.tools/network";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "networkmodel";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    NetworkmodelPackage eINSTANCE = networkmodel.impl.NetworkmodelPackageImpl.init();

    /**
     * The meta object id for the '{@link networkmodel.impl.NetworkImpl <em>Network</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see networkmodel.impl.NetworkImpl
     * @see networkmodel.impl.NetworkmodelPackageImpl#getNetwork()
     * @generated
     */
    int NETWORK = 0;

    /**
     * The feature id for the '<em><b>Network</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NETWORK__NETWORK = 0;

    /**
     * The number of structural features of the '<em>Network</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NETWORK_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link networkmodel.impl.NetworkObjectImpl <em>Network Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see networkmodel.impl.NetworkObjectImpl
     * @see networkmodel.impl.NetworkmodelPackageImpl#getNetworkObject()
     * @generated
     */
    int NETWORK_OBJECT = 6;

    /**
     * The number of structural features of the '<em>Network Object</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NETWORK_OBJECT_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link networkmodel.impl.AbstractNodeImpl <em>Abstract Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see networkmodel.impl.AbstractNodeImpl
     * @see networkmodel.impl.NetworkmodelPackageImpl#getAbstractNode()
     * @generated
     */
    int ABSTRACT_NODE = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__ID = NETWORK_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__LABEL = NETWORK_OBJECT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Out</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__OUT = NETWORK_OBJECT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>In</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__IN = NETWORK_OBJECT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Abstract Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_FEATURE_COUNT = NETWORK_OBJECT_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link networkmodel.impl.AlphaNodeImpl <em>Alpha Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see networkmodel.impl.AlphaNodeImpl
     * @see networkmodel.impl.NetworkmodelPackageImpl#getAlphaNode()
     * @generated
     */
    int ALPHA_NODE = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ALPHA_NODE__ID = ABSTRACT_NODE__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ALPHA_NODE__LABEL = ABSTRACT_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Out</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ALPHA_NODE__OUT = ABSTRACT_NODE__OUT;

    /**
     * The feature id for the '<em><b>In</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ALPHA_NODE__IN = ABSTRACT_NODE__IN;

    /**
     * The number of structural features of the '<em>Alpha Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ALPHA_NODE_FEATURE_COUNT = ABSTRACT_NODE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link networkmodel.impl.OmegaNodeImpl <em>Omega Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see networkmodel.impl.OmegaNodeImpl
     * @see networkmodel.impl.NetworkmodelPackageImpl#getOmegaNode()
     * @generated
     */
    int OMEGA_NODE = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OMEGA_NODE__ID = ABSTRACT_NODE__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OMEGA_NODE__LABEL = ABSTRACT_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Out</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OMEGA_NODE__OUT = ABSTRACT_NODE__OUT;

    /**
     * The feature id for the '<em><b>In</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OMEGA_NODE__IN = ABSTRACT_NODE__IN;

    /**
     * The number of structural features of the '<em>Omega Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OMEGA_NODE_FEATURE_COUNT = ABSTRACT_NODE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link networkmodel.impl.AbstractEdgeImpl <em>Abstract Edge</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see networkmodel.impl.AbstractEdgeImpl
     * @see networkmodel.impl.NetworkmodelPackageImpl#getAbstractEdge()
     * @generated
     */
    int ABSTRACT_EDGE = 4;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_EDGE__SOURCE = NETWORK_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_EDGE__TARGET = NETWORK_OBJECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Abstract Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_EDGE_FEATURE_COUNT = NETWORK_OBJECT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link networkmodel.impl.UndirectedEdgeImpl <em>Undirected Edge</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see networkmodel.impl.UndirectedEdgeImpl
     * @see networkmodel.impl.NetworkmodelPackageImpl#getUndirectedEdge()
     * @generated
     */
    int UNDIRECTED_EDGE = 5;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNDIRECTED_EDGE__SOURCE = ABSTRACT_EDGE__SOURCE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNDIRECTED_EDGE__TARGET = ABSTRACT_EDGE__TARGET;

    /**
     * The number of structural features of the '<em>Undirected Edge</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UNDIRECTED_EDGE_FEATURE_COUNT = ABSTRACT_EDGE_FEATURE_COUNT + 0;


    /**
     * Returns the meta object for class '{@link networkmodel.Network <em>Network</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Network</em>'.
     * @see networkmodel.Network
     * @generated
     */
    EClass getNetwork();

    /**
     * Returns the meta object for the containment reference list '{@link networkmodel.Network#getNetwork <em>Network</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Network</em>'.
     * @see networkmodel.Network#getNetwork()
     * @see #getNetwork()
     * @generated
     */
    EReference getNetwork_Network();

    /**
     * Returns the meta object for class '{@link networkmodel.AbstractNode <em>Abstract Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Node</em>'.
     * @see networkmodel.AbstractNode
     * @generated
     */
    EClass getAbstractNode();

    /**
     * Returns the meta object for the attribute '{@link networkmodel.AbstractNode#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see networkmodel.AbstractNode#getId()
     * @see #getAbstractNode()
     * @generated
     */
    EAttribute getAbstractNode_Id();

    /**
     * Returns the meta object for the attribute '{@link networkmodel.AbstractNode#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see networkmodel.AbstractNode#getLabel()
     * @see #getAbstractNode()
     * @generated
     */
    EAttribute getAbstractNode_Label();

    /**
     * Returns the meta object for the reference list '{@link networkmodel.AbstractNode#getOut <em>Out</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Out</em>'.
     * @see networkmodel.AbstractNode#getOut()
     * @see #getAbstractNode()
     * @generated
     */
    EReference getAbstractNode_Out();

    /**
     * Returns the meta object for the reference list '{@link networkmodel.AbstractNode#getIn <em>In</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>In</em>'.
     * @see networkmodel.AbstractNode#getIn()
     * @see #getAbstractNode()
     * @generated
     */
    EReference getAbstractNode_In();

    /**
     * Returns the meta object for class '{@link networkmodel.AlphaNode <em>Alpha Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Alpha Node</em>'.
     * @see networkmodel.AlphaNode
     * @generated
     */
    EClass getAlphaNode();

    /**
     * Returns the meta object for class '{@link networkmodel.OmegaNode <em>Omega Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Omega Node</em>'.
     * @see networkmodel.OmegaNode
     * @generated
     */
    EClass getOmegaNode();

    /**
     * Returns the meta object for class '{@link networkmodel.AbstractEdge <em>Abstract Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Edge</em>'.
     * @see networkmodel.AbstractEdge
     * @generated
     */
    EClass getAbstractEdge();

    /**
     * Returns the meta object for the reference '{@link networkmodel.AbstractEdge#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see networkmodel.AbstractEdge#getSource()
     * @see #getAbstractEdge()
     * @generated
     */
    EReference getAbstractEdge_Source();

    /**
     * Returns the meta object for the reference '{@link networkmodel.AbstractEdge#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see networkmodel.AbstractEdge#getTarget()
     * @see #getAbstractEdge()
     * @generated
     */
    EReference getAbstractEdge_Target();

    /**
     * Returns the meta object for class '{@link networkmodel.UndirectedEdge <em>Undirected Edge</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Undirected Edge</em>'.
     * @see networkmodel.UndirectedEdge
     * @generated
     */
    EClass getUndirectedEdge();

    /**
     * Returns the meta object for class '{@link networkmodel.NetworkObject <em>Network Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Network Object</em>'.
     * @see networkmodel.NetworkObject
     * @generated
     */
    EClass getNetworkObject();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    NetworkmodelFactory getNetworkmodelFactory();

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
         * The meta object literal for the '{@link networkmodel.impl.NetworkImpl <em>Network</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see networkmodel.impl.NetworkImpl
         * @see networkmodel.impl.NetworkmodelPackageImpl#getNetwork()
         * @generated
         */
        EClass NETWORK = eINSTANCE.getNetwork();

        /**
         * The meta object literal for the '<em><b>Network</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference NETWORK__NETWORK = eINSTANCE.getNetwork_Network();

        /**
         * The meta object literal for the '{@link networkmodel.impl.AbstractNodeImpl <em>Abstract Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see networkmodel.impl.AbstractNodeImpl
         * @see networkmodel.impl.NetworkmodelPackageImpl#getAbstractNode()
         * @generated
         */
        EClass ABSTRACT_NODE = eINSTANCE.getAbstractNode();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_NODE__ID = eINSTANCE.getAbstractNode_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_NODE__LABEL = eINSTANCE.getAbstractNode_Label();

        /**
         * The meta object literal for the '<em><b>Out</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_NODE__OUT = eINSTANCE.getAbstractNode_Out();

        /**
         * The meta object literal for the '<em><b>In</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_NODE__IN = eINSTANCE.getAbstractNode_In();

        /**
         * The meta object literal for the '{@link networkmodel.impl.AlphaNodeImpl <em>Alpha Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see networkmodel.impl.AlphaNodeImpl
         * @see networkmodel.impl.NetworkmodelPackageImpl#getAlphaNode()
         * @generated
         */
        EClass ALPHA_NODE = eINSTANCE.getAlphaNode();

        /**
         * The meta object literal for the '{@link networkmodel.impl.OmegaNodeImpl <em>Omega Node</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see networkmodel.impl.OmegaNodeImpl
         * @see networkmodel.impl.NetworkmodelPackageImpl#getOmegaNode()
         * @generated
         */
        EClass OMEGA_NODE = eINSTANCE.getOmegaNode();

        /**
         * The meta object literal for the '{@link networkmodel.impl.AbstractEdgeImpl <em>Abstract Edge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see networkmodel.impl.AbstractEdgeImpl
         * @see networkmodel.impl.NetworkmodelPackageImpl#getAbstractEdge()
         * @generated
         */
        EClass ABSTRACT_EDGE = eINSTANCE.getAbstractEdge();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_EDGE__SOURCE = eINSTANCE.getAbstractEdge_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_EDGE__TARGET = eINSTANCE.getAbstractEdge_Target();

        /**
         * The meta object literal for the '{@link networkmodel.impl.UndirectedEdgeImpl <em>Undirected Edge</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see networkmodel.impl.UndirectedEdgeImpl
         * @see networkmodel.impl.NetworkmodelPackageImpl#getUndirectedEdge()
         * @generated
         */
        EClass UNDIRECTED_EDGE = eINSTANCE.getUndirectedEdge();

        /**
         * The meta object literal for the '{@link networkmodel.impl.NetworkObjectImpl <em>Network Object</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see networkmodel.impl.NetworkObjectImpl
         * @see networkmodel.impl.NetworkmodelPackageImpl#getNetworkObject()
         * @generated
         */
        EClass NETWORK_OBJECT = eINSTANCE.getNetworkObject();

    }

} //NetworkmodelPackage
