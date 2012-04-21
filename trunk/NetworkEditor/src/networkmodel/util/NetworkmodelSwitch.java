/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package networkmodel.util;

import networkmodel.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see networkmodel.NetworkmodelPackage
 * @generated
 */
public class NetworkmodelSwitch<T> extends Switch<T>
{
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static NetworkmodelPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NetworkmodelSwitch()
    {
        if (modelPackage == null)
        {
            modelPackage = NetworkmodelPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage)
    {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject)
    {
        switch (classifierID)
        {
            case NetworkmodelPackage.NETWORK:
            {
                Network network = (Network)theEObject;
                T result = caseNetwork(network);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case NetworkmodelPackage.ABSTRACT_NODE:
            {
                AbstractNode abstractNode = (AbstractNode)theEObject;
                T result = caseAbstractNode(abstractNode);
                if (result == null) result = caseNetworkObject(abstractNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case NetworkmodelPackage.ALPHA_NODE:
            {
                AlphaNode alphaNode = (AlphaNode)theEObject;
                T result = caseAlphaNode(alphaNode);
                if (result == null) result = caseAbstractNode(alphaNode);
                if (result == null) result = caseNetworkObject(alphaNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case NetworkmodelPackage.OMEGA_NODE:
            {
                OmegaNode omegaNode = (OmegaNode)theEObject;
                T result = caseOmegaNode(omegaNode);
                if (result == null) result = caseAbstractNode(omegaNode);
                if (result == null) result = caseNetworkObject(omegaNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case NetworkmodelPackage.ABSTRACT_EDGE:
            {
                AbstractEdge abstractEdge = (AbstractEdge)theEObject;
                T result = caseAbstractEdge(abstractEdge);
                if (result == null) result = caseNetworkObject(abstractEdge);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case NetworkmodelPackage.UNDIRECTED_EDGE:
            {
                UndirectedEdge undirectedEdge = (UndirectedEdge)theEObject;
                T result = caseUndirectedEdge(undirectedEdge);
                if (result == null) result = caseAbstractEdge(undirectedEdge);
                if (result == null) result = caseNetworkObject(undirectedEdge);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case NetworkmodelPackage.NETWORK_OBJECT:
            {
                NetworkObject networkObject = (NetworkObject)theEObject;
                T result = caseNetworkObject(networkObject);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case NetworkmodelPackage.DIRECTED_EDGE:
            {
                DirectedEdge directedEdge = (DirectedEdge)theEObject;
                T result = caseDirectedEdge(directedEdge);
                if (result == null) result = caseAbstractEdge(directedEdge);
                if (result == null) result = caseNetworkObject(directedEdge);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Network</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Network</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNetwork(Network object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractNode(AbstractNode object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Alpha Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Alpha Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAlphaNode(AlphaNode object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Omega Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Omega Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOmegaNode(OmegaNode object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Edge</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Edge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractEdge(AbstractEdge object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Undirected Edge</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Undirected Edge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUndirectedEdge(UndirectedEdge object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Network Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Network Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNetworkObject(NetworkObject object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Directed Edge</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Directed Edge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDirectedEdge(DirectedEdge object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object)
    {
        return null;
    }

} //NetworkmodelSwitch