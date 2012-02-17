/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.MultiSetSort;

import runtime.AbstractValue;
import runtime.MSElementValue;
import runtime.MSValue;
import runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MS Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link runtime.impl.MSValueImpl#getMsSort <em>Ms Sort</em>}</li>
 *   <li>{@link runtime.impl.MSValueImpl#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MSValueImpl extends AbstractValueImpl implements MSValue
{
	/**
     * The cached value of the '{@link #getMsSort() <em>Ms Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMsSort()
     * @generated
     * @ordered
     */
    protected MultiSetSort msSort;

    /**
     * The cached value of the '{@link #getValues() <em>Values</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValues()
     * @generated
     * @ordered
     */
    protected EMap<AbstractValue, Integer> values;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MSValueImpl()
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
        return RuntimePackage.Literals.MS_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MultiSetSort getMsSort()
    {
        if (msSort != null && msSort.eIsProxy())
        {
            InternalEObject oldMsSort = (InternalEObject)msSort;
            msSort = (MultiSetSort)eResolveProxy(oldMsSort);
            if (msSort != oldMsSort)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RuntimePackage.MS_VALUE__MS_SORT, oldMsSort, msSort));
            }
        }
        return msSort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MultiSetSort basicGetMsSort()
    {
        return msSort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMsSort(MultiSetSort newMsSort)
    {
        MultiSetSort oldMsSort = msSort;
        msSort = newMsSort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.MS_VALUE__MS_SORT, oldMsSort, msSort));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<AbstractValue, Integer> getValues()
    {
        if (values == null)
        {
            values = new EcoreEMap<AbstractValue,Integer>(RuntimePackage.Literals.VALUE_TO_INTEGER_MAP, ValueToIntegerMapImpl.class, this, RuntimePackage.MS_VALUE__VALUES);
        }
        return values;
    }

    /**
     * <!-- begin-user-doc -->
     * Returns a multiplicity of the multiset element
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public int getMultiplicity(AbstractValue value)
    {
        if(getValues().containsKey(value))
        {
        	return getValues().get(value);
        }
        return 0;
    }

    /**
     * <!-- begin-user-doc -->
     * Adds a value to the multiset:
     *  1. Chaeck if it is an instance of MSElementValue
     * 	2. Checks if it is already presented in the multiset 
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void add(AbstractValue value)
    {
    	if(value instanceof MSElementValue)
    	{
    		MSElementValue elemValue = (MSElementValue) value;
    		updateValue(getValues(), elemValue.getMsElement(), elemValue.getMultiplicity());
    	}
    	else
    	{
    		updateValue(getValues(), value, 1);
    	}
    }
    
    /**
     * <!-- begin-user-doc -->
     * Appends a shallow copy of the multiset
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void append(MSValue ms)
    {    	
        for(AbstractValue key : ms.getValues().keySet())
        {
        	updateValue(getValues(), key, ms.getValues().get(key));
        }
    }
    
    private static void updateValue(EMap<AbstractValue, Integer> values, AbstractValue value,
    		Integer multiplicity)
    {
    	if(values.containsKey(value))
        {
        	values.put(value, values.get(value) + multiplicity);
        }
    	else
    	{
    		values.put(value, multiplicity);
    	}
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
            case RuntimePackage.MS_VALUE__VALUES:
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
            case RuntimePackage.MS_VALUE__MS_SORT:
                if (resolve) return getMsSort();
                return basicGetMsSort();
            case RuntimePackage.MS_VALUE__VALUES:
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
            case RuntimePackage.MS_VALUE__MS_SORT:
                setMsSort((MultiSetSort)newValue);
                return;
            case RuntimePackage.MS_VALUE__VALUES:
                ((EStructuralFeature.Setting)getValues()).set(newValue);
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
            case RuntimePackage.MS_VALUE__MS_SORT:
                setMsSort((MultiSetSort)null);
                return;
            case RuntimePackage.MS_VALUE__VALUES:
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
            case RuntimePackage.MS_VALUE__MS_SORT:
                return msSort != null;
            case RuntimePackage.MS_VALUE__VALUES:
                return values != null && !values.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //MSValueImpl
