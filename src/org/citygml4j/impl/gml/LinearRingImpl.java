package org.citygml4j.impl.gml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.citygml4j.builder.copy.CopyBuilder;
import org.citygml4j.commons.child.ChildList;
import org.citygml4j.geometry.BoundingBox;
import org.citygml4j.model.gml.Coord;
import org.citygml4j.model.gml.Coordinates;
import org.citygml4j.model.gml.DirectPosition;
import org.citygml4j.model.gml.DirectPositionList;
import org.citygml4j.model.gml.GMLClass;
import org.citygml4j.model.gml.LinearRing;
import org.citygml4j.model.gml.PointProperty;
import org.citygml4j.model.gml.PointRep;
import org.citygml4j.model.gml.PosOrPointPropertyOrPointRep;
import org.citygml4j.visitor.GMLFunction;
import org.citygml4j.visitor.GMLVisitor;
import org.citygml4j.visitor.GeometryFunction;
import org.citygml4j.visitor.GeometryVisitor;

public class LinearRingImpl extends AbstractRingImpl implements LinearRing {
	private List<PosOrPointPropertyOrPointRep> controlPoints;
	private DirectPositionList posList;
	private Coordinates coordinates;
	private List<Coord> coord;

	public GMLClass getGMLClass() {
		return GMLClass.LINEARRING;
	}

	public BoundingBox calcBoundingBox() {
		BoundingBox bbox = new BoundingBox();
		List<Double> points = toList3d();

		for (int i = 0; i < points.size(); i += 3)
			bbox.update(points.get(i), points.get(i + 1), points.get(i + 2));

		if (bbox.getLowerCorner().isEqual(Double.MAX_VALUE) && 
				bbox.getUpperCorner().isEqual(-Double.MAX_VALUE))
			return null;
		else
			return bbox;
	}

	public void addCoord(Coord coord) {
		if (this.coord == null)
			this.coord = new ChildList<Coord>(this);

		this.coord.add(coord);
	}

	public void addPointProperty(PointProperty pointProperty) {
		if (controlPoints == null)
			controlPoints = new ChildList<PosOrPointPropertyOrPointRep>(this);

		controlPoints.add(new PosOrPointPropertyOrPointRepImpl(pointProperty));
	}

	public void addPointRep(PointRep pointRep) {
		if (controlPoints == null)
			controlPoints = new ChildList<PosOrPointPropertyOrPointRep>(this);

		controlPoints.add(new PosOrPointPropertyOrPointRepImpl(pointRep));
	}

	public void addPos(DirectPosition pos) {
		if (controlPoints == null)
			controlPoints = new ChildList<PosOrPointPropertyOrPointRep>(this);

		controlPoints.add(new PosOrPointPropertyOrPointRepImpl(pos));
	}

	public void addControlPoint(PosOrPointPropertyOrPointRep controlPoint) {
		if (controlPoints == null)
			controlPoints = new ChildList<PosOrPointPropertyOrPointRep>(this);

		controlPoints.add(controlPoint);
	}

