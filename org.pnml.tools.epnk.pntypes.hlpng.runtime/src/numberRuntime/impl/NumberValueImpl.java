/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime.impl;

import java.util.Collection;

import numberRuntime.NumberValue;
import numberRuntime.NumberruntimePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

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
     * The cached value of the '{@link #getN() <em>N</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getN()
     * @generated
     * @ordered
     */
    protected EList<Integer> n;

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
    public EList<Integer> getN()
    {
        if (n == null)
        {
            n = new EDataTypeUniqueEList<Integer>(Integer.class, this, NumberruntimePackage.NUMBER_VALUE__N);
        }
        return n;
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
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case NumberruntimePackage.NUMBER_VALUE__N:
                getN().clear();
                getN().addAll((Collection<? extends Integer>)newValue);
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
    @Override
    public void eUnset(int featureID)
    {
        switch (featureID)
        {
            case NumberruntimePackage.NUMBER_VALUE__N:
                getN().clear();
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
                return n != null && !n.isEmpty();
            case NumberruntimePackage.NUMBER_VALUE__NUM_SORT:
                return numSort != null;
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
        result.append(" (n: ");
        result.append(n);
        result.append(')');
        return result.toString();
    }

} //NumberValueImpl
