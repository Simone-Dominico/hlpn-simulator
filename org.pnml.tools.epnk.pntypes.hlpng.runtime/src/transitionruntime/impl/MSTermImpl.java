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

import transitionruntime.MSTerm;
import transitionruntime.TransitionruntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MS Term</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link transitionruntime.impl.MSTermImpl#getPlaceId <em>Place Id</em>}</li>
 *   <li>{@link transitionruntime.impl.MSTermImpl#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link transitionruntime.impl.MSTermImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MSTermImpl extends EObjectImpl implements MSTerm
{
    /**
     * The default value of the '{@link #getPlaceId() <em>Place Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPlaceId()
     * @generated
     * @ordered
     */
    protected static final String PLACE_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPlaceId() <em>Place Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPlaceId()
     * @generated
     * @ordered
     */
    protected String placeId = PLACE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMultiplicity()
     * @generated
     * @ordered
     */
    protected static final int MULTIPLICITY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMultiplicity()
     * @generated
     * @ordered
     */
    protected int multiplicity = MULTIPLICITY_EDEFAULT;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected AbstractValue value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MSTermImpl()
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
        return TransitionruntimePackage.Literals.MS_TERM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPlaceId()
    {
        return placeId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPlaceId(String newPlaceId)
    {
        String oldPlaceId = placeId;
        placeId = newPlaceId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TransitionruntimePackage.MS_TERM__PLACE_ID, oldPlaceId, placeId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getMultiplicity()
    {
        return multiplicity;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMultiplicity(int newMultiplicity)
    {
        int oldMultiplicity = multiplicity;
        multiplicity = newMultiplicity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TransitionruntimePackage.MS_TERM__MULTIPLICITY, oldMultiplicity, multiplicity));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue getValue()
    {
        if (value != null && value.eIsProxy())
        {
            InternalEObject oldValue = (InternalEObject)value;
            value = (AbstractValue)eResolveProxy(oldValue);
            if (value != oldValue)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TransitionruntimePackage.MS_TERM__VALUE, oldValue, value));
            }
        }
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue basicGetValue()
    {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setValue(AbstractValue newValue)
    {
        AbstractValue oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TransitionruntimePackage.MS_TERM__VALUE, oldValue, value));
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
            case TransitionruntimePackage.MS_TERM__PLACE_ID:
                return getPlaceId();
            case TransitionruntimePackage.MS_TERM__MULTIPLICITY:
                return getMultiplicity();
            case TransitionruntimePackage.MS_TERM__VALUE:
                if (resolve) return getValue();
                return basicGetValue();
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
            case TransitionruntimePackage.MS_TERM__PLACE_ID:
                setPlaceId((String)newValue);
                return;
            case TransitionruntimePackage.MS_TERM__MULTIPLICITY:
                setMultiplicity((Integer)newValue);
                return;
            case TransitionruntimePackage.MS_TERM__VALUE:
                setValue((AbstractValue)newValue);
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
            case TransitionruntimePackage.MS_TERM__PLACE_ID:
                setPlaceId(PLACE_ID_EDEFAULT);
                return;
            case TransitionruntimePackage.MS_TERM__MULTIPLICITY:
                setMultiplicity(MULTIPLICITY_EDEFAULT);
                return;
            case TransitionruntimePackage.MS_TERM__VALUE:
                setValue((AbstractValue)null);
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
            case TransitionruntimePackage.MS_TERM__PLACE_ID:
                return PLACE_ID_EDEFAULT == null ? placeId != null : !PLACE_ID_EDEFAULT.equals(placeId);
            case TransitionruntimePackage.MS_TERM__MULTIPLICITY:
                return multiplicity != MULTIPLICITY_EDEFAULT;
            case TransitionruntimePackage.MS_TERM__VALUE:
                return value != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString()
    {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (placeId: ");
        result.append(placeId);
        result.append(", multiplicity: ");
        result.append(multiplicity);
        result.append(')');
        return result.toString();
    }

} //MSTermImpl
