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
	private long pause = 0;
	
	public AutoModeJob(Display jobDisplay, String name, IWorker worker, long pause)
    {
	    super(jobDisplay, name);
	    
	    this.worker = worker;
	    this.pause = pause;
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
	    	schedule(pause);	
	    }
	    
	    return Status.OK_STATUS;
    }

}
