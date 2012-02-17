/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime.impl;

import numberRuntime.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NumberruntimeFactoryImpl extends EFactoryImpl implements NumberruntimeFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static NumberruntimeFactory init()
    {
        try
        {
            NumberruntimeFactory theNumberruntimeFactory = (NumberruntimeFactory)EPackage.Registry.INSTANCE.getEFactory("http://numberruntime/1.0"); 
            if (theNumberruntimeFactory != null)
            {
                return theNumberruntimeFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new NumberruntimeFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NumberruntimeFactoryImpl()
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
            case NumberruntimePackage.INT_VALUE: return createIntValue();
            case NumberruntimePackage.POS_VALUE: return createPosValue();
            case NumberruntimePackage.NAT_VALUE: return createNatValue();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntValue createIntValue()
    {
        IntValueImpl intValue = new IntValueImpl();
        return intValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PosValue createPosValue()
    {
        PosValueImpl posValue = new PosValueImpl();
        return posValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NatValue createNatValue()
    {
        NatValueImpl natValue = new NatValueImpl();
        return natValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NumberruntimePackage getNumberruntimePackage()
    {
        return (NumberruntimePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static NumberruntimePackage getPackage()
    {
        return NumberruntimePackage.eINSTANCE;
    }

} //NumberruntimeFactoryImpl
