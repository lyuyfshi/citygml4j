package org.citygml4j.model.ade.generic;

import org.citygml4j.model.relief.ADEOfRasterRelief;
import org.w3c.dom.Element;

public class GenericADEPropertyOfRasterRelief extends ADEOfRasterRelief<Element> implements ADEGenericProperty {

    private GenericADEPropertyOfRasterRelief(Element value) {
        super(value);
    }

    public static GenericADEPropertyOfRasterRelief of(Element value) {
        return new GenericADEPropertyOfRasterRelief(value);
    }
}
