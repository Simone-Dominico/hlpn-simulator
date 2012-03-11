/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.pnml.tools.epnk.annotations.netannotations.Annotation;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;

import runtime.AbstractMarking;
import transitionruntime.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see transitionruntime.TransitionruntimePackage
 * @generated
 */
public class TransitionruntimeAdapterFactory extends AdapterFactoryImpl
{
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static TransitionruntimePackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransitionruntimeAdapterFactory()
    {
        if (modelPackage == null)
        {
            modelPackage = TransitionruntimePackage.eINSTANCE;
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
    protected TransitionruntimeSwitch<Adapter> modelSwitch =
        new TransitionruntimeSwitch<Adapter>()
        {
            @Override
            public Adapter caseTransitionMarking(TransitionMarking object)
            {
                return createTransitionMarkingAdapter();
            }
            @Override
            public Adapter caseFiringMode(FiringMode object)
            {
                return createFiringModeAdapter();
            }
            @Override
            public Adapter caseFiringData(FiringData object)
            {
                return createFiringDataAdapter();
            }
            @Override
            public Adapter caseMSTerm(MSTerm object)
            {
                return createMSTermAdapter();
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
            public Adapter caseAbstractMarking(AbstractMarking object)
            {
                return createAbstractMarkingAdapter();
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
     * Creates a new adapter for an object of class '{@link transitionruntime.TransitionMarking <em>Transition Marking</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see transitionruntime.TransitionMarking
     * @generated
     */
    public Adapter createTransitionMarkingAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link transitionruntime.FiringMode <em>Firing Mode</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see transitionruntime.FiringMode
     * @generated
     */
    public Adapter createFiringModeAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link transitionruntime.FiringData <em>Firing Data</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see transitionruntime.FiringData
     * @generated
     */
    public Adapter createFiringDataAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link transitionruntime.MSTerm <em>MS Term</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see transitionruntime.MSTerm
     * @generated
     */
    public Adapter createMSTermAdapter()
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
     * Creates a new adapter for an object of class '{@link runtime.AbstractMarking <em>Abstract Marking</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see runtime.AbstractMarking
     * @generated
     */
    public Adapter createAbstractMarkingAdapter()
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

} //TransitionruntimeAdapterFactory
