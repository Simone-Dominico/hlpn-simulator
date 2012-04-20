package networkmodel.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import networkmodel.NetworkmodelPackage;
import networkmodel.diagram.edit.parts.AlphaNodeEditPart;
import networkmodel.diagram.edit.parts.NetworkEditPart;
import networkmodel.diagram.edit.parts.OmegaNodeEditPart;
import networkmodel.diagram.edit.parts.UndirectedEdgeEditPart;
import networkmodel.diagram.part.NetworkDiagramEditorPlugin;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class NetworkElementTypes
{

	/**
	 * @generated
	 */
	private NetworkElementTypes()
	{
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType Network_1000 = getElementType("NetworkEditor.diagram.Network_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType AlphaNode_2001 = getElementType("NetworkEditor.diagram.AlphaNode_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType OmegaNode_2002 = getElementType("NetworkEditor.diagram.OmegaNode_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UndirectedEdge_4001 = getElementType("NetworkEditor.diagram.UndirectedEdge_4001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry()
	{
		if(imageRegistry == null)
		{
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element)
	{
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
	        ENamedElement element)
	{
		if(element instanceof EStructuralFeature)
		{
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if(eContainingClass != null && !eContainingClass.isAbstract())
			{
				element = eContainingClass;
			}
			else if(eType instanceof EClass && !((EClass) eType).isAbstract())
			{
				element = eType;
			}
		}
		if(element instanceof EClass)
		{
			EClass eClass = (EClass) element;
			if(!eClass.isAbstract())
			{
				return NetworkDiagramEditorPlugin.getInstance()
				        .getItemImageDescriptor(
				                eClass.getEPackage().getEFactoryInstance()
				                        .create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element)
	{
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if(imageDescriptor == null)
		{
			imageDescriptor = getProvidedImageDescriptor(element);
			if(imageDescriptor == null)
			{
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element)
	{
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if(image == null)
		{
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if(imageDescriptor == null)
			{
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint)
	{
		ENamedElement element = getElement(hint);
		if(element == null)
		{
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint)
	{
		ENamedElement element = getElement(hint);
		if(element == null)
		{
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint)
	{
		Object type = hint.getAdapter(IElementType.class);
		if(elements == null)
		{
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(Network_1000,
			        NetworkmodelPackage.eINSTANCE.getNetwork());

			elements.put(AlphaNode_2001,
			        NetworkmodelPackage.eINSTANCE.getAlphaNode());

			elements.put(OmegaNode_2002,
			        NetworkmodelPackage.eINSTANCE.getOmegaNode());

			elements.put(UndirectedEdge_4001,
			        NetworkmodelPackage.eINSTANCE.getUndirectedEdge());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id)
	{
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType)
	{
		if(KNOWN_ELEMENT_TYPES == null)
		{
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(Network_1000);
			KNOWN_ELEMENT_TYPES.add(AlphaNode_2001);
			KNOWN_ELEMENT_TYPES.add(OmegaNode_2002);
			KNOWN_ELEMENT_TYPES.add(UndirectedEdge_4001);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID)
	{
		switch(visualID)
		{
			case NetworkEditPart.VISUAL_ID:
				return Network_1000;
			case AlphaNodeEditPart.VISUAL_ID:
				return AlphaNode_2001;
			case OmegaNodeEditPart.VISUAL_ID:
				return OmegaNode_2002;
			case UndirectedEdgeEditPart.VISUAL_ID:
				return UndirectedEdge_4001;
		}
		return null;
	}

}
