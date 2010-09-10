package org.citygml4j.model.citygml.relief;

import java.util.List;

import org.citygml4j.model.citygml.ade.ADEComponent;
import org.citygml4j.model.gml.geometry.aggregates.MultiCurveProperty;

public interface BreaklineRelief extends AbstractReliefComponent {
	public MultiCurveProperty getRidgeOrValleyLines();
	public MultiCurveProperty getBreaklines();
	public List<ADEComponent> getGenericApplicationPropertyOfBreaklineRelief();
	public boolean isSetRidgeOrValleyLines();
	public boolean isSetBreaklines();
	public boolean isSetGenericApplicationPropertyOfBreaklineRelief();
	
	public void setRidgeOrValleyLines(MultiCurveProperty ridgeOrValleyLines);
	public void setBreaklines(MultiCurveProperty breaklines);	
	public void addGenericApplicationPropertyOfBreaklineRelief(ADEComponent ade);
	public void setGenericApplicationPropertyOfBreaklineRelief(List<ADEComponent> ade);
	public void unsetRidgeOrValleyLines();
	public void unsetBreaklines();
	public void unsetGenericApplicationPropertyOfBreaklineRelief();
	public boolean unsetGenericApplicationPropertyOfBreaklineRelief(ADEComponent ade);
}
