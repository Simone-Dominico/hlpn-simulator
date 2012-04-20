package networkmodel.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import networkmodel.AbstractNode;
import networkmodel.AlphaNode;
import networkmodel.Network;
import networkmodel.NetworkObject;
import networkmodel.NetworkmodelPackage;
import networkmodel.OmegaNode;
import networkmodel.UndirectedEdge;
import networkmodel.diagram.edit.parts.AlphaNodeEditPart;
import networkmodel.diagram.edit.parts.NetworkEditPart;
import networkmodel.diagram.edit.parts.OmegaNodeEditPart;
import networkmodel.diagram.edit.parts.UndirectedEdgeEditPart;
import networkmodel.diagram.providers.NetworkElementTypes;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class NetworkDiagramUpdater
{

	/**
	 * @generated
	 */
	public static List<NetworkNodeDescriptor> getSemanticChildren(View view)
	{
		switch(NetworkVisualIDRegistry.getVisualID(view))
		{
			case NetworkEditPart.VISUAL_ID:
				return getNetwork_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkNodeDescriptor> getNetwork_1000SemanticChildren(
	        View view)
	{
		if(!view.isSetElement())
		{
			return Collections.emptyList();
		}
		Network modelElement = (Network) view.getElement();
		LinkedList<NetworkNodeDescriptor> result = new LinkedList<NetworkNodeDescriptor>();
		for(Iterator<?> it = modelElement.getNetwork().iterator(); it.hasNext();)
		{
			NetworkObject childElement = (NetworkObject) it.next();
			int visualID = NetworkVisualIDRegistry.getNodeVisualID(view,
			        childElement);
			if(visualID == AlphaNodeEditPart.VISUAL_ID)
			{
				result.add(new NetworkNodeDescriptor(childElement, visualID));
				continue;
			}
			if(visualID == OmegaNodeEditPart.VISUAL_ID)
			{
				result.add(new NetworkNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getContainedLinks(View view)
	{
		switch(NetworkVisualIDRegistry.getVisualID(view))
		{
			case NetworkEditPart.VISUAL_ID:
				return getNetwork_1000ContainedLinks(view);
			case AlphaNodeEditPart.VISUAL_ID:
				return getAlphaNode_2001ContainedLinks(view);
			case OmegaNodeEditPart.VISUAL_ID:
				return getOmegaNode_2002ContainedLinks(view);
			case UndirectedEdgeEditPart.VISUAL_ID:
				return getUndirectedEdge_4001ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getIncomingLinks(View view)
	{
		switch(NetworkVisualIDRegistry.getVisualID(view))
		{
			case AlphaNodeEditPart.VISUAL_ID:
				return getAlphaNode_2001IncomingLinks(view);
			case OmegaNodeEditPart.VISUAL_ID:
				return getOmegaNode_2002IncomingLinks(view);
			case UndirectedEdgeEditPart.VISUAL_ID:
				return getUndirectedEdge_4001IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getOutgoingLinks(View view)
	{
		switch(NetworkVisualIDRegistry.getVisualID(view))
		{
			case AlphaNodeEditPart.VISUAL_ID:
				return getAlphaNode_2001OutgoingLinks(view);
			case OmegaNodeEditPart.VISUAL_ID:
				return getOmegaNode_2002OutgoingLinks(view);
			case UndirectedEdgeEditPart.VISUAL_ID:
				return getUndirectedEdge_4001OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getNetwork_1000ContainedLinks(
	        View view)
	{
		Network modelElement = (Network) view.getElement();
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_UndirectedEdge_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getAlphaNode_2001ContainedLinks(
	        View view)
	{
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getOmegaNode_2002ContainedLinks(
	        View view)
	{
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getUndirectedEdge_4001ContainedLinks(
	        View view)
	{
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getAlphaNode_2001IncomingLinks(
	        View view)
	{
		AlphaNode modelElement = (AlphaNode) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
		        .find(view.eResource().getResourceSet().getResources());
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_UndirectedEdge_4001(
		        modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getOmegaNode_2002IncomingLinks(
	        View view)
	{
		OmegaNode modelElement = (OmegaNode) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
		        .find(view.eResource().getResourceSet().getResources());
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_UndirectedEdge_4001(
		        modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getUndirectedEdge_4001IncomingLinks(
	        View view)
	{
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getAlphaNode_2001OutgoingLinks(
	        View view)
	{
		AlphaNode modelElement = (AlphaNode) view.getElement();
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_UndirectedEdge_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getOmegaNode_2002OutgoingLinks(
	        View view)
	{
		OmegaNode modelElement = (OmegaNode) view.getElement();
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_UndirectedEdge_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NetworkLinkDescriptor> getUndirectedEdge_4001OutgoingLinks(
	        View view)
	{
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<NetworkLinkDescriptor> getContainedTypeModelFacetLinks_UndirectedEdge_4001(
	        Network container)
	{
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		for(Iterator<?> links = container.getNetwork().iterator(); links
		        .hasNext();)
		{
			EObject linkObject = (EObject) links.next();
			if(false == linkObject instanceof UndirectedEdge)
			{
				continue;
			}
			UndirectedEdge link = (UndirectedEdge) linkObject;
			if(UndirectedEdgeEditPart.VISUAL_ID != NetworkVisualIDRegistry
			        .getLinkWithClassVisualID(link))
			{
				continue;
			}
			AbstractNode dst = link.getTarget();
			AbstractNode src = link.getSource();
			result.add(new NetworkLinkDescriptor(src, dst, link,
			        NetworkElementTypes.UndirectedEdge_4001,
			        UndirectedEdgeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NetworkLinkDescriptor> getIncomingTypeModelFacetLinks_UndirectedEdge_4001(
	        AbstractNode target,
	        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences)
	{
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
		        .get(target);
		for(EStructuralFeature.Setting setting : settings)
		{
			if(setting.getEStructuralFeature() != NetworkmodelPackage.eINSTANCE
			        .getAbstractEdge_Target()
			        || false == setting.getEObject() instanceof UndirectedEdge)
			{
				continue;
			}
			UndirectedEdge link = (UndirectedEdge) setting.getEObject();
			if(UndirectedEdgeEditPart.VISUAL_ID != NetworkVisualIDRegistry
			        .getLinkWithClassVisualID(link))
			{
				continue;
			}
			AbstractNode src = link.getSource();
			result.add(new NetworkLinkDescriptor(src, target, link,
			        NetworkElementTypes.UndirectedEdge_4001,
			        UndirectedEdgeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NetworkLinkDescriptor> getOutgoingTypeModelFacetLinks_UndirectedEdge_4001(
	        AbstractNode source)
	{
		Network container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for(EObject element = source; element != null && container == null; element = element
		        .eContainer())
		{
			if(element instanceof Network)
			{
				container = (Network) element;
			}
		}
		if(container == null)
		{
			return Collections.emptyList();
		}
		LinkedList<NetworkLinkDescriptor> result = new LinkedList<NetworkLinkDescriptor>();
		for(Iterator<?> links = container.getNetwork().iterator(); links
		        .hasNext();)
		{
			EObject linkObject = (EObject) links.next();
			if(false == linkObject instanceof UndirectedEdge)
			{
				continue;
			}
			UndirectedEdge link = (UndirectedEdge) linkObject;
			if(UndirectedEdgeEditPart.VISUAL_ID != NetworkVisualIDRegistry
			        .getLinkWithClassVisualID(link))
			{
				continue;
			}
			AbstractNode dst = link.getTarget();
			AbstractNode src = link.getSource();
			if(src != source)
			{
				continue;
			}
			result.add(new NetworkLinkDescriptor(src, dst, link,
			        NetworkElementTypes.UndirectedEdge_4001,
			        UndirectedEdgeEditPart.VISUAL_ID));
		}
		return result;
	}

}
