package org.citygml4j.model.transportation;

import org.citygml4j.model.core.AbstractThematicSurface;
import org.citygml4j.model.core.AbstractUnoccupiedSpace;
import org.citygml4j.model.core.ClosureSurface;
import org.citygml4j.model.generics.GenericThematicSurface;
import org.xmlobjects.gml.model.basictypes.Code;
import org.xmlobjects.model.ChildList;

import java.util.List;

public class Hole extends AbstractUnoccupiedSpace {
    private Code classifier;
    private List<ADEPropertyOfHole> adeProperties;

    @Override
    public boolean isValidBoundarySurface(AbstractThematicSurface boundarySurface) {
        return boundarySurface instanceof HoleSurface
                || boundarySurface instanceof ClosureSurface
                || boundarySurface instanceof GenericThematicSurface;
    }

    public Code getClassifier() {
        return classifier;
    }

    public void setClassifier(Code classifier) {
        this.classifier = asChild(classifier);
    }

    public List<ADEPropertyOfHole> getADEPropertiesOfHole() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertiesOfHole(List<ADEPropertyOfHole> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}