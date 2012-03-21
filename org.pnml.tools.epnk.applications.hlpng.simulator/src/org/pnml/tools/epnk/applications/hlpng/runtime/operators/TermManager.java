package org.pnml.tools.epnk.applications.hlpng.runtime.operators;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.AddImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.TupleImpl;

public class TermManager
{
	private static TermManager termManager = null;
	
	private Map<Class, AbstractTermHandler> handlers = null;
	
	private TermManager()
	{
		handlers = new HashMap<Class, AbstractTermHandler>();
		{
			AbstractTermHandler defaultOp = new DefaultOperator(this, null);
			handlers.put(NumberConstantImpl.class, new NumberConstantOperator(this, defaultOp));
			handlers.put(NumberOfImpl.class, new NumberOfOperator(this, defaultOp));
			handlers.put(AddImpl.class, new AddOperator(this, defaultOp));
			handlers.put(TupleImpl.class, new TupleOperator(this, defaultOp));
		}
	}
	
	public static TermManager getInstance()
	{
		if(termManager == null)
		{
			termManager = new TermManager();
		}
		return termManager;
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
