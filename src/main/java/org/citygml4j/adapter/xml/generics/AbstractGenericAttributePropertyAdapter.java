package org.citygml4j.adapter.xml.generics;

import org.citygml4j.model.generics.AbstractGenericAttributeProperty;
import org.xmlobjects.gml.adapter.base.AbstractInlinePropertyAdapter;

import javax.xml.namespace.QName;

public class AbstractGenericAttributePropertyAdapter extends AbstractInlinePropertyAdapter<AbstractGenericAttributeProperty> {

    @Override
    public AbstractGenericAttributeProperty createObject(QName name) {
        return new AbstractGenericAttributeProperty();
    }
}