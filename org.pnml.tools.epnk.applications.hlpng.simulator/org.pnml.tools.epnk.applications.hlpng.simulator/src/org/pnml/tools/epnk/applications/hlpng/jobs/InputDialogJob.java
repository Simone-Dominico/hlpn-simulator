package org.pnml.tools.epnk.applications.hlpng.jobs;

import java.util.Map;
import java.util.Set;

import org.eclipse.swt.widgets.Display;
import org.pnml.tools.epnk.applications.hlpng.dialogs.InputDialog;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;

public class InputDialogJob implements Runnable
{
	private InputDialog dialog = null;
	
	private final Display display;
	private final Map<String, String> knownValues;
	private final String text;
	private final String textData;
	private final Set<TermWrapper> varSet;
	
	public InputDialogJob(final Display display, final Map<String, String> knownValues,
			final String text, final String textData, final Set<TermWrapper> varSet)
	{
		this.display = display;
		this.knownValues = knownValues;
		this.text = text;
		this.varSet = varSet;
		this.textData = textData;
	}
	
	@Override
	public void run()
	{
		dialog = new InputDialog(varSet, display, text, knownValues, textData);
	}
	
	public boolean isCanceled()
	{
		return dialog.isCanceled();
	}
	
	public Map<TermWrapper, String> getMapping()
	{
		return dialog.getMapping();
	}

}
