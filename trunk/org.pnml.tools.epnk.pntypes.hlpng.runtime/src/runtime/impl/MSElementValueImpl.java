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

import runtime.AbstractValue;
import runtime.MSElementValue;
import runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MS Element Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link runtime.impl.MSElementValueImpl#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link runtime.impl.MSElementValueImpl#getMsElement <em>Ms Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MSElementValueImpl extends AbstractValueImpl implements MSElementValue
{
    /**
     * The default value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMultiplicity()
     * @generated
     * @ordered
     */
    protected static final int MULTIPLICITY_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMultiplicity()
     * @generated
     * @ordered
     */
    protected int multiplicity = MULTIPLICITY_EDEFAULT;

    /**
     * The cached value of the '{@link #getMsElement() <em>Ms Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMsElement()
     * @generated
     * @ordered
     */
    protected AbstractValue msElement;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MSElementValueImpl()
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
        return RuntimePackage.Literals.MS_ELEMENT_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getMultiplicity()
    {
        return multiplicity;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMultiplicity(int newMultiplicity)
    {
        int oldMultiplicity = multiplicity;
        multiplicity = newMultiplicity;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.MS_ELEMENT_VALUE__MULTIPLICITY, oldMultiplicity, multiplicity));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue getMsElement()
    {
        if (msElement != null && msElement.eIsProxy())
        {
            InternalEObject oldMsElement = (InternalEObject)msElement;
            msElement = (AbstractValue)eResolveProxy(oldMsElement);
            if (msElement != oldMsElement)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RuntimePackage.MS_ELEMENT_VALUE__MS_ELEMENT, oldMsElement, msElement));
            }
        }
        return msElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractValue basicGetMsElement()
    {
        return msElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMsElement(AbstractValue newMsElement)
    {
        AbstractValue oldMsElement = msElement;
        msElement = newMsElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.MS_ELEMENT_VALUE__MS_ELEMENT, oldMsElement, msElement));
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
            case RuntimePackage.MS_ELEMENT_VALUE__MULTIPLICITY:
                return getMultiplicity();
            case RuntimePackage.MS_ELEMENT_VALUE__MS_ELEMENT:
                if (resolve) return getMsElement();
                return basicGetMsElement();
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
            case RuntimePackage.MS_ELEMENT_VALUE__MULTIPLICITY:
                setMultiplicity((Integer)newValue);
                return;
            case RuntimePackage.MS_ELEMENT_VALUE__MS_ELEMENT:
                setMsElement((AbstractValue)newValue);
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
            case RuntimePackage.MS_ELEMENT_VALUE__MULTIPLICITY:
                setMultiplicity(MULTIPLICITY_EDEFAULT);
                return;
            case RuntimePackage.MS_ELEMENT_VALUE__MS_ELEMENT:
                setMsElement((AbstractValue)null);
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
            case RuntimePackage.MS_ELEMENT_VALUE__MULTIPLICITY:
                return multiplicity != MULTIPLICITY_EDEFAULT;
            case RuntimePackage.MS_ELEMENT_VALUE__MS_ELEMENT:
                return msElement != null;
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
        result.append(" (multiplicity: ");
        result.append(multiplicity);
        result.append(')');
        return result.toString();
    }

} //MSElementValueImpl
