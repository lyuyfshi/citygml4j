package org.citygml4j.model.ade.generic;

import org.citygml4j.model.pointcloud.ADEOfPointCloud;
import org.w3c.dom.Element;

public class GenericADEPropertyOfPointCloud extends ADEOfPointCloud<Element> implements ADEGenericProperty {

    private GenericADEPropertyOfPointCloud(Element value) {
        super(value);
    }

    public static GenericADEPropertyOfPointCloud of(Element value) {
        return new GenericADEPropertyOfPointCloud(value);
    }
}
