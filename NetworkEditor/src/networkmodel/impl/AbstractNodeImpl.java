/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package networkmodel.impl;

import java.util.Collection;

import networkmodel.AbstractEdge;
import networkmodel.AbstractNode;
import networkmodel.NetworkmodelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link networkmodel.impl.AbstractNodeImpl#getId <em>Id</em>}</li>
 *   <li>{@link networkmodel.impl.AbstractNodeImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link networkmodel.impl.AbstractNodeImpl#getOut <em>Out</em>}</li>
 *   <li>{@link networkmodel.impl.AbstractNodeImpl#getIn <em>In</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractNodeImpl extends NetworkObjectImpl implements AbstractNode
{
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final int ID_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected int id = ID_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = LABEL_EDEFAULT;

    /**
     * The cached value of the '{@link #getOut() <em>Out</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOut()
     * @generated
     * @ordered
     */
    protected EList<AbstractEdge> out;

    /**
     * The cached value of the '{@link #getIn() <em>In</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIn()
     * @generated
     * @ordered
     */
    protected EList<AbstractEdge> in;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractNodeImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return NetworkmodelPackage.Literals.ABSTRACT_NODE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getId()
    {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(int newId)
    {
        int oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NetworkmodelPackage.ABSTRACT_NODE__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabel(String newLabel)
    {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NetworkmodelPackage.ABSTRACT_NODE__LABEL, oldLabel, label));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractEdge> getOut()
    {
        if (out == null)
        {
            out = new EObjectWithInverseResolvingEList<AbstractEdge>(AbstractEdge.class, this, NetworkmodelPackage.ABSTRACT_NODE__OUT, NetworkmodelPackage.ABSTRACT_EDGE__SOURCE);
        }
        return out;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AbstractEdge> getIn()
    {
        if (in == null)
        {
            in = new EObjectWithInverseResolvingEList<AbstractEdge>(AbstractEdge.class, this, NetworkmodelPackage.ABSTRACT_NODE__IN, NetworkmodelPackage.ABSTRACT_EDGE__TARGET);
        }
        return in;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case NetworkmodelPackage.ABSTRACT_NODE__OUT:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getOut()).basicAdd(otherEnd, msgs);
            case NetworkmodelPackage.ABSTRACT_NODE__IN:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getIn()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case NetworkmodelPackage.ABSTRACT_NODE__OUT:
                return ((InternalEList<?>)getOut()).basicRemove(otherEnd, msgs);
            case NetworkmodelPackage.ABSTRACT_NODE__IN:
                return ((InternalEList<?>)getIn()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case NetworkmodelPackage.ABSTRACT_NODE__ID:
                return getId();
            case NetworkmodelPackage.ABSTRACT_NODE__LABEL:
                return getLabel();
            case NetworkmodelPackage.ABSTRACT_NODE__OUT:
                return getOut();
            case NetworkmodelPackage.ABSTRACT_NODE__IN:
                return getIn();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case NetworkmodelPackage.ABSTRACT_NODE__ID:
                setId((Integer)newValue);
                return;
            case NetworkmodelPackage.ABSTRACT_NODE__LABEL:
                setLabel((String)newValue);
                return;
            case NetworkmodelPackage.ABSTRACT_NODE__OUT:
                getOut().clear();
                getOut().addAll((Collection<? extends AbstractEdge>)newValue);
                return;
            case NetworkmodelPackage.ABSTRACT_NODE__IN:
                getIn().clear();
                getIn().addAll((Collection<? extends AbstractEdge>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID)
    {
        switch (featureID)
        {
            case NetworkmodelPackage.ABSTRACT_NODE__ID:
                setId(ID_EDEFAULT);
                return;
            case NetworkmodelPackage.ABSTRACT_NODE__LABEL:
                setLabel(LABEL_EDEFAULT);
                return;
            case NetworkmodelPackage.ABSTRACT_NODE__OUT:
                getOut().clear();
                return;
            case NetworkmodelPackage.ABSTRACT_NODE__IN:
                getIn().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID)
    {
        switch (featureID)
        {
            case NetworkmodelPackage.ABSTRACT_NODE__ID:
                return id != ID_EDEFAULT;
            case NetworkmodelPackage.ABSTRACT_NODE__LABEL:
                return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
            case NetworkmodelPackage.ABSTRACT_NODE__OUT:
                return out != null && !out.isEmpty();
            case NetworkmodelPackage.ABSTRACT_NODE__IN:
                return in != null && !in.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString()
    {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (id: ");
        result.append(id);
        result.append(", label: ");
        result.append(label);
        result.append(')');
        return result.toString();
    }

} //AbstractNodeImpl