	public List<Coord> getCoord() {
		if (coord == null)
			coord = new ChildList<Coord>(this);

		return coord;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public DirectPositionList getPosList() {
		return posList;
	}

	public List<PosOrPointPropertyOrPointRep> getPosOrPointPropertyOrPointRep() {
		if (controlPoints == null)
			controlPoints = new ChildList<PosOrPointPropertyOrPointRep>(this);

		return controlPoints;
	}

	public boolean isSetCoord() {
		return coord != null && !coord.isEmpty();
	}

	public boolean isSetCoordinates() {
		return coordinates != null;
	}

	public boolean isSetPosList() {
		return posList != null;
	}

	public boolean isSetPosOrPointPropertyOrPointRep() {
		return controlPoints != null && !controlPoints.isEmpty();
	}

	public void setCoordinates(Coordinates coordinates) {
		if (coordinates != null)
			coordinates.setParent(this);

		this.coordinates = coordinates;
	}

	public void setPosList(DirectPositionList posList) {
		if (posList != null)
			posList.setParent(this);

		this.posList = posList;
	}

	public void setCoord(List<Coord> coord) {
		this.coord = new ChildList<Coord>(this, coord);
	}

	public void setPosOrPointPropertyOrPointRep(List<PosOrPointPropertyOrPointRep> controlPoints) {
		this.controlPoints = new ChildList<PosOrPointPropertyOrPointRep>(this, controlPoints);
	}

	public List<Double> toList3d() {
		List<Double> tmp = new ArrayList<Double>();

		if (isSetPosList())
			tmp.addAll(posList.toList3d());

		if (isSetPosOrPointPropertyOrPointRep())
			for (PosOrPointPropertyOrPointRep controlPoint : controlPoints)
				tmp.addAll(controlPoint.toList3d());

		if (isSetCoord())
			for (Coord value : coord) 
				tmp.addAll(value.toList3d());

		if (isSetCoordinates())
			tmp.addAll(coordinates.toList3d());

		return tmp;
	}

	public List<Double> toList3d(boolean reverseOrder) {
		List<Double> points = toList3d();

		if (reverseOrder) {
			List<Double> reversed = new ArrayList<Double>();

			for (int i = points.size() - 3; i >= 0; i -=3)
				reversed.addAll(points.subList(i, i + 3));

			points = reversed;
		}

		return points;
	}

	public void unsetCoord() {
		if (isSetCoord()) {
			for (Coord value : coord)
				if (value != null)
					value.unsetParent();
		}

		coord = null;
	}

	public void unsetCoordinates() {
		if (isSetCoordinates())
			coordinates.unsetParent();

		coordinates = null;
	}

	public boolean unsetPosOrPointPropertyOrPointRep(PosOrPointPropertyOrPointRep controlPoint) {
		return isSetPosOrPointPropertyOrPointRep() ? controlPoints.remove(controlPoint) : false;	
	}

	public boolean unsetPointProperty(PointProperty pointProperty) {
		boolean success = false;

		if (isSetPosOrPointPropertyOrPointRep()) {
			Iterator<PosOrPointPropertyOrPointRep> iter = controlPoints.iterator();
			while (iter.hasNext()) {
				PosOrPointPropertyOrPointRep controlPoint = iter.next();

				if (controlPoint != null && controlPoint.getPointProperty().equals(pointProperty)) {
					iter.remove();
					success = true;
					break;
				}
			}
		}

		return success;
	}

	public boolean unsetPointRep(PointRep pointRep) {
		boolean success = false;

		if (isSetPosOrPointPropertyOrPointRep()) {
			Iterator<PosOrPointPropertyOrPointRep> iter = controlPoints.iterator();
			while (iter.hasNext()) {
				PosOrPointPropertyOrPointRep controlPoint = iter.next();

				if (controlPoint != null && controlPoint.getPointRep().equals(pointRep)) {
					iter.remove();
					success = true;
					break;
				}
			}
		}

		return success;
	}

	public boolean unsetPos(DirectPosition pos) {
		boolean success = false;

		if (isSetPosOrPointPropertyOrPointRep()) {
			Iterator<PosOrPointPropertyOrPointRep> iter = controlPoints.iterator();
			while (iter.hasNext()) {
				PosOrPointPropertyOrPointRep controlPoint = iter.next();

				if (controlPoint != null && controlPoint.getPos().equals(pos)) {
					iter.remove();
					success = true;
					break;
				}
			}
		}

		return success;
	}

	public void unsetPosList() {
		if (isSetPosList())
			posList.unsetParent();

		posList = null;
	}

	public void unsetPosOrPointPropertyOrPointRep() {
		if (isSetPosOrPointPropertyOrPointRep())
			controlPoints.clear();

		controlPoints = null;
	}

	@Override
	public Object copyTo(Object target, CopyBuilder copyBuilder) {
		LinearRing copy = (target == null) ? new LinearRingImpl() : (LinearRing)target;
		super.copyTo(copy, copyBuilder);

		if (isSetPosOrPointPropertyOrPointRep()) {
			for (PosOrPointPropertyOrPointRep part : controlPoints) {
				PosOrPointPropertyOrPointRep copyPart = (PosOrPointPropertyOrPointRep)copyBuilder.copy(part);
				copy.addControlPoint(copyPart);

				if (part != null && copyPart == part)
					part.setParent(this);
			}
		}

		if (isSetPosList()) {
			copy.setPosList((DirectPositionList)copyBuilder.copy(posList));
			if (copy.getPosList() == posList)
				posList.setParent(this);
		}

		if (isSetCoordinates()) {
			copy.setCoordinates((Coordinates)copyBuilder.copy(coordinates));
			if (copy.getCoordinates() == coordinates)
				coordinates.setParent(this);
		}

		if (isSetCoord()) {
			for (Coord part : coord) {
				Coord copyPart = (Coord)copyBuilder.copy(part);
				copy.addCoord(copyPart);

				if (part != null && copyPart == part)
					part.setParent(this);
			}
		}

		return copy;
	}

	public Object copy(CopyBuilder copyBuilder) {
		return copyTo(new LinearRingImpl(), copyBuilder);
	}
	
	public void visit(GeometryVisitor visitor) {
		visitor.accept(this);
	}

	public <T> T apply(GeometryFunction<T> visitor) {
		return visitor.accept(this);
	}
	
	public void visit(GMLVisitor visitor) {
		visitor.accept(this);
	}
	
	public <T> T apply(GMLFunction<T> visitor) {
		return visitor.accept(this);
	}

}
