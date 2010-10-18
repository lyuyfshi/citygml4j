/*
 * This file is part of citygml4j.
 * Copyright (c) 2007 - 2010
 * Institute for Geodesy and Geoinformation Science
 * Technische Universitšt Berlin, Germany
 * http://www.igg.tu-berlin.de/
 *
 * The citygml4j library is free software:
 * you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see 
 * <http://www.gnu.org/licenses/>.
 */
package org.citygml4j.impl.gml.geometry.complexes;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.impl.gml.geometry.GeometryPropertyImpl;
import org.citygml4j.model.gml.GMLClass;
import org.citygml4j.model.gml.geometry.complexes.CompositeCurve;
import org.citygml4j.model.gml.geometry.complexes.CompositeCurveProperty;

public class CompositeCurvePropertyImpl extends GeometryPropertyImpl<CompositeCurve> implements CompositeCurveProperty {
	
	public CompositeCurve getCompositeCurve() {
		return super.getGeometry();
	}
	
	public boolean isSetCompositeCurve() {
		return super.isSetGeometry();
	}
	
	public void setCompositeCurve(CompositeCurve compositeCurve) {
		super.setGeometry(compositeCurve);
	}
	
	public void unsetCompositeCurve() {
		super.unsetGeometry();
	}
	
	public GMLClass getGMLClass() {
		return GMLClass.COMPOSITE_CURVE_PROPERTY;
	}
	
	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new CompositeCurvePropertyImpl(), copyBuilder);
	}
	
	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		CompositeCurveProperty copy = (target == null) ? new CompositeCurvePropertyImpl() : (CompositeCurveProperty)target;
		return super.copyTo(copy, copyBuilder);
	}
	
}
