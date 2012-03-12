/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package runtime.util;

import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.pnml.tools.epnk.annotations.netannotations.Annotation;
import org.pnml.tools.epnk.annotations.netannotations.NetAnnotation;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;

import runtime.*;

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
 * @see runtime.RuntimePackage
 * @generated
 */
public class RuntimeSwitch<T> extends Switch<T>
{
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static RuntimePackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimeSwitch()
    {
        if (modelPackage == null)
        {
            modelPackage = RuntimePackage.eINSTANCE;
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
            case RuntimePackage.MS_VALUE:
            {
                MSValue msValue = (MSValue)theEObject;
                T result = caseMSValue(msValue);
                if (result == null) result = caseAbstractValue(msValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RuntimePackage.PLACE_MARKING:
            {
                PlaceMarking placeMarking = (PlaceMarking)theEObject;
                T result = casePlaceMarking(placeMarking);
                if (result == null) result = caseAbstractMarking(placeMarking);
                if (result == null) result = caseObjectAnnotation(placeMarking);
                if (result == null) result = caseAnnotation(placeMarking);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RuntimePackage.NET_MARKING:
            {
                NetMarking netMarking = (NetMarking)theEObject;
                T result = caseNetMarking(netMarking);
                if (result == null) result = caseNetAnnotation(netMarking);
                if (result == null) result = caseAnnotation(netMarking);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RuntimePackage.VALUE_TO_INTEGER_MAP:
            {
                @SuppressWarnings("unchecked") Map.Entry<AbstractValue, EList<AbstractValue>> valueToIntegerMap = (Map.Entry<AbstractValue, EList<AbstractValue>>)theEObject;
                T result = caseValueToIntegerMap(valueToIntegerMap);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RuntimePackage.ABSTRACT_VALUE:
            {
                AbstractValue abstractValue = (AbstractValue)theEObject;
                T result = caseAbstractValue(abstractValue);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RuntimePackage.RUNTIME_VARIABLE:
            {
                RuntimeVariable runtimeVariable = (RuntimeVariable)theEObject;
                T result = caseRuntimeVariable(runtimeVariable);
                if (result == null) result = caseAbstractValue(runtimeVariable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case RuntimePackage.ABSTRACT_MARKING:
            {
                AbstractMarking abstractMarking = (AbstractMarking)theEObject;
                T result = caseAbstractMarking(abstractMarking);
                if (result == null) result = caseObjectAnnotation(abstractMarking);
                if (result == null) result = caseAnnotation(abstractMarking);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>MS Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>MS Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMSValue(MSValue object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Place Marking</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Place Marking</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePlaceMarking(PlaceMarking object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Net Marking</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Net Marking</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNetMarking(NetMarking object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Value To Integer Map</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Value To Integer Map</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseValueToIntegerMap(Map.Entry<AbstractValue, EList<AbstractValue>> object)
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
     * Returns the result of interpreting the object as an instance of '<em>Variable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRuntimeVariable(RuntimeVariable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Marking</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Marking</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractMarking(AbstractMarking object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Annotation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Annotation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAnnotation(Annotation object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Object Annotation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Object Annotation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseObjectAnnotation(ObjectAnnotation object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Net Annotation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Net Annotation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNetAnnotation(NetAnnotation object)
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

} //RuntimeSwitch
