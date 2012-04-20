package networkmodel.diagram.part;

import networkmodel.Network;
import networkmodel.NetworkmodelPackage;
import networkmodel.diagram.edit.parts.AlphaNodeEditPart;
import networkmodel.diagram.edit.parts.AlphaNodeLabelEditPart;
import networkmodel.diagram.edit.parts.DirectedEdgeEditPart;
import networkmodel.diagram.edit.parts.NetworkEditPart;
import networkmodel.diagram.edit.parts.OmegaNodeEditPart;
import networkmodel.diagram.edit.parts.OmegaNodeLabelEditPart;
import networkmodel.diagram.edit.parts.UndirectedEdgeEditPart;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class NetworkVisualIDRegistry
{

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "NetworkEditor.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view)
	{
		if(view instanceof Diagram)
		{
			if(NetworkEditPart.MODEL_ID.equals(view.getType()))
			{
				return NetworkEditPart.VISUAL_ID;
			}
			else
			{
				return -1;
			}
		}
		return networkmodel.diagram.part.NetworkVisualIDRegistry
		        .getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view)
	{
		View diagram = view.getDiagram();
		while(view != diagram)
		{
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if(annotation != null)
			{
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type)
	{
		try
		{
			return Integer.parseInt(type);
		}
		catch(NumberFormatException e)
		{
			if(Boolean.TRUE.toString().equalsIgnoreCase(
			        Platform.getDebugOption(DEBUG_KEY)))
			{
				NetworkDiagramEditorPlugin.getInstance().logError(
				        "Unable to parse view type as a visualID number: "
				                + type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID)
	{
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement)
	{
		if(domainElement == null)
		{
			return -1;
		}
		if(NetworkmodelPackage.eINSTANCE.getNetwork().isSuperTypeOf(
		        domainElement.eClass())
		        && isDiagram((Network) domainElement))
		{
			return NetworkEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement)
	{
		if(domainElement == null)
		{
			return -1;
		}
		String containerModelID = networkmodel.diagram.part.NetworkVisualIDRegistry
		        .getModelID(containerView);
		if(!NetworkEditPart.MODEL_ID.equals(containerModelID))
		{
			return -1;
		}
		int containerVisualID;
		if(NetworkEditPart.MODEL_ID.equals(containerModelID))
		{
			containerVisualID = networkmodel.diagram.part.NetworkVisualIDRegistry
			        .getVisualID(containerView);
		}
		else
		{
			if(containerView instanceof Diagram)
			{
				containerVisualID = NetworkEditPart.VISUAL_ID;
			}
			else
			{
				return -1;
			}
		}
		switch(containerVisualID)
		{
			case NetworkEditPart.VISUAL_ID:
				if(NetworkmodelPackage.eINSTANCE.getAlphaNode().isSuperTypeOf(
				        domainElement.eClass()))
				{
					return AlphaNodeEditPart.VISUAL_ID;
				}
				if(NetworkmodelPackage.eINSTANCE.getOmegaNode().isSuperTypeOf(
				        domainElement.eClass()))
				{
					return OmegaNodeEditPart.VISUAL_ID;
				}
				break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID)
	{
		String containerModelID = networkmodel.diagram.part.NetworkVisualIDRegistry
		        .getModelID(containerView);
		if(!NetworkEditPart.MODEL_ID.equals(containerModelID))
		{
			return false;
		}
		int containerVisualID;
		if(NetworkEditPart.MODEL_ID.equals(containerModelID))
		{
			containerVisualID = networkmodel.diagram.part.NetworkVisualIDRegistry
			        .getVisualID(containerView);
		}
		else
		{
			if(containerView instanceof Diagram)
			{
				containerVisualID = NetworkEditPart.VISUAL_ID;
			}
			else
			{
				return false;
			}
		}
		switch(containerVisualID)
		{
			case NetworkEditPart.VISUAL_ID:
				if(AlphaNodeEditPart.VISUAL_ID == nodeVisualID)
				{
					return true;
				}
				if(OmegaNodeEditPart.VISUAL_ID == nodeVisualID)
				{
					return true;
				}
				break;
			case AlphaNodeEditPart.VISUAL_ID:
				if(AlphaNodeLabelEditPart.VISUAL_ID == nodeVisualID)
				{
					return true;
				}
				break;
			case OmegaNodeEditPart.VISUAL_ID:
				if(OmegaNodeLabelEditPart.VISUAL_ID == nodeVisualID)
				{
					return true;
				}
				break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement)
	{
		if(domainElement == null)
		{
			return -1;
		}
		if(NetworkmodelPackage.eINSTANCE.getUndirectedEdge().isSuperTypeOf(
		        domainElement.eClass()))
		{
			return UndirectedEdgeEditPart.VISUAL_ID;
		}
		if(NetworkmodelPackage.eINSTANCE.getDirectedEdge().isSuperTypeOf(
		        domainElement.eClass()))
		{
			return DirectedEdgeEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Network element)
	{
		return true;
	}

}
