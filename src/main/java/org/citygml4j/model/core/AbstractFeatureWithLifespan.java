package org.citygml4j.model.core;

import org.citygml4j.model.CityGMLObject;
import org.xmlobjects.gml.model.common.ChildList;
import org.xmlobjects.gml.model.feature.AbstractFeature;

import java.time.OffsetDateTime;
import java.util.List;

public abstract class AbstractFeatureWithLifespan extends AbstractFeature implements CityGMLObject {
    private OffsetDateTime creationDate;
    private OffsetDateTime terminationDate;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;
    private List<ADEPropertyOfAbstractFeatureWithLifespan> adeProperties;

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public OffsetDateTime getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(OffsetDateTime terminationDate) {
        this.terminationDate = terminationDate;
    }

    public OffsetDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(OffsetDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public OffsetDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(OffsetDateTime validTo) {
        this.validTo = validTo;
    }

    public List<ADEPropertyOfAbstractFeatureWithLifespan> getADEPropertiesOfAbstractFeatureWithLifespan() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfAbstractFeatureWithLifespan(List<ADEPropertyOfAbstractFeatureWithLifespan> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}