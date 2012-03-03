/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import runtime.AbstractValue;
import runtime.PlaceMarking;

import transitionruntime.FiringMode;
import transitionruntime.TransitionruntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Firing Mode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link transitionruntime.impl.FiringModeImpl#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FiringModeImpl extends EObjectImpl implements FiringMode
{
    /**
     * The cached value of the '{@link #getValues() <em>Values</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValues()
     * @generated
     * @ordered
     */
    protected EMap<PlaceMarking, AbstractValue> values;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FiringModeImpl()
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
        return TransitionruntimePackage.Literals.FIRING_MODE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<PlaceMarking, AbstractValue> getValues()
    {
        if (values == null)
        {
            values = new EcoreEMap<PlaceMarking,AbstractValue>(TransitionruntimePackage.Literals.PLACE_MARKING_TO_VALUE_MAP, PlaceMarkingToValueMapImpl.class, this, TransitionruntimePackage.FIRING_MODE__VALUES);
        }
        return values;
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
            case TransitionruntimePackage.FIRING_MODE__VALUES:
                return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
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
            case TransitionruntimePackage.FIRING_MODE__VALUES:
                if (coreType) return getValues();
                else return getValues().map();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case TransitionruntimePackage.FIRING_MODE__VALUES:
                ((EStructuralFeature.Setting)getValues()).set(newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    @Override
    // @generated NOT
    public String toString()
    {
    	StringBuffer buffer = new StringBuffer();
    	for(AbstractValue value : values.values())
    	{
    		buffer.append(value.toString() + "\n");
    	}
	    return buffer.toString().replaceAll("\\s+$", "");
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
            case TransitionruntimePackage.FIRING_MODE__VALUES:
                getValues().clear();
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
            case TransitionruntimePackage.FIRING_MODE__VALUES:
                return values != null && !values.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //FiringModeImpl
