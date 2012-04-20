package networkmodel.diagram.edit.commands;

import networkmodel.AbstractNode;
import networkmodel.Network;
import networkmodel.UndirectedEdge;
import networkmodel.diagram.edit.policies.NetworkBaseItemSemanticEditPolicy;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

/**
 * @generated
 */
public class UndirectedEdgeReorientCommand extends EditElementCommand
{

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public UndirectedEdgeReorientCommand(ReorientRelationshipRequest request)
	{
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute()
	{
		if(false == getElementToEdit() instanceof UndirectedEdge)
		{
			return false;
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE)
		{
			return canReorientSource();
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET)
		{
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource()
	{
		if(!(oldEnd instanceof AbstractNode && newEnd instanceof AbstractNode))
		{
			return false;
		}
		AbstractNode target = getLink().getTarget();
		if(!(getLink().eContainer() instanceof Network))
		{
			return false;
		}
		Network container = (Network) getLink().eContainer();
		return NetworkBaseItemSemanticEditPolicy.getLinkConstraints()
		        .canExistUndirectedEdge_4001(container, getLink(),
		                getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget()
	{
		if(!(oldEnd instanceof AbstractNode && newEnd instanceof AbstractNode))
		{
			return false;
		}
		AbstractNode source = getLink().getSource();
		if(!(getLink().eContainer() instanceof Network))
		{
			return false;
		}
		Network container = (Network) getLink().eContainer();
		return NetworkBaseItemSemanticEditPolicy.getLinkConstraints()
		        .canExistUndirectedEdge_4001(container, getLink(), source,
		                getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
	        IAdaptable info) throws ExecutionException
	{
		if(!canExecute())
		{
			throw new ExecutionException(
			        "Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE)
		{
			return reorientSource();
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET)
		{
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException
	{
		getLink().setSource(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException
	{
		getLink().setTarget(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected UndirectedEdge getLink()
	{
		return (UndirectedEdge) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected AbstractNode getOldSource()
	{
		return (AbstractNode) oldEnd;
	}

	/**
	 * @generated
	 */
	protected AbstractNode getNewSource()
	{
		return (AbstractNode) newEnd;
	}

	/**
	 * @generated
	 */
	protected AbstractNode getOldTarget()
	{
		return (AbstractNode) oldEnd;
	}

	/**
	 * @generated
	 */
	protected AbstractNode getNewTarget()
	{
		return (AbstractNode) newEnd;
	}
}
