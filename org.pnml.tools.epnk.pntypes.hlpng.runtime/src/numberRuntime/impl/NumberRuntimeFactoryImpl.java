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
public class NumberRuntimeFactoryImpl extends EFactoryImpl implements NumberRuntimeFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static NumberRuntimeFactory init()
    {
        try
        {
            NumberRuntimeFactory theNumberRuntimeFactory = (NumberRuntimeFactory)EPackage.Registry.INSTANCE.getEFactory("http://numberruntime/1.0"); 
            if (theNumberRuntimeFactory != null)
            {
                return theNumberRuntimeFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new NumberRuntimeFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NumberRuntimeFactoryImpl()
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
            case NumberRuntimePackage.INT_VALUE: return createIntValue();
            case NumberRuntimePackage.POS_VALUE: return createPosValue();
            case NumberRuntimePackage.NAT_VALUE: return createNatValue();
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
    public NumberRuntimePackage getNumberRuntimePackage()
    {
        return (NumberRuntimePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static NumberRuntimePackage getPackage()
    {
        return NumberRuntimePackage.eINSTANCE;
    }

} //NumberRuntimeFactoryImpl
