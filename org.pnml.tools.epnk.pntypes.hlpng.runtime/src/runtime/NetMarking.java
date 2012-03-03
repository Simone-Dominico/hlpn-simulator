/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime;

import org.eclipse.emf.common.util.EList;

import org.pnml.tools.epnk.annotations.netannotations.NetAnnotation;
import transitionruntime.TransitionMarking;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Net Marking</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link runtime.NetMarking#getMarkings <em>Markings</em>}</li>
 *   <li>{@link runtime.NetMarking#getTransitionMarkings <em>Transition Markings</em>}</li>
 * </ul>
 * </p>
 *
 * @see runtime.RuntimePackage#getNetMarking()
 * @model
 * @generated
 */
public interface NetMarking extends NetAnnotation
{
    /**
     * Returns the value of the '<em><b>Markings</b></em>' containment reference list.
     * The list contents are of type {@link runtime.PlaceMarking}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Markings</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Markings</em>' containment reference list.
     * @see runtime.RuntimePackage#getNetMarking_Markings()
     * @model containment="true"
     * @generated
     */
    EList<PlaceMarking> getMarkings();

    /**
     * Returns the value of the '<em><b>Transition Markings</b></em>' containment reference list.
     * The list contents are of type {@link transitionruntime.TransitionMarking}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transition Markings</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transition Markings</em>' containment reference list.
     * @see runtime.RuntimePackage#getNetMarking_TransitionMarkings()
     * @model containment="true"
     * @generated
     */
    EList<TransitionMarking> getTransitionMarkings();

} // NetMarking
