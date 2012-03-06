/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package variableruntime.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import variableruntime.VariableruntimePackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class VariableruntimeXMLProcessor extends XMLProcessor
{

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public VariableruntimeXMLProcessor()
    {
        super((EPackage.Registry.INSTANCE));
        VariableruntimePackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the VariableruntimeResourceFactoryImpl factory.
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
            registrations.put(XML_EXTENSION, new VariableruntimeResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new VariableruntimeResourceFactoryImpl());
        }
        return registrations;
    }

} //VariableruntimeXMLProcessor
