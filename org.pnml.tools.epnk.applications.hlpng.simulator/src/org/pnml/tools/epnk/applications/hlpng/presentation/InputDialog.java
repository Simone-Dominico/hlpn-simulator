package org.pnml.tools.epnk.applications.hlpng.presentation;

import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing.TermWrapper;

public class InputDialog
{
	final int EDITABLE_COLUMN = 1;

	private Shell shell = null;
	
	public InputDialog(Set<TermWrapper> variables, Display dialog)
	{
		Monitor primary = dialog.getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    
	    shell = new Shell(dialog);
	    Rectangle rect = shell.getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    
		shell.setLocation(x, y);
		shell.setText("Assign values");
		shell.setLayout(new FillLayout());
		final Table table = new Table(shell, SWT.BORDER);

		TableColumn tc1 = new TableColumn(table, SWT.LEFT);
		TableColumn tc2 = new TableColumn(table, SWT.RIGHT);

		tc1.setText("Variable name");
		tc2.setText("Variable value");

		tc1.setWidth(100);
		tc2.setWidth(100);

		table.setHeaderVisible(true);

		for(TermWrapper wrapper : variables)
		{
			TableItem item = new TableItem(table, SWT.NONE);
			item.setData(wrapper);
			item.setText(new String[] { wrapper.getName(), ""});
		}

		final TableEditor editor = new TableEditor(table);
		table.addSelectionListener(new TableSelectionListener(table, EDITABLE_COLUMN, editor));

		shell.pack();
		shell.open();
		while(!shell.isDisposed())
		{
			if(!dialog.readAndDispatch())
			{
				dialog.sleep();	
			}
		}
	}
}