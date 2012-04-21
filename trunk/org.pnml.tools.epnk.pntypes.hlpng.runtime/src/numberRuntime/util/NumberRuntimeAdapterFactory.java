/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime.util;

import numberRuntime.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import runtime.AbstractValue;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see numberRuntime.NumberRuntimePackage
 * @generated
 */
public class NumberRuntimeAdapterFactory extends AdapterFactoryImpl
{
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static NumberRuntimePackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NumberRuntimeAdapterFactory()
    {
        if (modelPackage == null)
        {
            modelPackage = NumberRuntimePackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object)
    {
        if (object == modelPackage)
        {
            return true;
        }
        if (object instanceof EObject)
        {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NumberRuntimeSwitch<Adapter> modelSwitch =
        new NumberRuntimeSwitch<Adapter>()
        {
            @Override
            public Adapter caseNumberValue(NumberValue object)
            {
                return createNumberValueAdapter();
            }
            @Override
            public Adapter caseIntValue(IntValue object)
            {
                return createIntValueAdapter();
            }
            @Override
            public Adapter casePosValue(PosValue object)
            {
                return createPosValueAdapter();
            }
            @Override
            public Adapter caseNatValue(NatValue object)
            {
                return createNatValueAdapter();
            }
            @Override
            public Adapter caseAbstractValue(AbstractValue object)
            {
                return createAbstractValueAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object)
            {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target)
    {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link numberRuntime.NumberValue <em>Number Value</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see numberRuntime.NumberValue
     * @generated
     */
    public Adapter createNumberValueAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link numberRuntime.IntValue <em>Int Value</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see numberRuntime.IntValue
     * @generated
     */
    public Adapter createIntValueAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link numberRuntime.PosValue <em>Pos Value</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see numberRuntime.PosValue
     * @generated
     */
    public Adapter createPosValueAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link numberRuntime.NatValue <em>Nat Value</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see numberRuntime.NatValue
     * @generated
     */
    public Adapter createNatValueAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link runtime.AbstractValue <em>Abstract Value</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see runtime.AbstractValue
     * @generated
     */
    public Adapter createAbstractValueAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter()
    {
        return null;
    }

} //NumberRuntimeAdapterFactory