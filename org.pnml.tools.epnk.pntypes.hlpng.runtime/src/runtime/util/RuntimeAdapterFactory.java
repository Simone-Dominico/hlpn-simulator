/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.util;

import java.util.Map;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.pnml.tools.epnk.annotations.netannotations.Annotation;
import org.pnml.tools.epnk.annotations.netannotations.NetAnnotation;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;

import runtime.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see runtime.RuntimePackage
 * @generated
 */
public class RuntimeAdapterFactory extends AdapterFactoryImpl
{
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static RuntimePackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimeAdapterFactory()
    {
        if (modelPackage == null)
        {
            modelPackage = RuntimePackage.eINSTANCE;
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
    protected RuntimeSwitch<Adapter> modelSwitch =
        new RuntimeSwitch<Adapter>()
        {
            @Override
            public Adapter caseMSValue(MSValue object)
            {
                return createMSValueAdapter();
            }
            @Override
            public Adapter casePlaceMarking(PlaceMarking object)
            {
                return createPlaceMarkingAdapter();
            }
            @Override
            public Adapter caseNetMarking(NetMarking object)
            {
                return createNetMarkingAdapter();
            }
            @Override
            public Adapter caseValueToIntegerMap(Map.Entry<AbstractValue, Integer> object)
            {
                return createValueToIntegerMapAdapter();
            }
            @Override
            public Adapter caseAbstractValue(AbstractValue object)
            {
                return createAbstractValueAdapter();
            }
            @Override
            public Adapter caseAnnotation(Annotation object)
            {
                return createAnnotationAdapter();
            }
            @Override
            public Adapter caseObjectAnnotation(ObjectAnnotation object)
            {
                return createObjectAnnotationAdapter();
            }
            @Override
            public Adapter caseNetAnnotation(NetAnnotation object)
            {
                return createNetAnnotationAdapter();
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
     * Creates a new adapter for an object of class '{@link runtime.MSValue <em>MS Value</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see runtime.MSValue
     * @generated
     */
    public Adapter createMSValueAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link runtime.PlaceMarking <em>Place Marking</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see runtime.PlaceMarking
     * @generated
     */
    public Adapter createPlaceMarkingAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link runtime.NetMarking <em>Net Marking</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see runtime.NetMarking
     * @generated
     */
    public Adapter createNetMarkingAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Value To Integer Map</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createValueToIntegerMapAdapter()
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
     * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.annotations.netannotations.Annotation <em>Annotation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.pnml.tools.epnk.annotations.netannotations.Annotation
     * @generated
     */
    public Adapter createAnnotationAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation <em>Object Annotation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation
     * @generated
     */
    public Adapter createObjectAnnotationAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.pnml.tools.epnk.annotations.netannotations.NetAnnotation <em>Net Annotation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.pnml.tools.epnk.annotations.netannotations.NetAnnotation
     * @generated
     */
    public Adapter createNetAnnotationAdapter()
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

} //RuntimeAdapterFactory
