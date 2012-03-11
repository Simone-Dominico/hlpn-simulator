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
public class ProductRuntimeFactoryImpl extends EFactoryImpl implements ProductRuntimeFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ProductRuntimeFactory init()
    {
        try
        {
            ProductRuntimeFactory theProductRuntimeFactory = (ProductRuntimeFactory)EPackage.Registry.INSTANCE.getEFactory("http://productruntime/1.0"); 
            if (theProductRuntimeFactory != null)
            {
                return theProductRuntimeFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ProductRuntimeFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProductRuntimeFactoryImpl()
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
            case ProductRuntimePackage.PRODUCT_VALUE: return createProductValue();
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
    public ProductRuntimePackage getProductRuntimePackage()
    {
        return (ProductRuntimePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ProductRuntimePackage getPackage()
    {
        return ProductRuntimePackage.eINSTANCE;
    }

} //ProductRuntimeFactoryImpl
