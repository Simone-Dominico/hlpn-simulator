/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Place;

import runtime.MSValue;
import runtime.PlaceMarking;
import runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Place Marking</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link runtime.impl.PlaceMarkingImpl#getMsValue <em>Ms Value</em>}</li>
 *   <li>{@link runtime.impl.PlaceMarkingImpl#getPlace <em>Place</em>}</li>
 *   <li>{@link runtime.impl.PlaceMarkingImpl#isDirty <em>Dirty</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PlaceMarkingImpl extends AbstractMarkingImpl implements PlaceMarking
{
    /**
     * The cached value of the '{@link #getMsValue() <em>Ms Value</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMsValue()
     * @generated
     * @ordered
     */
    protected MSValue msValue;

    /**
     * The cached value of the '{@link #getPlace() <em>Place</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPlace()
     * @generated
     * @ordered
     */
    protected Place place;

    /**
     * The default value of the '{@link #isDirty() <em>Dirty</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDirty()
     * @generated
     * @ordered
     */
    protected static final boolean DIRTY_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDirty() <em>Dirty</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDirty()
     * @generated
     * @ordered
     */
    protected boolean dirty = DIRTY_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PlaceMarkingImpl()
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
        return RuntimePackage.Literals.PLACE_MARKING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MSValue getMsValue()
    {
        if (msValue != null && msValue.eIsProxy())
        {
            InternalEObject oldMsValue = (InternalEObject)msValue;
            msValue = (MSValue)eResolveProxy(oldMsValue);
            if (msValue != oldMsValue)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RuntimePackage.PLACE_MARKING__MS_VALUE, oldMsValue, msValue));
            }
        }
        return msValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MSValue basicGetMsValue()
    {
        return msValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMsValue(MSValue newMsValue)
    {
        MSValue oldMsValue = msValue;
        msValue = newMsValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.PLACE_MARKING__MS_VALUE, oldMsValue, msValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Place getPlace()
    {
        if (place != null && place.eIsProxy())
        {
            InternalEObject oldPlace = (InternalEObject)place;
            place = (Place)eResolveProxy(oldPlace);
            if (place != oldPlace)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RuntimePackage.PLACE_MARKING__PLACE, oldPlace, place));
            }
        }
        return place;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Place basicGetPlace()
    {
        return place;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPlace(Place newPlace)
    {
        Place oldPlace = place;
        place = newPlace;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.PLACE_MARKING__PLACE, oldPlace, place));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDirty()
    {
        return dirty;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDirty(boolean newDirty)
    {
        boolean oldDirty = dirty;
        dirty = newDirty;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.PLACE_MARKING__DIRTY, oldDirty, dirty));
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
            case RuntimePackage.PLACE_MARKING__MS_VALUE:
                if (resolve) return getMsValue();
                return basicGetMsValue();
            case RuntimePackage.PLACE_MARKING__PLACE:
                if (resolve) return getPlace();
                return basicGetPlace();
            case RuntimePackage.PLACE_MARKING__DIRTY:
                return isDirty();
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
            case RuntimePackage.PLACE_MARKING__MS_VALUE:
                setMsValue((MSValue)newValue);
                return;
            case RuntimePackage.PLACE_MARKING__PLACE:
                setPlace((Place)newValue);
                return;
            case RuntimePackage.PLACE_MARKING__DIRTY:
                setDirty((Boolean)newValue);
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
            case RuntimePackage.PLACE_MARKING__MS_VALUE:
                setMsValue((MSValue)null);
                return;
            case RuntimePackage.PLACE_MARKING__PLACE:
                setPlace((Place)null);
                return;
            case RuntimePackage.PLACE_MARKING__DIRTY:
                setDirty(DIRTY_EDEFAULT);
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
            case RuntimePackage.PLACE_MARKING__MS_VALUE:
                return msValue != null;
            case RuntimePackage.PLACE_MARKING__PLACE:
                return place != null;
            case RuntimePackage.PLACE_MARKING__DIRTY:
                return dirty != DIRTY_EDEFAULT;
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
        result.append(" (dirty: ");
        result.append(dirty);
        result.append(')');
        return result.toString();
    }

} //PlaceMarkingImpl
