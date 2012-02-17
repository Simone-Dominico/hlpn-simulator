/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime.util;

import numberRuntime.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import runtime.AbstractValue;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see numberRuntime.NumberruntimePackage
 * @generated
 */
public class NumberruntimeSwitch<T> extends Switch<T>
{
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static NumberruntimePackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NumberruntimeSwitch()
    {
        if (modelPackage == null)
        {
            modelPackage = NumberruntimePackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage)
    {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject)
    {
        switch (classifierID)
        {
            case NumberruntimePackage.NUMBER_VALUE:
            {
                NumberValue numberValue = (NumberValue)theEObject;
                T result = caseNumberValue(numberValue);
                if (result == null) result = caseAbstractValue(numberValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case NumberruntimePackage.INT_VALUE:
            {
                IntValue intValue = (IntValue)theEObject;
                T result = caseIntValue(intValue);
                if (result == null) result = caseNumberValue(intValue);
                if (result == null) result = caseAbstractValue(intValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case NumberruntimePackage.POS_VALUE:
            {
                PosValue posValue = (PosValue)theEObject;
                T result = casePosValue(posValue);
                if (result == null) result = caseNumberValue(posValue);
                if (result == null) result = caseAbstractValue(posValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case NumberruntimePackage.NAT_VALUE:
            {
                NatValue natValue = (NatValue)theEObject;
                T result = caseNatValue(natValue);
                if (result == null) result = caseNumberValue(natValue);
                if (result == null) result = caseAbstractValue(natValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Number Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Number Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNumberValue(NumberValue object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Int Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Int Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIntValue(IntValue object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Pos Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pos Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePosValue(PosValue object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Nat Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Nat Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNatValue(NatValue object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractValue(AbstractValue object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object)
    {
        return null;
    }

} //NumberruntimeSwitch
