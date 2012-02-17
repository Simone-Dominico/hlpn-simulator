/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime.impl;

import numberRuntime.IntValue;
import numberRuntime.NumberruntimePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Int Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link numberRuntime.impl.IntValueImpl#getIntSort <em>Int Sort</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntValueImpl extends NumberValueImpl implements IntValue
{
    /**
     * The cached value of the '{@link #getIntSort() <em>Int Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntSort()
     * @generated
     * @ordered
     */
    protected org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Integer intSort;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IntValueImpl()
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
        return NumberruntimePackage.Literals.INT_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Integer getIntSort()
    {
        if (intSort != null && intSort.eIsProxy())
        {
            InternalEObject oldIntSort = (InternalEObject)intSort;
            intSort = (org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Integer)eResolveProxy(oldIntSort);
            if (intSort != oldIntSort)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, NumberruntimePackage.INT_VALUE__INT_SORT, oldIntSort, intSort));
            }
        }
        return intSort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Integer basicGetIntSort()
    {
        return intSort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIntSort(org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Integer newIntSort)
    {
        org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Integer oldIntSort = intSort;
        intSort = newIntSort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NumberruntimePackage.INT_VALUE__INT_SORT, oldIntSort, intSort));
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
            case NumberruntimePackage.INT_VALUE__INT_SORT:
                if (resolve) return getIntSort();
                return basicGetIntSort();
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
            case NumberruntimePackage.INT_VALUE__INT_SORT:
                setIntSort((org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Integer)newValue);
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
            case NumberruntimePackage.INT_VALUE__INT_SORT:
                setIntSort((org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Integer)null);
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
            case NumberruntimePackage.INT_VALUE__INT_SORT:
                return intSort != null;
        }
        return super.eIsSet(featureID);
    }

} //IntValueImpl
