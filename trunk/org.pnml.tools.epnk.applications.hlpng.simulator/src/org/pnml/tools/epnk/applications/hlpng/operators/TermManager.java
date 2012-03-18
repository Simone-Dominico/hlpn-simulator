package org.pnml.tools.epnk.applications.hlpng.operators;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractBinaryOperation;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeAddition;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeMultiplication;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.AdditionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.MultiplicationImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.AddImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.TupleImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.VariableImpl;

public class TermManager
{
	private Map<Class, AbstractTermHandler> handlers = null;
	
	public TermManager()
	{
		handlers = new HashMap<Class, AbstractTermHandler>();
		{
			AbstractTermHandler defaultOp = new DefaultOperator(this, null);
			handlers.put(NumberConstantImpl.class, new NumberConstantOperator(this, defaultOp));
			handlers.put(NumberOfImpl.class, new NumberOfOperator(this, defaultOp));
			handlers.put(AddImpl.class, new AddOperator(this, defaultOp));
			handlers.put(TupleImpl.class, new TupleOperator(this, defaultOp));
			handlers.put(VariableImpl.class, new VariableHandler(this, defaultOp));
			
			handlers.put(AdditionImpl.class, new BinaryIntegerOperator(this, defaultOp));
			handlers.put(MultiplicationImpl.class, new BinaryIntegerOperator(this, defaultOp));
		}
	}
	
	public void register(Class targetClass, AbstractTermHandler operator)
	{
		handlers.put(targetClass, operator);
	}
	
	public void unregister(Class targetClass)
	{
		handlers.remove(targetClass);
	}
	
	public AbstractTermHandler getHandler(Class targetClass)
	{
		return handlers.get(targetClass);
	}
	
	public static AbstractBinaryOperation createOperation(Class name)
	{
		if(name.equals(AdditionImpl.class))
		{
			return new RuntimeAddition();
		}
		if(name.equals(MultiplicationImpl.class))
		{
			return new RuntimeMultiplication();
		}
		
		throw new RuntimeException("Do not know the operation!");
	}
}
