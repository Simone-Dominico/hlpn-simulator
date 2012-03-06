/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package variableruntime.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import variableruntime.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class VariableruntimeFactoryImpl extends EFactoryImpl implements VariableruntimeFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static VariableruntimeFactory init()
    {
        try
        {
            VariableruntimeFactory theVariableruntimeFactory = (VariableruntimeFactory)EPackage.Registry.INSTANCE.getEFactory("http://variableruntime/1.0"); 
            if (theVariableruntimeFactory != null)
            {
                return theVariableruntimeFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new VariableruntimeFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariableruntimeFactoryImpl()
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
            case VariableruntimePackage.RUNTIME_VARIABLE: return createRuntimeVariable();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimeVariable createRuntimeVariable()
    {
        RuntimeVariableImpl runtimeVariable = new RuntimeVariableImpl();
        return runtimeVariable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariableruntimePackage getVariableruntimePackage()
    {
        return (VariableruntimePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static VariableruntimePackage getPackage()
    {
        return VariableruntimePackage.eINSTANCE;
    }

} //VariableruntimeFactoryImpl
