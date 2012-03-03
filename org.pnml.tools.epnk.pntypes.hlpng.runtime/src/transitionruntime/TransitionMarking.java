/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime;

import org.eclipse.emf.common.util.EList;

import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;

import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition Marking</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link transitionruntime.TransitionMarking#getTransition <em>Transition</em>}</li>
 *   <li>{@link transitionruntime.TransitionMarking#getModes <em>Modes</em>}</li>
 * </ul>
 * </p>
 *
 * @see transitionruntime.TransitionruntimePackage#getTransitionMarking()
 * @model
 * @generated
 */
public interface TransitionMarking extends ObjectAnnotation
{
    /**
     * Returns the value of the '<em><b>Transition</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transition</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transition</em>' reference.
     * @see #setTransition(Transition)
     * @see transitionruntime.TransitionruntimePackage#getTransitionMarking_Transition()
     * @model required="true"
     * @generated
     */
    Transition getTransition();

    /**
     * Sets the value of the '{@link transitionruntime.TransitionMarking#getTransition <em>Transition</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transition</em>' reference.
     * @see #getTransition()
     * @generated
     */
    void setTransition(Transition value);

    /**
     * Returns the value of the '<em><b>Modes</b></em>' containment reference list.
     * The list contents are of type {@link transitionruntime.FiringMode}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Modes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Modes</em>' containment reference list.
     * @see transitionruntime.TransitionruntimePackage#getTransitionMarking_Modes()
     * @model containment="true"
     * @generated
     */
    EList<FiringMode> getModes();

} // TransitionMarking
