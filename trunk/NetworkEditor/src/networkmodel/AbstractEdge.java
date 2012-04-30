/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package networkmodel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link networkmodel.AbstractEdge#getSource <em>Source</em>}</li>
 *   <li>{@link networkmodel.AbstractEdge#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see networkmodel.NetworkmodelPackage#getAbstractEdge()
 * @model abstract="true"
 * @generated
 */
public interface AbstractEdge extends NetworkObject
{
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * It is bidirectional and its opposite is '{@link networkmodel.Node#getOut <em>Out</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(Node)
     * @see networkmodel.NetworkmodelPackage#getAbstractEdge_Source()
     * @see networkmodel.Node#getOut
     * @model opposite="out" required="true"
     * @generated
     */
    Node getSource();

    /**
     * Sets the value of the '{@link networkmodel.AbstractEdge#getSource <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(Node value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * It is bidirectional and its opposite is '{@link networkmodel.Node#getIn <em>In</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(Node)
     * @see networkmodel.NetworkmodelPackage#getAbstractEdge_Target()
     * @see networkmodel.Node#getIn
     * @model opposite="in" required="true"
     * @generated
     */
    Node getTarget();

    /**
     * Sets the value of the '{@link networkmodel.AbstractEdge#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(Node value);

} // AbstractEdge