package org.pnml.tools.epnk.applications.hlpng.transitionBinding.firing;

import java.util.List;

import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

public interface ITermWrapper
{
	public Term getRootTerm();
	public List<ITermWrapper> getSubterms();
}
