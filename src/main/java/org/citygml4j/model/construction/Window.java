package org.citygml4j.model.construction;

import org.xmlobjects.gml.model.common.ChildList;

import java.util.List;

public class Window extends AbstractFillingElement<WindowSurfaceProperty> {
    private List<ADEPropertyOfWindow> adeProperties;

    public List<ADEPropertyOfWindow> getADEPropertyOfWindow() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setADEPropertyOfWindow(List<ADEPropertyOfWindow> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}