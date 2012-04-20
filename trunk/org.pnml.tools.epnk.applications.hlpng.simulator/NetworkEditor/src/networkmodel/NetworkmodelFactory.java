/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package networkmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see networkmodel.NetworkmodelPackage
 * @generated
 */
public interface NetworkmodelFactory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    NetworkmodelFactory eINSTANCE = networkmodel.impl.NetworkmodelFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Network</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Network</em>'.
     * @generated
     */
    Network createNetwork();

    /**
     * Returns a new object of class '<em>Alpha Node</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Alpha Node</em>'.
     * @generated
     */
    AlphaNode createAlphaNode();

    /**
     * Returns a new object of class '<em>Omega Node</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Omega Node</em>'.
     * @generated
     */
    OmegaNode createOmegaNode();

    /**
     * Returns a new object of class '<em>Undirected Edge</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Undirected Edge</em>'.
     * @generated
     */
    UndirectedEdge createUndirectedEdge();

    /**
     * Returns a new object of class '<em>Directed Edge</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Directed Edge</em>'.
     * @generated
     */
    DirectedEdge createDirectedEdge();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    NetworkmodelPackage getNetworkmodelPackage();

} //NetworkmodelFactory
