package org.pnml.tools.epnk.applications.hlpng.operations;

import java.util.HashMap;
import java.util.Map;

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
}
