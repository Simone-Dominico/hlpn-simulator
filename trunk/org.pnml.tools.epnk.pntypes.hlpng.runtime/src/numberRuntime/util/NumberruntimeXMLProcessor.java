/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package numberRuntime.util;

import java.util.Map;

import numberRuntime.NumberruntimePackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class NumberruntimeXMLProcessor extends XMLProcessor
{

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NumberruntimeXMLProcessor()
    {
        super((EPackage.Registry.INSTANCE));
        NumberruntimePackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the NumberruntimeResourceFactoryImpl factory.
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
            registrations.put(XML_EXTENSION, new NumberruntimeResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new NumberruntimeResourceFactoryImpl());
        }
        return registrations;
    }

} //NumberruntimeXMLProcessor
