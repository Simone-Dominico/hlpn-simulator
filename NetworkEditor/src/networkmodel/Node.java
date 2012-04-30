/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package networkmodel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link networkmodel.Node#getLabel <em>Label</em>}</li>
 *   <li>{@link networkmodel.Node#getOut <em>Out</em>}</li>
 *   <li>{@link networkmodel.Node#getIn <em>In</em>}</li>
 * </ul>
 * </p>
 *
 * @see networkmodel.NetworkmodelPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends NetworkObject
{
    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' attribute.
     * @see #setLabel(String)
     * @see networkmodel.NetworkmodelPackage#getNode_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link networkmodel.Node#getLabel <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

    /**
     * Returns the value of the '<em><b>Out</b></em>' reference list.
     * The list contents are of type {@link networkmodel.AbstractEdge}.
     * It is bidirectional and its opposite is '{@link networkmodel.AbstractEdge#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Out</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Out</em>' reference list.
     * @see networkmodel.NetworkmodelPackage#getNode_Out()
     * @see networkmodel.AbstractEdge#getSource
     * @model opposite="source"
     * @generated
     */
    EList<AbstractEdge> getOut();

    /**
     * Returns the value of the '<em><b>In</b></em>' reference list.
     * The list contents are of type {@link networkmodel.AbstractEdge}.
     * It is bidirectional and its opposite is '{@link networkmodel.AbstractEdge#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>In</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>In</em>' reference list.
     * @see networkmodel.NetworkmodelPackage#getNode_In()
     * @see networkmodel.AbstractEdge#getTarget
     * @model opposite="target"
     * @generated
     */
    EList<AbstractEdge> getIn();

} // Node
