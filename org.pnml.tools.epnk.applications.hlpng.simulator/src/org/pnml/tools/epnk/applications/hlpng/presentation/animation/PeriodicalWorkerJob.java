package org.pnml.tools.epnk.applications.hlpng.presentation.animation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;

/*
 * Author: Mindaugas Laganeckas
 * Email: s100972@student.dtu.dk
 */

public class PeriodicalWorkerJob extends UIJob
{
	private IWorker worker = null;
	
	public PeriodicalWorkerJob(Display jobDisplay, String name, IWorker worker)
    {
	    super(jobDisplay, name);
	    
	    this.worker = worker;
    }

	@Override
    public IStatus runInUIThread(IProgressMonitor monitor)
    {
	    if(!worker.isCompleted())
	    {
	    	worker.work();
	    	schedule(worker.getSimulationPause());	
	    }
	    
	    return Status.OK_STATUS;
    }
}
