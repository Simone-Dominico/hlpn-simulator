/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime;

import org.eclipse.emf.ecore.EObject;

import runtime.AbstractValue;
import runtime.PlaceMarking;
import runtime.RuntimeVariable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Firing Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link transitionruntime.FiringData#getPlaceMarking <em>Place Marking</em>}</li>
 *   <li>{@link transitionruntime.FiringData#getMsTerm <em>Ms Term</em>}</li>
 *   <li>{@link transitionruntime.FiringData#getVarValue <em>Var Value</em>}</li>
 *   <li>{@link transitionruntime.FiringData#getVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see transitionruntime.TransitionruntimePackage#getFiringData()
 * @model
 * @generated
 */
public interface FiringData extends EObject
{
    /**
     * Returns the value of the '<em><b>Place Marking</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Place Marking</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Place Marking</em>' reference.
     * @see #setPlaceMarking(PlaceMarking)
     * @see transitionruntime.TransitionruntimePackage#getFiringData_PlaceMarking()
     * @model required="true"
     * @generated
     */
    PlaceMarking getPlaceMarking();

    /**
     * Sets the value of the '{@link transitionruntime.FiringData#getPlaceMarking <em>Place Marking</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Place Marking</em>' reference.
     * @see #getPlaceMarking()
     * @generated
     */
    void setPlaceMarking(PlaceMarking value);

    /**
     * Returns the value of the '<em><b>Ms Term</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ms Term</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ms Term</em>' reference.
     * @see #setMsTerm(MSTerm)
     * @see transitionruntime.TransitionruntimePackage#getFiringData_MsTerm()
     * @model required="true"
     * @generated
     */
    MSTerm getMsTerm();

    /**
     * Sets the value of the '{@link transitionruntime.FiringData#getMsTerm <em>Ms Term</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ms Term</em>' reference.
     * @see #getMsTerm()
     * @generated
     */
    void setMsTerm(MSTerm value);

    /**
     * Returns the value of the '<em><b>Variable</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variable</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variable</em>' reference.
     * @see #setVariable(RuntimeVariable)
     * @see transitionruntime.TransitionruntimePackage#getFiringData_Variable()
     * @model required="true"
     * @generated
     */
    RuntimeVariable getVariable();

    /**
     * Sets the value of the '{@link transitionruntime.FiringData#getVariable <em>Variable</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variable</em>' reference.
     * @see #getVariable()
     * @generated
     */
    void setVariable(RuntimeVariable value);

    /**
     * Returns the value of the '<em><b>Var Value</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Var Value</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Var Value</em>' reference.
     * @see #setVarValue(AbstractValue)
     * @see transitionruntime.TransitionruntimePackage#getFiringData_VarValue()
     * @model required="true"
     * @generated
     */
    AbstractValue getVarValue();

    /**
     * Sets the value of the '{@link transitionruntime.FiringData#getVarValue <em>Var Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Var Value</em>' reference.
     * @see #getVarValue()
     * @generated
     */
    void setVarValue(AbstractValue value);

} // FiringData
