package org.pnml.tools.epnk.applications.hlpng.operators;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractBinaryOperation;
import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.IntegerOperator;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public class BinaryIntegerOperator extends AbstractTermHandler
{

	public BinaryIntegerOperator(TermManager termManager, AbstractTermHandler next)
	{
		super(termManager, next);
	}

	@Override
	public AbstractValue handle(Term term)
	{
		if(term instanceof IntegerOperator)
		{
			IntegerOperator op = (IntegerOperator) term;
			if(op.getSubterm().size() != 2)
			{
				throw new RuntimeException("Incorrect number of arguments: " + term);
			}
			
			Term leftTerm = op.getSubterm().get(0);
			AbstractTermHandler leftTermHandler = termManager.getHandler(leftTerm.getClass());
			AbstractValue leftOp = leftTermHandler.handle(leftTerm);
			
			Term rightTerm = op.getSubterm().get(1);
			AbstractTermHandler rightTermHandler = termManager.getHandler(rightTerm.getClass());
			AbstractValue rightOp = rightTermHandler.handle(rightTerm);
			
			AbstractBinaryOperation handler = TermManager.createOperation(term.getClass());
			handler.setOperand(AbstractBinaryOperation.LEFT_OPERAND, leftOp);
			handler.setOperand(AbstractBinaryOperation.RIGHT_OPERAND, rightOp);
			
			if(handler.operandsHaveValues())
			{
				return handler.evaluate();
			}
			return handler;
		}
		return next.handle(term);
	}

}
