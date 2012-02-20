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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

import runtime.AbstractValue;
import runtime.RuntimePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link runtime.impl.AbstractValueImpl#getSort <em>Sort</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractValueImpl extends EObjectImpl implements AbstractValue
{
    /**
     * The cached value of the '{@link #getSort() <em>Sort</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSort()
     * @generated
     * @ordered
     */
    protected Sort sort;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractValueImpl()
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
        return RuntimePackage.Literals.ABSTRACT_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Sort getSort()
    {
        if (sort != null && sort.eIsProxy())
        {
            InternalEObject oldSort = (InternalEObject)sort;
            sort = (Sort)eResolveProxy(oldSort);
            if (sort != oldSort)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, RuntimePackage.ABSTRACT_VALUE__SORT, oldSort, sort));
            }
        }
        return sort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Sort basicGetSort()
    {
        return sort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSort(Sort newSort)
    {
        Sort oldSort = sort;
        sort = newSort;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, RuntimePackage.ABSTRACT_VALUE__SORT, oldSort, sort));
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
            case RuntimePackage.ABSTRACT_VALUE__SORT:
                if (resolve) return getSort();
                return basicGetSort();
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
            case RuntimePackage.ABSTRACT_VALUE__SORT:
                setSort((Sort)newValue);
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
            case RuntimePackage.ABSTRACT_VALUE__SORT:
                setSort((Sort)null);
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
            case RuntimePackage.ABSTRACT_VALUE__SORT:
                return sort != null;
        }
        return super.eIsSet(featureID);
    }

	@Override
    public boolean equals(Object obj)
    {
	    if(this == obj)
	    {
		    return true;
	    }
	    if(obj == null)
	    {
		    return false;
	    }
	    if(!(obj instanceof AbstractValueImpl))
	    {
		    return false;
	    }
	    AbstractValueImpl other = (AbstractValueImpl) obj;
	    if(sort == null)
	    {
		    if(other.sort != null)
		    {
			    return false;
		    }
	    }
	    else if(!sort.equals(other.sort))
	    {
		    return false;
	    }
	    return true;
    }

} //AbstractValueImpl
