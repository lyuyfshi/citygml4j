package org.citygml4j.impl.gml.valueObjects;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.model.gml.GMLClass;
import org.citygml4j.model.gml.basicTypes.Code;
import org.citygml4j.model.gml.basicTypes.Measure;
import org.citygml4j.model.gml.valueObjects.ScalarValue;

public class ScalarValueImpl implements ScalarValue {
	private Boolean _boolean;
	private Code category;
	private Measure quantity;
	private Integer count;
	private Object parent;
	
	public ScalarValueImpl() {
		
	}
	
	public ScalarValueImpl(boolean _boolean) {
		this._boolean = _boolean;
	}
	
	public ScalarValueImpl(Code category) {
		setCategory(category);
	}
	
	public ScalarValueImpl(Measure quantity) {
		setQuantity(quantity);
	}
	
	public ScalarValueImpl(Integer count) {
		this.count = count;
	}

	public GMLClass getGMLClass() {
		return GMLClass.SCALAR_VALUE;
	}

	public Boolean getBoolean() {
		return _boolean;
	}

	public Code getCategory() {
		return category;
	}

	public Measure getQuantity() {
		return quantity;
	}

	public Integer getCount() {
		return count;
	}

	public boolean isSetBoolean() {
		return _boolean != null;
	}

	public boolean isSetCategory() {
		return category != null;
	}

	public boolean isSetQuantity() {
		return quantity != null;
	}

	public boolean isSetCount() {
		return count != null;
	}

	public void setBoolean(Boolean _boolean) {
		this._boolean = _boolean;
		
		unsetCategory();
		unsetCount();
		unsetQuantity();
	}

	public void setCategory(Code category) {
		if (category != null)
			category.setParent(this);
		
		this.category = category;
		
		unsetBoolean();
		unsetCount();
		unsetQuantity();
	}

	public void setQuantity(Measure quantity) {
		if (quantity != null)
			quantity.setParent(this);
		
		this.quantity = quantity;
		
		unsetBoolean();
		unsetCategory();
		unsetCount();
	}

	public void setCount(Integer count) {
		this.count = count;
		
		unsetBoolean();
		unsetCategory();
		unsetQuantity();
	}

	public void unsetBoolean() {
		_boolean = null;
	}

	public void unsetCategory() {
		if (isSetCategory())
			category.unsetParent();
		
		category = null;
	}

	public void unsetQuantity() {
		if (isSetQuantity())
			quantity.unsetParent();
		
		quantity = null;
	}

	public void unsetCount() {
		count = null;
	}
	
	public Object getParent() {
		return parent;
	}

	public boolean isSetParent() {
		return parent != null;
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}

	public void unsetParent() {
		parent = null;
	}

	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		ScalarValue copy = (target == null) ? new ScalarValueImpl() : (ScalarValue)target;
		
		if (isSetBoolean())
			copy.setBoolean(copyBuilder.copy(_boolean));

		if (isSetCategory()) {
			copy.setCategory((Code)copyBuilder.copy(category));
			if (copy.getCategory() == category)
				category.setParent(this);
		}
		
		if (isSetCount())
			copy.setCount((Integer)copyBuilder.copy(count));
		
		if (isSetQuantity()) {
			copy.setQuantity((Measure)copyBuilder.copy(quantity));
			if (copy.getQuantity() == quantity)
				quantity.setParent(this);
		}
		
		copy.unsetParent();

		return copy;
	}

	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new ScalarValueImpl(), copyBuilder);
	}

}