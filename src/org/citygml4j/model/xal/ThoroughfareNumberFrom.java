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
package org.citygml4j.model.xal;

import java.util.List;

import org.citygml4j.model.common.child.Child;
import org.citygml4j.model.common.copy.Copyable;
import org.citygml4j.model.common.visitor.XALFunctor;
import org.citygml4j.model.common.visitor.XALVisitor;

public interface ThoroughfareNumberFrom extends XAL, GrPostal, Child, Copyable {
	public List<ThoroughfareNumberFromContent> getContent();
	public boolean isSetContent();
	
	public void addContent(ThoroughfareNumberFromContent content);
	public void setContent(List<ThoroughfareNumberFromContent> content);
	public void addAddressLine(AddressLine addressLine);
	public void addThoroughfareNumberPrefix(ThoroughfareNumberPrefix thoroughfareNumberPrefix);
	public void addThoroughfareNumber(ThoroughfareNumber thoroughfareNumber);
	public void addThoroughfareNumberSuffix(ThoroughfareNumberSuffix thoroughfareNumberSuffix);
	public void addString(String string);
	public void unsetContent();
	public boolean unsetContent(ThoroughfareNumberFromContent content);
	public boolean unsetAddressLine(AddressLine addressLine);
	public boolean unsetThoroughfareNumberPrefix(ThoroughfareNumberPrefix thoroughfareNumberPrefix);
	public boolean unsetThoroughfareNumber(ThoroughfareNumber thoroughfareNumber);
	public boolean unsetThoroughfareNumberSuffix(ThoroughfareNumberSuffix thoroughfareNumberSuffix);
	public boolean unsetString(String string);
	
	public void visit(XALVisitor visitor);
	public <T> T visit(XALFunctor<T> visitor);
}
