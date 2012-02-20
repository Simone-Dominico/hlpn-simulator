/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package productRuntime.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;


import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import productRuntime.ProductValue;
import productRuntime.ProductruntimePackage;

import runtime.AbstractValue;

import runtime.impl.AbstractValueImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Product Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link productRuntime.impl.ProductValueImpl#getComponents <em>Components</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProductValueImpl extends AbstractValueImpl implements ProductValue
{
    /**
     * The cached value of the '{@link #getComponents() <em>Components</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComponents()
     * @generated
     * @ordered
     */
    protected EList<AbstractValue> components;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ProductValueImpl()
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
        return ProductruntimePackage.Literals.PRODUCT_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractValue> getComponents()
    {
        if (components == null)
        {
            components = new EObjectContainmentEList<AbstractValue>(AbstractValue.class, this, ProductruntimePackage.PRODUCT_VALUE__COMPONENTS);
        }
        return components;
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
            case ProductruntimePackage.PRODUCT_VALUE__COMPONENTS:
                return ((InternalEList<?>)getComponents()).basicRemove(otherEnd, msgs);
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
            case ProductruntimePackage.PRODUCT_VALUE__COMPONENTS:
                return getComponents();
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
            case ProductruntimePackage.PRODUCT_VALUE__COMPONENTS:
                getComponents().clear();
                getComponents().addAll((Collection<? extends AbstractValue>)newValue);
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
            case ProductruntimePackage.PRODUCT_VALUE__COMPONENTS:
                getComponents().clear();
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
            case ProductruntimePackage.PRODUCT_VALUE__COMPONENTS:
                return components != null && !components.isEmpty();
        }
        return super.eIsSet(featureID);
    }
    
    // @generated NOT
	@Override
    public int hashCode()
    {
	    final int prime = 31;

	    int result = prime
	            + ((components == null) ? 0 : components.hashCode());
	    return result;
    }

	// @generated NOT
	@Override
    public String toString()
    {
		StringBuffer buffer = new StringBuffer("(");
		for(AbstractValue value : components)
		{
			buffer.append(value.toString() + ", ");
		}
	    return buffer.toString().replaceAll("(.*),\\s+$", "$1") + ")";
    }

} //ProductValueImpl
