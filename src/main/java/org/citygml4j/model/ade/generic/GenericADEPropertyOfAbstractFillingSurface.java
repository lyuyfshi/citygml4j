package org.citygml4j.model.ade.generic;

import org.citygml4j.model.construction.ADEOfAbstractFillingSurface;
import org.w3c.dom.Element;

public class GenericADEPropertyOfAbstractFillingSurface extends ADEOfAbstractFillingSurface<Element> implements ADEGenericProperty {

    private GenericADEPropertyOfAbstractFillingSurface(Element value) {
        super(value);
    }

    public static GenericADEPropertyOfAbstractFillingSurface of(Element value) {
        return new GenericADEPropertyOfAbstractFillingSurface(value);
    }
}
