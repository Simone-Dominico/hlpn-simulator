/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import runtime.AbstractValue;
import runtime.PlaceMarking;

import runtime.RuntimeVariable;
import transitionruntime.FiringData;
import transitionruntime.MSTerm;
import transitionruntime.TransitionruntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Firing Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link transitionruntime.impl.FiringDataImpl#getPlaceMarking <em>Place Marking</em>}</li>
 *   <li>{@link transitionruntime.impl.FiringDataImpl#getMsTerm <em>Ms Term</em>}</li>
 *   <li>{@link transitionruntime.impl.FiringDataImpl#getVarValue <em>Var Value</em>}</li>
 *   <li>{@link transitionruntime.impl.FiringDataImpl#getVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FiringDataImpl extends EObjectImpl implements FiringData
{
    /**
     * The cached value of the '{@link #getPlaceMarking() <em>Place Marking</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPlaceMarking()
     * @generated
     * @ordered
     */
    protected PlaceMarking placeMarking;

    /**
     * The cached value of the '{@link #getMsTerm() <em>Ms Term</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMsTerm()
     * @generated
     * @ordered
     */
    protected MSTerm msTerm;

    /**
     * The cached value of the '{@link #getVarValue() <em>Var Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVarValue()
     * @generated
     * @ordered
     */
    protected AbstractValue varValue;

    /**
     * The cached value of the '{@link #getVariable() <em>Variable</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariable()
     * @generated
     * @ordered
     */
    protected RuntimeVariable variable;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FiringDataImpl()
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
        return TransitionruntimePackage.Literals.FIRING_DATA;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PlaceMarking getPlaceMarking()
    {
        if (placeMarking != null && placeMarking.eIsProxy())
        {
            InternalEObject oldPlaceMarking = (InternalEObject)placeMarking;
            placeMarking = (PlaceMarking)eResolveProxy(oldPlaceMarking);
            if (placeMarking != oldPlaceMarking)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TransitionruntimePackage.FIRING_DATA__PLACE_MARKING, oldPlaceMarking, placeMarking));
            }
        }
        return placeMarking;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PlaceMarking basicGetPlaceMarking()
    {
        return placeMarking;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPlaceMarking(PlaceMarking newPlaceMarking)
    {
        PlaceMarking oldPlaceMarking = placeMarking;
        placeMarking = newPlaceMarking;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TransitionruntimePackage.FIRING_DATA__PLACE_MARKING, oldPlaceMarking, placeMarking));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MSTerm getMsTerm()
    {
        if (msTerm != null && msTerm.eIsProxy())
        {
            InternalEObject oldMsTerm = (InternalEObject)msTerm;
            msTerm = (MSTerm)eResolveProxy(oldMsTerm);
            if (msTerm != oldMsTerm)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TransitionruntimePackage.FIRING_DATA__MS_TERM, oldMsTerm, msTerm));
            }
        }
        return msTerm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MSTerm basicGetMsTerm()
    {
        return msTerm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMsTerm(MSTerm newMsTerm)
    {
        MSTerm oldMsTerm = msTerm;
        msTerm = newMsTerm;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TransitionruntimePackage.FIRING_DATA__MS_TERM, oldMsTerm, msTerm));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimeVariable getVariable()
    {
        if (variable != null && variable.eIsProxy())
        {
            InternalEObject oldVariable = (InternalEObject)variable;
            variable = (RuntimeVariable)eResolveProxy(oldVariable);
            if (variable != oldVariable)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TransitionruntimePackage.FIRING_DATA__VARIABLE, oldVariable, variable));
            }
        }
        return variable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimeVariable basicGetVariable()
    {
        return variable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVariable(RuntimeVariable newVariable)
    {
        RuntimeVariable oldVariable = variable;
        variable = newVariable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TransitionruntimePackage.FIRING_DATA__VARIABLE, oldVariable, variable));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue getVarValue()
    {
        if (varValue != null && varValue.eIsProxy())
        {
            InternalEObject oldVarValue = (InternalEObject)varValue;
            varValue = (AbstractValue)eResolveProxy(oldVarValue);
            if (varValue != oldVarValue)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TransitionruntimePackage.FIRING_DATA__VAR_VALUE, oldVarValue, varValue));
            }
        }
        return varValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue basicGetVarValue()
    {
        return varValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVarValue(AbstractValue newVarValue)
    {
        AbstractValue oldVarValue = varValue;
        varValue = newVarValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TransitionruntimePackage.FIRING_DATA__VAR_VALUE, oldVarValue, varValue));
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
            case TransitionruntimePackage.FIRING_DATA__PLACE_MARKING:
                if (resolve) return getPlaceMarking();
                return basicGetPlaceMarking();
            case TransitionruntimePackage.FIRING_DATA__MS_TERM:
                if (resolve) return getMsTerm();
                return basicGetMsTerm();
            case TransitionruntimePackage.FIRING_DATA__VAR_VALUE:
                if (resolve) return getVarValue();
                return basicGetVarValue();
            case TransitionruntimePackage.FIRING_DATA__VARIABLE:
                if (resolve) return getVariable();
                return basicGetVariable();
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
            case TransitionruntimePackage.FIRING_DATA__PLACE_MARKING:
                setPlaceMarking((PlaceMarking)newValue);
                return;
            case TransitionruntimePackage.FIRING_DATA__MS_TERM:
                setMsTerm((MSTerm)newValue);
                return;
            case TransitionruntimePackage.FIRING_DATA__VAR_VALUE:
                setVarValue((AbstractValue)newValue);
                return;
            case TransitionruntimePackage.FIRING_DATA__VARIABLE:
                setVariable((RuntimeVariable)newValue);
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
            case TransitionruntimePackage.FIRING_DATA__PLACE_MARKING:
                setPlaceMarking((PlaceMarking)null);
                return;
            case TransitionruntimePackage.FIRING_DATA__MS_TERM:
                setMsTerm((MSTerm)null);
                return;
            case TransitionruntimePackage.FIRING_DATA__VAR_VALUE:
                setVarValue((AbstractValue)null);
                return;
            case TransitionruntimePackage.FIRING_DATA__VARIABLE:
                setVariable((RuntimeVariable)null);
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
            case TransitionruntimePackage.FIRING_DATA__PLACE_MARKING:
                return placeMarking != null;
            case TransitionruntimePackage.FIRING_DATA__MS_TERM:
                return msTerm != null;
            case TransitionruntimePackage.FIRING_DATA__VAR_VALUE:
                return varValue != null;
            case TransitionruntimePackage.FIRING_DATA__VARIABLE:
                return variable != null;
        }
        return super.eIsSet(featureID);
    }

} //FiringDataImpl
