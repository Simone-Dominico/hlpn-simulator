/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package transitionruntime.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import transitionruntime.TransitionruntimePackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TransitionruntimeXMLProcessor extends XMLProcessor
{

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransitionruntimeXMLProcessor()
    {
        super((EPackage.Registry.INSTANCE));
        TransitionruntimePackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the TransitionruntimeResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations()
    {
        if (registrations == null)
        {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new TransitionruntimeResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new TransitionruntimeResourceFactoryImpl());
        }
        return registrations;
    }

} //TransitionruntimeXMLProcessor
