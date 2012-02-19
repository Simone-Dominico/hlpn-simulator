/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime.impl;

import numberRuntime.NumberValue;
import numberRuntime.NumberruntimePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import runtime.impl.AbstractValueImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Number Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link numberRuntime.impl.NumberValueImpl#getN <em>N</em>}</li>
 *   <li>{@link numberRuntime.impl.NumberValueImpl#getNumSort <em>Num Sort</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class NumberValueImpl extends AbstractValueImpl implements NumberValue
{
    /**
     * The default value of the '{@link #getN() <em>N</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getN()
     * @generated
     * @ordered
     */
    protected static final int N_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getN() <em>N</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getN()
     * @generated
     * @ordered
     */
    protected int n = N_EDEFAULT;

    /**
     * The cached value of the '{@link #getNumSort() <em>Num Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNumSort()
     * @generated
     * @ordered
     */
    protected org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number numSort;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NumberValueImpl()
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
        return NumberruntimePackage.Literals.NUMBER_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getN()
    {
        return n;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setN(int newN)
    {
        int oldN = n;
        n = newN;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NumberruntimePackage.NUMBER_VALUE__N, oldN, n));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number getNumSort()
    {
        if (numSort != null && numSort.eIsProxy())
        {
            InternalEObject oldNumSort = (InternalEObject)numSort;
            numSort = (org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number)eResolveProxy(oldNumSort);
            if (numSort != oldNumSort)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, NumberruntimePackage.NUMBER_VALUE__NUM_SORT, oldNumSort, numSort));
            }
        }
        return numSort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number basicGetNumSort()
    {
        return numSort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNumSort(org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number newNumSort)
    {
        org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number oldNumSort = numSort;
        numSort = newNumSort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NumberruntimePackage.NUMBER_VALUE__NUM_SORT, oldNumSort, numSort));
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
            case NumberruntimePackage.NUMBER_VALUE__N:
                return getN();
            case NumberruntimePackage.NUMBER_VALUE__NUM_SORT:
                if (resolve) return getNumSort();
                return basicGetNumSort();
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
            case NumberruntimePackage.NUMBER_VALUE__N:
                setN((Integer)newValue);
                return;
            case NumberruntimePackage.NUMBER_VALUE__NUM_SORT:
                setNumSort((org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */


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
            case NumberruntimePackage.NUMBER_VALUE__N:
                setN(N_EDEFAULT);
                return;
            case NumberruntimePackage.NUMBER_VALUE__NUM_SORT:
                setNumSort((org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Number)null);
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
            case NumberruntimePackage.NUMBER_VALUE__N:
                return n != N_EDEFAULT;
            case NumberruntimePackage.NUMBER_VALUE__NUM_SORT:
                return numSort != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    @Override
    public String toString()
    {
        return String.valueOf(n);
    }

	@Override
    public int hashCode()
    {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + n;
	    return result;
    }

	@Override
    public boolean equals(Object obj)
    {
	    if(this == obj)
		    return true;
	    if(obj == null)
		    return false;
	    if(getClass() != obj.getClass())
		    return false;
	    NumberValueImpl other = (NumberValueImpl) obj;
	    if(n != other.n)
		    return false;
	    return true;
    }

} //NumberValueImpl
