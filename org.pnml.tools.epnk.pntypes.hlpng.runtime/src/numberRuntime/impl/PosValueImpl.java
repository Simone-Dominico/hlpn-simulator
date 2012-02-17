/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime.impl;

import numberRuntime.NumberruntimePackage;
import numberRuntime.PosValue;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.Positive;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pos Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link numberRuntime.impl.PosValueImpl#getPosSort <em>Pos Sort</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PosValueImpl extends NumberValueImpl implements PosValue
{
    /**
     * The cached value of the '{@link #getPosSort() <em>Pos Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPosSort()
     * @generated
     * @ordered
     */
    protected Positive posSort;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PosValueImpl()
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
        return NumberruntimePackage.Literals.POS_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Positive getPosSort()
    {
        if (posSort != null && posSort.eIsProxy())
        {
            InternalEObject oldPosSort = (InternalEObject)posSort;
            posSort = (Positive)eResolveProxy(oldPosSort);
            if (posSort != oldPosSort)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, NumberruntimePackage.POS_VALUE__POS_SORT, oldPosSort, posSort));
            }
        }
        return posSort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Positive basicGetPosSort()
    {
        return posSort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPosSort(Positive newPosSort)
    {
        Positive oldPosSort = posSort;
        posSort = newPosSort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NumberruntimePackage.POS_VALUE__POS_SORT, oldPosSort, posSort));
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
            case NumberruntimePackage.POS_VALUE__POS_SORT:
                if (resolve) return getPosSort();
                return basicGetPosSort();
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
            case NumberruntimePackage.POS_VALUE__POS_SORT:
                setPosSort((Positive)newValue);
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
            case NumberruntimePackage.POS_VALUE__POS_SORT:
                setPosSort((Positive)null);
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
            case NumberruntimePackage.POS_VALUE__POS_SORT:
                return posSort != null;
        }
        return super.eIsSet(featureID);
    }

} //PosValueImpl
