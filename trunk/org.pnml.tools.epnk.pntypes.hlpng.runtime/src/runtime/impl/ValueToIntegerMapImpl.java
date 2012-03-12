/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import runtime.AbstractValue;
import runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value To Integer Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link runtime.impl.ValueToIntegerMapImpl#getTypedKey <em>Key</em>}</li>
 *   <li>{@link runtime.impl.ValueToIntegerMapImpl#getTypedValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueToIntegerMapImpl extends EObjectImpl implements BasicEMap.Entry<AbstractValue,EList<AbstractValue>>
{
    /**
     * The cached value of the '{@link #getTypedKey() <em>Key</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypedKey()
     * @generated
     * @ordered
     */
    protected AbstractValue key;

    /**
     * The cached value of the '{@link #getTypedValue() <em>Value</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTypedValue()
     * @generated
     * @ordered
     */
    protected EList<AbstractValue> value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ValueToIntegerMapImpl()
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
        return RuntimePackage.Literals.VALUE_TO_INTEGER_MAP;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue getTypedKey()
    {
        if (key != null && key.eIsProxy())
        {
            InternalEObject oldKey = (InternalEObject)key;
            key = (AbstractValue)eResolveProxy(oldKey);
            if (key != oldKey)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RuntimePackage.VALUE_TO_INTEGER_MAP__KEY, oldKey, key));
            }
        }
        return key;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue basicGetTypedKey()
    {
        return key;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTypedKey(AbstractValue newKey)
    {
        AbstractValue oldKey = key;
        key = newKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.VALUE_TO_INTEGER_MAP__KEY, oldKey, key));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractValue> getTypedValue()
    {
        if (value == null)
        {
            value = new EObjectContainmentEList<AbstractValue>(AbstractValue.class, this, RuntimePackage.VALUE_TO_INTEGER_MAP__VALUE);
        }
        return value;
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
            case RuntimePackage.VALUE_TO_INTEGER_MAP__VALUE:
                return ((InternalEList<?>)getTypedValue()).basicRemove(otherEnd, msgs);
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
            case RuntimePackage.VALUE_TO_INTEGER_MAP__KEY:
                if (resolve) return getTypedKey();
                return basicGetTypedKey();
            case RuntimePackage.VALUE_TO_INTEGER_MAP__VALUE:
                return getTypedValue();
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
            case RuntimePackage.VALUE_TO_INTEGER_MAP__KEY:
                setTypedKey((AbstractValue)newValue);
                return;
            case RuntimePackage.VALUE_TO_INTEGER_MAP__VALUE:
                getTypedValue().clear();
                getTypedValue().addAll((Collection<? extends AbstractValue>)newValue);
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
            case RuntimePackage.VALUE_TO_INTEGER_MAP__KEY:
                setTypedKey((AbstractValue)null);
                return;
            case RuntimePackage.VALUE_TO_INTEGER_MAP__VALUE:
                getTypedValue().clear();
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
            case RuntimePackage.VALUE_TO_INTEGER_MAP__KEY:
                return key != null;
            case RuntimePackage.VALUE_TO_INTEGER_MAP__VALUE:
                return value != null && !value.isEmpty();
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
    public AbstractValue getKey()
    {
        return getTypedKey();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKey(AbstractValue key)
    {
        setTypedKey(key);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractValue> getValue()
    {
        return getTypedValue();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractValue> setValue(EList<AbstractValue> value)
    {
        EList<AbstractValue> oldValue = getValue();
        getTypedValue().clear();
        getTypedValue().addAll(value);
        return oldValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    public EMap<AbstractValue, EList<AbstractValue>> getEMap()
    {
        EObject container = eContainer();
        return container == null ? null : (EMap<AbstractValue, EList<AbstractValue>>)container.eGet(eContainmentFeature());
    }

} //ValueToIntegerMapImpl
