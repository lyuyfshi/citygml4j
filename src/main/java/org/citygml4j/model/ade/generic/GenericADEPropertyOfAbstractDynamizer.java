package org.citygml4j.model.ade.generic;

import org.citygml4j.model.core.ADEPropertyOfAbstractDynamizer;
import org.w3c.dom.Element;

public class GenericADEPropertyOfAbstractDynamizer extends ADEPropertyOfAbstractDynamizer<Element> implements ADEGenericProperty {

    private GenericADEPropertyOfAbstractDynamizer(Element value) {
        super(value);
    }

    public static GenericADEPropertyOfAbstractDynamizer of(Element value) {
        return new GenericADEPropertyOfAbstractDynamizer(value);
    }
}