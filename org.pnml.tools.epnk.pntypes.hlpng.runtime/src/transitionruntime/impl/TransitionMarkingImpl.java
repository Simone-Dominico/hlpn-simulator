/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.pnml.tools.epnk.annotations.netannotations.impl.ObjectAnnotationImpl;

import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Transition;

import transitionruntime.FiringMode;
import transitionruntime.TransitionMarking;
import transitionruntime.TransitionruntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition Marking</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link transitionruntime.impl.TransitionMarkingImpl#getTransition <em>Transition</em>}</li>
 *   <li>{@link transitionruntime.impl.TransitionMarkingImpl#getModes <em>Modes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransitionMarkingImpl extends ObjectAnnotationImpl implements TransitionMarking
{
    /**
     * The cached value of the '{@link #getTransition() <em>Transition</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTransition()
     * @generated
     * @ordered
     */
    protected Transition transition;

    /**
     * The cached value of the '{@link #getModes() <em>Modes</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getModes()
     * @generated
     * @ordered
     */
    protected EList<FiringMode> modes;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TransitionMarkingImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return TransitionruntimePackage.Literals.TRANSITION_MARKING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Transition getTransition()
    {
        if (transition != null && transition.eIsProxy())
        {
            InternalEObject oldTransition = (InternalEObject)transition;
            transition = (Transition)eResolveProxy(oldTransition);
            if (transition != oldTransition)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TransitionruntimePackage.TRANSITION_MARKING__TRANSITION, oldTransition, transition));
            }
        }
        return transition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Transition basicGetTransition()
    {
        return transition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTransition(Transition newTransition)
    {
        Transition oldTransition = transition;
        transition = newTransition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TransitionruntimePackage.TRANSITION_MARKING__TRANSITION, oldTransition, transition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<FiringMode> getModes()
    {
        if (modes == null)
        {
            modes = new EObjectContainmentEList<FiringMode>(FiringMode.class, this, TransitionruntimePackage.TRANSITION_MARKING__MODES);
        }
        return modes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case TransitionruntimePackage.TRANSITION_MARKING__MODES:
                return ((InternalEList<?>)getModes()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case TransitionruntimePackage.TRANSITION_MARKING__TRANSITION:
                if (resolve) return getTransition();
                return basicGetTransition();
            case TransitionruntimePackage.TRANSITION_MARKING__MODES:
                return getModes();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case TransitionruntimePackage.TRANSITION_MARKING__TRANSITION:
                setTransition((Transition)newValue);
                return;
            case TransitionruntimePackage.TRANSITION_MARKING__MODES:
                getModes().clear();
                getModes().addAll((Collection<? extends FiringMode>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID)
    {
        switch (featureID)
        {
            case TransitionruntimePackage.TRANSITION_MARKING__TRANSITION:
                setTransition((Transition)null);
                return;
            case TransitionruntimePackage.TRANSITION_MARKING__MODES:
                getModes().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID)
    {
        switch (featureID)
        {
            case TransitionruntimePackage.TRANSITION_MARKING__TRANSITION:
                return transition != null;
            case TransitionruntimePackage.TRANSITION_MARKING__MODES:
                return modes != null && !modes.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //TransitionMarkingImpl
