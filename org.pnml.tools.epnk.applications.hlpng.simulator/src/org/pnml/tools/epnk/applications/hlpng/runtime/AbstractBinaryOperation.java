package org.pnml.tools.epnk.applications.hlpng.runtime;

public abstract class AbstractBinaryOperation extends AbstractValue
{
	public static final int LEFT_OPERAND = 0;
	public static final int RIGHT_OPERAND = 1;

	protected AbstractValue[] operands = new AbstractValue[2];
	
	public AbstractValue getOperand(int operandNo)
    {
    	return operands[operandNo];
    }
	
	public void setOperand(int operandNo, AbstractValue value)
    {
    	operands[operandNo] = value;
    }
	
	public boolean operandsHaveValues()
	{
		for(AbstractValue value : operands)
		{
			if(value instanceof RuntimeVariable)
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
    public String toString()
    {
	    return "(" + getOperand(LEFT_OPERAND) + stringRepresentation() + 
	    		getOperand(RIGHT_OPERAND) + ")";
    }
	
	
	// both operands are known
	public abstract AbstractValue evaluate();
	
	// both operands are unknown but one of them can be evaluated elsewhere
	public abstract AbstractValue evaluate(AbstractValue result,
			AbstractValue variableValue, int operandNo);
	
	public abstract String stringRepresentation();
}
