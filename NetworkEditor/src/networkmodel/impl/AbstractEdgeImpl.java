/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package networkmodel.impl;

import networkmodel.AbstractEdge;
import networkmodel.AbstractNode;
import networkmodel.NetworkmodelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link networkmodel.impl.AbstractEdgeImpl#getSource <em>Source</em>}</li>
 *   <li>{@link networkmodel.impl.AbstractEdgeImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractEdgeImpl extends NetworkObjectImpl implements AbstractEdge
{
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected AbstractNode source;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected AbstractNode target;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractEdgeImpl()
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
        return NetworkmodelPackage.Literals.ABSTRACT_EDGE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractNode getSource()
    {
        if (source != null && source.eIsProxy())
        {
            InternalEObject oldSource = (InternalEObject)source;
            source = (AbstractNode)eResolveProxy(oldSource);
            if (source != oldSource)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, NetworkmodelPackage.ABSTRACT_EDGE__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractNode basicGetSource()
    {
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSource(AbstractNode newSource, NotificationChain msgs)
    {
        AbstractNode oldSource = source;
        source = newSource;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, NetworkmodelPackage.ABSTRACT_EDGE__SOURCE, oldSource, newSource);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSource(AbstractNode newSource)
    {
        if (newSource != source)
        {
            NotificationChain msgs = null;
            if (source != null)
                msgs = ((InternalEObject)source).eInverseRemove(this, NetworkmodelPackage.ABSTRACT_NODE__OUT, AbstractNode.class, msgs);
            if (newSource != null)
                msgs = ((InternalEObject)newSource).eInverseAdd(this, NetworkmodelPackage.ABSTRACT_NODE__OUT, AbstractNode.class, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NetworkmodelPackage.ABSTRACT_EDGE__SOURCE, newSource, newSource));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractNode getTarget()
    {
        if (target != null && target.eIsProxy())
        {
            InternalEObject oldTarget = (InternalEObject)target;
            target = (AbstractNode)eResolveProxy(oldTarget);
            if (target != oldTarget)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, NetworkmodelPackage.ABSTRACT_EDGE__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractNode basicGetTarget()
    {
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTarget(AbstractNode newTarget, NotificationChain msgs)
    {
        AbstractNode oldTarget = target;
        target = newTarget;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, NetworkmodelPackage.ABSTRACT_EDGE__TARGET, oldTarget, newTarget);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTarget(AbstractNode newTarget)
    {
        if (newTarget != target)
        {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject)target).eInverseRemove(this, NetworkmodelPackage.ABSTRACT_NODE__IN, AbstractNode.class, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject)newTarget).eInverseAdd(this, NetworkmodelPackage.ABSTRACT_NODE__IN, AbstractNode.class, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, NetworkmodelPackage.ABSTRACT_EDGE__TARGET, newTarget, newTarget));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case NetworkmodelPackage.ABSTRACT_EDGE__SOURCE:
                if (source != null)
                    msgs = ((InternalEObject)source).eInverseRemove(this, NetworkmodelPackage.ABSTRACT_NODE__OUT, AbstractNode.class, msgs);
                return basicSetSource((AbstractNode)otherEnd, msgs);
            case NetworkmodelPackage.ABSTRACT_EDGE__TARGET:
                if (target != null)
                    msgs = ((InternalEObject)target).eInverseRemove(this, NetworkmodelPackage.ABSTRACT_NODE__IN, AbstractNode.class, msgs);
                return basicSetTarget((AbstractNode)otherEnd, msgs);
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
            case NetworkmodelPackage.ABSTRACT_EDGE__SOURCE:
                return basicSetSource(null, msgs);
            case NetworkmodelPackage.ABSTRACT_EDGE__TARGET:
                return basicSetTarget(null, msgs);
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
            case NetworkmodelPackage.ABSTRACT_EDGE__SOURCE:
                if (resolve) return getSource();
                return basicGetSource();
            case NetworkmodelPackage.ABSTRACT_EDGE__TARGET:
                if (resolve) return getTarget();
                return basicGetTarget();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case NetworkmodelPackage.ABSTRACT_EDGE__SOURCE:
                setSource((AbstractNode)newValue);
                return;
            case NetworkmodelPackage.ABSTRACT_EDGE__TARGET:
                setTarget((AbstractNode)newValue);
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
            case NetworkmodelPackage.ABSTRACT_EDGE__SOURCE:
                setSource((AbstractNode)null);
                return;
            case NetworkmodelPackage.ABSTRACT_EDGE__TARGET:
                setTarget((AbstractNode)null);
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
            case NetworkmodelPackage.ABSTRACT_EDGE__SOURCE:
                return source != null;
            case NetworkmodelPackage.ABSTRACT_EDGE__TARGET:
                return target != null;
        }
        return super.eIsSet(featureID);
    }

} //AbstractEdgeImpl
