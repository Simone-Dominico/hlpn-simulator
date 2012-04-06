package org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators;

import java.util.HashMap;
import java.util.Map;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.AdditionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.MultiplicationImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.impl.StringConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.MultiSetOperatorImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.TupleImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.VariableImpl;

public class ComparisonManager
{
	private static ComparisonManager evaluationManager = null;
	
	private Map<Class, IComparable> handlers = null;

	private ComparisonManager()
	{
		handlers = new HashMap<Class, IComparable>();
		{
			handlers.put(NumberConstantImpl.class, new NumberConstantComparator());
			handlers.put(StringConstantImpl.class, new StringConstantComparator());
			
			handlers.put(TupleImpl.class, new TupleComparator());
			handlers.put(MultiSetOperatorImpl.class, new MultisetComparator());
			handlers.put(NumberOfImpl.class, new NumberOfComparator());
			
			BinaryOperationComparator binEval = new BinaryOperationComparator();
			handlers.put(AdditionImpl.class, binEval);
			handlers.put(MultiplicationImpl.class, binEval);
			
			handlers.put(VariableImpl.class, new VariableComparator());
		}
	}
	
	public static ComparisonManager getInstance()
	{
		if(evaluationManager == null)
		{
			evaluationManager = new ComparisonManager();
		}
		return evaluationManager;
	}
	
	public void register(Class targetClass, IComparable comparator)
	{
		handlers.put(targetClass, comparator);
	}
	
	public void unregister(Class targetClass)
	{
		handlers.remove(targetClass);
	}
	
	public IComparable getComparator(Class targetClass)
	{
		return handlers.get(targetClass);
	}
}
