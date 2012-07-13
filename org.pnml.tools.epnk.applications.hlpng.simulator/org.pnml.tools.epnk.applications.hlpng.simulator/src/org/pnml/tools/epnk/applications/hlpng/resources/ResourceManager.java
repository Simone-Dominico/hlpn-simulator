package org.pnml.tools.epnk.applications.hlpng.resources;

import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.pnml.tools.epnk.applications.hlpng.runtime.RuntimeValueFactory;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ComparisonManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.DatatypesComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ListComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.MultisetComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.NumberOfComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.ReversibleOperationComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.TupleComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.UserOperatorComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.comparators.VariableComparator;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.extensions.IUserExtensions;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.IFiringStrategy;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.BooleansEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.DataTypeEvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.DivisionEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.DotsEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.EvaluationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.IntegersEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.ListsEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.MultisetsEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.StringsEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.TermsEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.UserOperatorEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.VariableEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.reversible.AdditionEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.reversible.MultiplicationEval;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.reversible.ReversibleOperationManager;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.operators.reversible.SubtractionEval;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.booleans.impl.BooleanConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.dots.impl.DotConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.AdditionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.DivisionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.MultiplicationImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.NumberConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.integers.impl.SubtractionImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.lists.impl.MakeListImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.multisets.impl.NumberOfImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.strings.impl.StringConstantImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.MultiSetOperatorImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.TupleImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.UserOperatorImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.UserSortImpl;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.impl.VariableImpl;

public class ResourceManager
{
	public static final String SIMULATOR_PLUGIN_ID = "org.pnml.tools.epnk.applications.hlpng.simulator"; 
	
	public static EvaluationManager createEvaluationManager(RuntimeValueFactory factory,
			String extensionId)
	{
		EvaluationManager evaluationManager = new EvaluationManager();
		// integers package
		evaluationManager.register(NumberConstantImpl.class.getPackage(), new IntegersEval());
		// booleans package
		evaluationManager.register(BooleanConstantImpl.class.getPackage(), new BooleansEval());
		// strings package
		evaluationManager.register(StringConstantImpl.class.getPackage(), new StringsEval());	
		// dots
		evaluationManager.register(DotConstantImpl.class.getPackage(), new DotsEval());
		// lists package
		evaluationManager.register(MakeListImpl.class.getPackage(), new ListsEval());
		// multisets package
		MultisetsEval multisetsEval = new MultisetsEval(factory);
		evaluationManager.register(NumberOfImpl.class.getPackage(), multisetsEval);
		// terms package
		evaluationManager.register(TupleImpl.class.getPackage(), new TermsEval());
		// variables
		evaluationManager.register(VariableImpl.class, new VariableEval());
		// user operations
		UserOperatorEval userOperatorEval = new UserOperatorEval(evaluationManager);
		evaluationManager.register(UserOperatorImpl.class, userOperatorEval);
		
		evaluationManager.register(AdditionImpl.class, new AdditionEval());
		evaluationManager.register(MultiplicationImpl.class, new MultiplicationEval());
		evaluationManager.register(SubtractionImpl.class, new SubtractionEval());
		evaluationManager.register(DivisionImpl.class, new DivisionEval());
		
		// user extensions
		if(extensionId != null)
		{
			IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(extensionId);
			for(IConfigurationElement e : config)
			{
				try
				{
					IUserExtensions arbitraryOperatorEval = (IUserExtensions) e.createExecutableExtension("class");
					userOperatorEval.setArbitraryOperatorEvaluator(arbitraryOperatorEval);
					
					DataTypeEvaluationManager manager = new DataTypeEvaluationManager();
					manager.register(UserSortImpl.class, arbitraryOperatorEval);
					multisetsEval.setDataTypeEvaluationManager(manager);
				}
				catch(CoreException e1)
				{
					e1.printStackTrace();
				}
			}	
		}

		return evaluationManager;
	}
	
	public static ReversibleOperationManager createReversibleOperationManager(EvaluationManager evaluationManager)
	{
		ReversibleOperationManager reversibleOperationManager = new ReversibleOperationManager(evaluationManager);
		
		reversibleOperationManager.register(AdditionImpl.class, new AdditionEval());
		reversibleOperationManager.register(MultiplicationImpl.class, new MultiplicationEval());
		reversibleOperationManager.register(SubtractionImpl.class, new SubtractionEval());
		reversibleOperationManager.register(DivisionImpl.class, new DivisionEval());
		
		return reversibleOperationManager;
	}
	
	public static ComparisonManager createComparisonManager(EvaluationManager evaluationManager,
			ReversibleOperationManager reversibleOperationManager)
	{
		ComparisonManager comparisonManager = new ComparisonManager();
		
		DatatypesComparator datatypesComparator = new DatatypesComparator();
		comparisonManager.register(NumberConstantImpl.class.getPackage(), datatypesComparator);
		comparisonManager.register(BooleanConstantImpl.class.getPackage(), datatypesComparator);
		comparisonManager.register(StringConstantImpl.class.getPackage(), datatypesComparator);
		
		comparisonManager.register(TupleImpl.class, new TupleComparator(comparisonManager));
		comparisonManager.register(MultiSetOperatorImpl.class, new MultisetComparator(comparisonManager));
		comparisonManager.register(NumberOfImpl.class, new NumberOfComparator(comparisonManager));
		comparisonManager.register(MakeListImpl.class, new ListComparator(comparisonManager));
		
		ReversibleOperationComparator binEval = 
				new ReversibleOperationComparator(evaluationManager, reversibleOperationManager);
		comparisonManager.register(AdditionImpl.class, binEval);
		comparisonManager.register(MultiplicationImpl.class, binEval);
		comparisonManager.register(SubtractionImpl.class, binEval);
		comparisonManager.register(DivisionImpl.class, binEval);
		
		comparisonManager.register(VariableImpl.class, new VariableComparator());
		comparisonManager.register(UserOperatorImpl.class, new UserOperatorComparator());
		
		return comparisonManager;
	}
	
	public static IFiringStrategy getFiringStrategy(String id)
	{
		IConfigurationElement[] config = Platform.getExtensionRegistry().
				getConfigurationElementsFor(id);
		IFiringStrategy strategy = null;
		for(IConfigurationElement e : config)
		{
			try
			{
				strategy = (IFiringStrategy) e.createExecutableExtension("class");
			}
			catch(CoreException e1)
			{
				System.err.println("WRN: failed to load the firing strateg: "
						+ e1.getMessage());
			}
		}
		return strategy;
	}
	
	public static ImageDescriptor getImageDescriptor(String relativePath,
			String pluginID) 
	{
		try
		{
			URL fileURL = Platform.getBundle(pluginID).getEntry(relativePath);
			final URL fullPath = FileLocator.resolve(fileURL);
			final Image i = new Image(Display.getDefault(), fullPath.getPath());
			return ImageDescriptor.createFromImage(i);
		}
		catch(Exception e)
		{
			System.err.println("WRN: Failed to load image: " + relativePath);
		}
		return null;
	}
}
