package org.citygml4j.model.gml;

import org.citygml4j.builder.copy.Copyable;
import org.citygml4j.commons.child.Child;

public interface ScalarValue extends GML, Child, Copyable {
	public Boolean getBoolean();
	public Code getCategory();
	public Measure getQuantity();
	public Integer getCount();
	public boolean isSetBoolean();
	public boolean isSetCategory();
	public boolean isSetQuantity();
	public boolean isSetCount();
	
	public void setBoolean(Boolean _boolean);
	public void setCategory(Code category);
	public void setQuantity(Measure quantity);
	public void setCount(Integer count);
	public void unsetBoolean();
	public void unsetCategory();
	public void unsetQuantity();
	public void unsetCount();
}
