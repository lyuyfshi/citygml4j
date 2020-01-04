package org.citygml4j.model.appearance;

import org.citygml4j.model.CityGMLObject;
import org.citygml4j.visitor.Visitable;
import org.xmlobjects.gml.model.feature.AbstractFeature;
import org.xmlobjects.gml.model.geometry.Envelope;
import org.xmlobjects.gml.util.EnvelopeOptions;
import org.xmlobjects.model.ChildList;

import java.util.List;

public abstract class AbstractSurfaceData extends AbstractFeature implements CityGMLObject, Visitable {
    private Boolean isFront;
    private List<ADEPropertyOfAbstractSurfaceData<?>> adeProperties;

    public Boolean getIsFront() {
        return isFront != null ? isFront : true;
    }

    public boolean isSetIsFront() {
        return isFront != null;
    }

    public void setIsFront(Boolean front) {
        isFront = front;
    }

    public List<ADEPropertyOfAbstractSurfaceData<?>> getADEPropertiesOfAbstractSurfaceData() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfAbstractSurfaceData(List<ADEPropertyOfAbstractSurfaceData<?>> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }

    @Override
    public void updateEnvelope(Envelope envelope, EnvelopeOptions options) {
    }
}
