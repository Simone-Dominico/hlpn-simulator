package org.pnml.tools.epnk.applications.hlpng.runtime;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Sort;

public class RuntimeAddition extends AbstractIntegerBinaryOperation
{
	@Override
	public AbstractValue evaluate()
	{
		Sort sort = getOperand(LEFT_OPERAND).getSort();
		
		NumberValue v =  createResultObject(sort);

		v.setSort(sort);
		v.setN(((NumberValue)getOperand(LEFT_OPERAND)).getN() +
				((NumberValue)getOperand(RIGHT_OPERAND)).getN());
		
		return v;
	}
	
	@Override
	public AbstractValue evaluate(AbstractValue result, AbstractValue variableValue, int operandNo)
	{
		Sort sort = getOperand(LEFT_OPERAND).getSort();
		
		NumberValue v =  createResultObject(sort);

		v.setSort(sort);
		v.setN(((NumberValue)result).getN() - ((NumberValue)getOperand(operandNo)).getN());
		
		return v;
	}
	
	@Override
    public String stringRepresentation()
    {
	    return "+";
    }
}
