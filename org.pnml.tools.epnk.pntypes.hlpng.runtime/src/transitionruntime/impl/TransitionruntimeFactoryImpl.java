/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import transitionruntime.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TransitionruntimeFactoryImpl extends EFactoryImpl implements TransitionruntimeFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static TransitionruntimeFactory init()
    {
        try
        {
            TransitionruntimeFactory theTransitionruntimeFactory = (TransitionruntimeFactory)EPackage.Registry.INSTANCE.getEFactory("http://transitionruntime/1.0"); 
            if (theTransitionruntimeFactory != null)
            {
                return theTransitionruntimeFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TransitionruntimeFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransitionruntimeFactoryImpl()
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
            case TransitionruntimePackage.TRANSITION_MARKING: return createTransitionMarking();
            case TransitionruntimePackage.FIRING_MODE: return createFiringMode();
            case TransitionruntimePackage.FIRING_DATA: return createFiringData();
            case TransitionruntimePackage.MS_TERM: return createMSTerm();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransitionMarking createTransitionMarking()
    {
        TransitionMarkingImpl transitionMarking = new TransitionMarkingImpl();
        return transitionMarking;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FiringMode createFiringMode()
    {
        FiringModeImpl firingMode = new FiringModeImpl();
        return firingMode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FiringData createFiringData()
    {
        FiringDataImpl firingData = new FiringDataImpl();
        return firingData;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MSTerm createMSTerm()
    {
        MSTermImpl msTerm = new MSTermImpl();
        return msTerm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransitionruntimePackage getTransitionruntimePackage()
    {
        return (TransitionruntimePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static TransitionruntimePackage getPackage()
    {
        return TransitionruntimePackage.eINSTANCE;
    }

} //TransitionruntimeFactoryImpl
