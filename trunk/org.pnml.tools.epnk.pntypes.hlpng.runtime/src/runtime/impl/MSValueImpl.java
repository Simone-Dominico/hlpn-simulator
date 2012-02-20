/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import runtime.AbstractValue;
import runtime.MSValue;
import runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MS Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link runtime.impl.MSValueImpl#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MSValueImpl extends AbstractValueImpl implements MSValue
{
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

    // @generated NOT
    @Override
    public String toString()
    {
    	StringBuffer buffer = new StringBuffer();
    	
    	for(AbstractValue value : values.keySet())
    	{
    		buffer.append(values.get(value));
    		buffer.append("`");
    		
    		if(value instanceof MSValue)
    		{
    			buffer.append("(" + value.toString() + ")");
    		}
    		else
    		{
    			buffer.append(value.toString());    			
    		}

    		buffer.append(" ++ ");
    	}
    	
	    return buffer.toString().replaceAll("(.*)\\s+\\+\\+\\s*$", "$1");
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
     * Adds an element to a multiset
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void add(AbstractValue value, int multiplicity)
    {
    	if(getValues().containsKey(value))
        {
    		getValues().put(value, getValues().get(value) + multiplicity);
        }
    	else
    	{
    		getValues().put(value, multiplicity);
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
        	add(key, ms.getValues().get(key));
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
            case RuntimePackage.MS_VALUE__VALUES:
                return values != null && !values.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //MSValueImpl
