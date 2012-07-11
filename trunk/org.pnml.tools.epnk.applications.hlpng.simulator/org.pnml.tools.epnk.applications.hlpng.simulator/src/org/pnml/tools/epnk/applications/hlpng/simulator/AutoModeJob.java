package org.pnml.tools.epnk.applications.hlpng.simulator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;

public class AutoModeJob extends UIJob
{
	private IWorker worker = null;
	private boolean stopped = true;
	
	public AutoModeJob(Display jobDisplay, String name, IWorker worker)
    {
	    super(jobDisplay, name);
	    
	    this.worker = worker;
    }

	public boolean isStopped()
    {
    	return stopped;
    }

	public void setStopped(boolean stopped)
    {
    	this.stopped = stopped;
    }

	@Override
    public IStatus runInUIThread(IProgressMonitor monitor)
    {
	    if(!stopped)
	    {
	    	worker.work();
	    	schedule(worker.getSimulationPause());	
	    }
	    
	    return Status.OK_STATUS;
    }
}
