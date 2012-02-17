/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package productRuntime.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import productRuntime.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProductruntimeFactoryImpl extends EFactoryImpl implements ProductruntimeFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ProductruntimeFactory init()
    {
        try
        {
            ProductruntimeFactory theProductruntimeFactory = (ProductruntimeFactory)EPackage.Registry.INSTANCE.getEFactory("http://productruntime/1.0"); 
            if (theProductruntimeFactory != null)
            {
                return theProductruntimeFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ProductruntimeFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProductruntimeFactoryImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass)
    {
        switch (eClass.getClassifierID())
        {
            case ProductruntimePackage.PRODUCT_VALUE: return createProductValue();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProductValue createProductValue()
    {
        ProductValueImpl productValue = new ProductValueImpl();
        return productValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProductruntimePackage getProductruntimePackage()
    {
        return (ProductruntimePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ProductruntimePackage getPackage()
    {
        return ProductruntimePackage.eINSTANCE;
    }

} //ProductruntimeFactoryImpl
