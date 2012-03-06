package org.pnml.tools.epnk.applications.hlpng.operations;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.AddImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.TupleImpl;

public class TermManager
{
	private Map<Class, AbstractOperator> handlers = null;
	
	public TermManager()
	{
		handlers = new HashMap<Class, AbstractOperator>();
		{
			AbstractOperator defaultOp = new DefaultOperator(this, null);
			handlers.put(NumberConstantImpl.class, new NumberConstantOperator(this, defaultOp));
			handlers.put(NumberOfImpl.class, new NumberOfOperator(this, defaultOp));
			handlers.put(AddImpl.class, new AddOperator(this, defaultOp));
			handlers.put(TupleImpl.class, new TupleOperator(this, defaultOp));
		}
	}
	
	public void register(Class targetClass, AbstractOperator operator)
	{
		handlers.put(targetClass, operator);
	}
	
	public void unregister(Class targetClass)
	{
		handlers.remove(targetClass);
	}
	
	public AbstractOperator getHandler(Class targetClass)
	{
		return handlers.get(targetClass);
	}
}
