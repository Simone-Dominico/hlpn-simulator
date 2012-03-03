/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.pnml.tools.epnk.annotations.netannotations.impl.NetAnnotationImpl;

import runtime.NetMarking;
import runtime.PlaceMarking;
import runtime.RuntimePackage;
import transitionruntime.TransitionMarking;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Net Marking</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link runtime.impl.NetMarkingImpl#getMarkings <em>Markings</em>}</li>
 *   <li>{@link runtime.impl.NetMarkingImpl#getTransitionMarkings <em>Transition Markings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NetMarkingImpl extends NetAnnotationImpl implements NetMarking
{
    /**
     * The cached value of the '{@link #getMarkings() <em>Markings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMarkings()
     * @generated
     * @ordered
     */
    protected EList<PlaceMarking> markings;

    /**
     * The cached value of the '{@link #getTransitionMarkings() <em>Transition Markings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTransitionMarkings()
     * @generated
     * @ordered
     */
    protected EList<TransitionMarking> transitionMarkings;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NetMarkingImpl()
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
        return RuntimePackage.Literals.NET_MARKING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<PlaceMarking> getMarkings()
    {
        if (markings == null)
        {
            markings = new EObjectContainmentEList<PlaceMarking>(PlaceMarking.class, this, RuntimePackage.NET_MARKING__MARKINGS);
        }
        return markings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TransitionMarking> getTransitionMarkings()
    {
        if (transitionMarkings == null)
        {
            transitionMarkings = new EObjectContainmentEList<TransitionMarking>(TransitionMarking.class, this, RuntimePackage.NET_MARKING__TRANSITION_MARKINGS);
        }
        return transitionMarkings;
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
            case RuntimePackage.NET_MARKING__MARKINGS:
                return ((InternalEList<?>)getMarkings()).basicRemove(otherEnd, msgs);
            case RuntimePackage.NET_MARKING__TRANSITION_MARKINGS:
                return ((InternalEList<?>)getTransitionMarkings()).basicRemove(otherEnd, msgs);
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
            case RuntimePackage.NET_MARKING__MARKINGS:
                return getMarkings();
            case RuntimePackage.NET_MARKING__TRANSITION_MARKINGS:
                return getTransitionMarkings();
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
            case RuntimePackage.NET_MARKING__MARKINGS:
                getMarkings().clear();
                getMarkings().addAll((Collection<? extends PlaceMarking>)newValue);
                return;
            case RuntimePackage.NET_MARKING__TRANSITION_MARKINGS:
                getTransitionMarkings().clear();
                getTransitionMarkings().addAll((Collection<? extends TransitionMarking>)newValue);
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
            case RuntimePackage.NET_MARKING__MARKINGS:
                getMarkings().clear();
                return;
            case RuntimePackage.NET_MARKING__TRANSITION_MARKINGS:
                getTransitionMarkings().clear();
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
            case RuntimePackage.NET_MARKING__MARKINGS:
                return markings != null && !markings.isEmpty();
            case RuntimePackage.NET_MARKING__TRANSITION_MARKINGS:
                return transitionMarkings != null && !transitionMarkings.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //NetMarkingImpl
