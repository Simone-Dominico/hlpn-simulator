package networkmodel.diagram.part;

import java.util.Collections;
import java.util.List;

import networkmodel.diagram.providers.NetworkElementTypes;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class NetworkPaletteFactory
{

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot)
	{
		paletteRoot.add(createNetworkmodel1Group());
	}

	/**
	 * Creates "networkmodel" palette tool group
	 * @generated
	 */
	private PaletteContainer createNetworkmodel1Group()
	{
		PaletteGroup paletteContainer = new PaletteGroup(
		        Messages.Networkmodel1Group_title);
		paletteContainer.setId("createNetworkmodel1Group"); //$NON-NLS-1$
		paletteContainer.add(createAlphaNode1CreationTool());
		paletteContainer.add(createOmegaNode2CreationTool());
		paletteContainer.add(createUndirectedEdge3CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAlphaNode1CreationTool()
	{
		NodeToolEntry entry = new NodeToolEntry(
		        Messages.AlphaNode1CreationTool_title,
		        Messages.AlphaNode1CreationTool_desc,
		        Collections.singletonList(NetworkElementTypes.AlphaNode_2001));
		entry.setId("createAlphaNode1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NetworkElementTypes
		        .getImageDescriptor(NetworkElementTypes.AlphaNode_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOmegaNode2CreationTool()
	{
		NodeToolEntry entry = new NodeToolEntry(
		        Messages.OmegaNode2CreationTool_title,
		        Messages.OmegaNode2CreationTool_desc,
		        Collections.singletonList(NetworkElementTypes.OmegaNode_2002));
		entry.setId("createOmegaNode2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NetworkElementTypes
		        .getImageDescriptor(NetworkElementTypes.OmegaNode_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createUndirectedEdge3CreationTool()
	{
		LinkToolEntry entry = new LinkToolEntry(
		        Messages.UndirectedEdge3CreationTool_title,
		        Messages.UndirectedEdge3CreationTool_desc,
		        Collections
		                .singletonList(NetworkElementTypes.UndirectedEdge_4001));
		entry.setId("createUndirectedEdge3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(NetworkElementTypes
		        .getImageDescriptor(NetworkElementTypes.UndirectedEdge_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry
	{

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
		        List<IElementType> elementTypes)
		{
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool()
		{
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry
	{

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
		        List<IElementType> relationshipTypes)
		{
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool()
		{
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
