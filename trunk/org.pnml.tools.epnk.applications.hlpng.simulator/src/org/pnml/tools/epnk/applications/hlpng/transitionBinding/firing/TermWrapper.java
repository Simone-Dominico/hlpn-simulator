package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import org.pnml.tools.epnk.applications.hlpng.runtime.AbstractValue;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;


public abstract class TermWrapper extends AbstractValue
{
	public abstract String getName();
	public abstract Term getRootTerm();
}
