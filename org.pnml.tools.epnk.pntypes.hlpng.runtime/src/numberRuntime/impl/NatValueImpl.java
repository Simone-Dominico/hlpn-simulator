/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime.impl;

import numberRuntime.NatValue;
import numberRuntime.NumberruntimePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Natural;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Nat Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link numberRuntime.impl.NatValueImpl#getNatSort <em>Nat Sort</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NatValueImpl extends NumberValueImpl implements NatValue
{
    /**
     * The cached value of the '{@link #getNatSort() <em>Nat Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNatSort()
     * @generated
     * @ordered
     */
    protected Natural natSort;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NatValueImpl()
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
        return NumberruntimePackage.Literals.NAT_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Natural getNatSort()
    {
        if (natSort != null && natSort.eIsProxy())
        {
            InternalEObject oldNatSort = (InternalEObject)natSort;
            natSort = (Natural)eResolveProxy(oldNatSort);
            if (natSort != oldNatSort)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, NumberruntimePackage.NAT_VALUE__NAT_SORT, oldNatSort, natSort));
            }
        }
        return natSort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Natural basicGetNatSort()
    {
        return natSort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNatSort(Natural newNatSort)
    {
        Natural oldNatSort = natSort;
        natSort = newNatSort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NumberruntimePackage.NAT_VALUE__NAT_SORT, oldNatSort, natSort));
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
            case NumberruntimePackage.NAT_VALUE__NAT_SORT:
                if (resolve) return getNatSort();
                return basicGetNatSort();
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
            case NumberruntimePackage.NAT_VALUE__NAT_SORT:
                setNatSort((Natural)newValue);
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
            case NumberruntimePackage.NAT_VALUE__NAT_SORT:
                setNatSort((Natural)null);
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
            case NumberruntimePackage.NAT_VALUE__NAT_SORT:
                return natSort != null;
        }
        return super.eIsSet(featureID);
    }

} //NatValueImpl
