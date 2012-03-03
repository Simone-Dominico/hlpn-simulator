/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import runtime.AbstractValue;
import runtime.PlaceMarking;

import transitionruntime.TransitionruntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Place Marking To Value Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link transitionruntime.impl.PlaceMarkingToValueMapImpl#getTypedKey <em>Key</em>}</li>
 *   <li>{@link transitionruntime.impl.PlaceMarkingToValueMapImpl#getTypedValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PlaceMarkingToValueMapImpl extends EObjectImpl implements BasicEMap.Entry<PlaceMarking,AbstractValue>
{
    /**
     * The cached value of the '{@link #getTypedKey() <em>Key</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypedKey()
     * @generated
     * @ordered
     */
    protected PlaceMarking key;

    /**
     * The cached value of the '{@link #getTypedValue() <em>Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypedValue()
     * @generated
     * @ordered
     */
    protected AbstractValue value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PlaceMarkingToValueMapImpl()
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
        return TransitionruntimePackage.Literals.PLACE_MARKING_TO_VALUE_MAP;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PlaceMarking getTypedKey()
    {
        if (key != null && key.eIsProxy())
        {
            InternalEObject oldKey = (InternalEObject)key;
            key = (PlaceMarking)eResolveProxy(oldKey);
            if (key != oldKey)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__KEY, oldKey, key));
            }
        }
        return key;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PlaceMarking basicGetTypedKey()
    {
        return key;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypedKey(PlaceMarking newKey)
    {
        PlaceMarking oldKey = key;
        key = newKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__KEY, oldKey, key));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue getTypedValue()
    {
        if (value != null && value.eIsProxy())
        {
            InternalEObject oldValue = (InternalEObject)value;
            value = (AbstractValue)eResolveProxy(oldValue);
            if (value != oldValue)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__VALUE, oldValue, value));
            }
        }
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue basicGetTypedValue()
    {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypedValue(AbstractValue newValue)
    {
        AbstractValue oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__VALUE, oldValue, value));
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
            case TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__KEY:
                if (resolve) return getTypedKey();
                return basicGetTypedKey();
            case TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__VALUE:
                if (resolve) return getTypedValue();
                return basicGetTypedValue();
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
            case TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__KEY:
                setTypedKey((PlaceMarking)newValue);
                return;
            case TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__VALUE:
                setTypedValue((AbstractValue)newValue);
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
            case TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__KEY:
                setTypedKey((PlaceMarking)null);
                return;
            case TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__VALUE:
                setTypedValue((AbstractValue)null);
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
            case TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__KEY:
                return key != null;
            case TransitionruntimePackage.PLACE_MARKING_TO_VALUE_MAP__VALUE:
                return value != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected int hash = -1;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getHash()
    {
        if (hash == -1)
        {
            Object theKey = getKey();
            hash = (theKey == null ? 0 : theKey.hashCode());
        }
        return hash;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHash(int hash)
    {
        this.hash = hash;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PlaceMarking getKey()
    {
        return getTypedKey();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKey(PlaceMarking key)
    {
        setTypedKey(key);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue getValue()
    {
        return getTypedValue();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue setValue(AbstractValue value)
    {
        AbstractValue oldValue = getValue();
        setTypedValue(value);
        return oldValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    public EMap<PlaceMarking, AbstractValue> getEMap()
    {
        EObject container = eContainer();
        return container == null ? null : (EMap<PlaceMarking, AbstractValue>)container.eGet(eContainmentFeature());
    }

} //PlaceMarkingToValueMapImpl
