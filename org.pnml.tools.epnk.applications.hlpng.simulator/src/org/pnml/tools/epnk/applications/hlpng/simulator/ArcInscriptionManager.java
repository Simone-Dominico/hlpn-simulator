package org.pnml.tools.epnk.applications.hlpng.simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pnml.tools.epnk.applications.hlpng.comparators.ComparatorManager;
import org.pnml.tools.epnk.applications.hlpng.operations.AbstractOperator;
import org.pnml.tools.epnk.applications.hlpng.operations.TermManager;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pntypes.hlpng.pntd.hlpngdefinition.Arc;
import org.pnml.tools.epnk.pntypes.hlpngs.datatypes.terms.Term;

import runtime.AbstractValue;
import runtime.MSValue;
import runtime.PlaceMarking;

public class ArcInscriptionManager
{
	private Map<String, SructuralPatternMatcher> arcMap = null;
	
	public ArcInscriptionManager(TermManager operatorManager, FlatAccess flatAccess)
	{
		arcMap = new HashMap<String, SructuralPatternMatcher>();
		
		ComparatorManager comparatorManager = new ComparatorManager();
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Transition transition : flatAccess.getTransitions())
		{
			List<org.pnml.tools.epnk.pnmlcoremodel.Arc> arcs = 
					new ArrayList<org.pnml.tools.epnk.pnmlcoremodel.Arc>();
			arcs.addAll(transition.getIn());
			arcs.addAll(transition.getOut());
			
			for(org.pnml.tools.epnk.pnmlcoremodel.Arc arc : arcs)
			{
				Arc hlArc = (Arc) arc;
				if(hlArc.getHlinscription() != null && hlArc.getHlinscription().getStructure() != null)
				{
					Term term = hlArc.getHlinscription().getStructure();
					AbstractOperator handler = operatorManager.getOperator(term.getClass());
					arcMap.put(hlArc.getId(), new SructuralPatternMatcher((MSValue)handler.handle(term),
							comparatorManager));
				}
				else
				{
					arcMap.put(hlArc.getId(), new SructuralPatternMatcher(null,
							comparatorManager));
				}
			}
		}
	}
	
	public List<AbstractValue> matchesInscription(Arc arc, PlaceMarking marking, boolean refresh)
	{
		MSValue msValue = marking.getMsValue();
		
		SructuralPatternMatcher matcher = arcMap.get(arc.getId());
		
		return matcher.match(msValue);
	}
}
