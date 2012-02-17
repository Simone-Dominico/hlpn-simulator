/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.impl;

import java.util.Map;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import runtime.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RuntimeFactoryImpl extends EFactoryImpl implements RuntimeFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static RuntimeFactory init()
    {
        try
        {
            RuntimeFactory theRuntimeFactory = (RuntimeFactory)EPackage.Registry.INSTANCE.getEFactory("http://runtime/1.0"); 
            if (theRuntimeFactory != null)
            {
                return theRuntimeFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new RuntimeFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimeFactoryImpl()
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
            case RuntimePackage.MS_VALUE: return createMSValue();
            case RuntimePackage.PLACE_MARKING: return createPlaceMarking();
            case RuntimePackage.NET_MARKING: return createNetMarking();
            case RuntimePackage.MS_ELEMENT_VALUE: return createMSElementValue();
            case RuntimePackage.VALUE_TO_INTEGER_MAP: return (EObject)createValueToIntegerMap();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MSValue createMSValue()
    {
        MSValueImpl msValue = new MSValueImpl();
        return msValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PlaceMarking createPlaceMarking()
    {
        PlaceMarkingImpl placeMarking = new PlaceMarkingImpl();
        return placeMarking;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NetMarking createNetMarking()
    {
        NetMarkingImpl netMarking = new NetMarkingImpl();
        return netMarking;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MSElementValue createMSElementValue()
    {
        MSElementValueImpl msElementValue = new MSElementValueImpl();
        return msElementValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map.Entry<AbstractValue, Integer> createValueToIntegerMap()
    {
        ValueToIntegerMapImpl valueToIntegerMap = new ValueToIntegerMapImpl();
        return valueToIntegerMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimePackage getRuntimePackage()
    {
        return (RuntimePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static RuntimePackage getPackage()
    {
        return RuntimePackage.eINSTANCE;
    }

} //RuntimeFactoryImpl
