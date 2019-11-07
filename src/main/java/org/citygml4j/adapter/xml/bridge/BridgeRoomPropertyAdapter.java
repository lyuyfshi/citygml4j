package org.citygml4j.adapter.xml.bridge;

import org.citygml4j.model.bridge.BridgeRoomProperty;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.gml.adapter.feature.AbstractFeaturePropertyAdapter;

import javax.xml.namespace.QName;

public class BridgeRoomPropertyAdapter extends AbstractFeaturePropertyAdapter<BridgeRoomProperty> {

    @Override
    public BridgeRoomProperty createObject(QName name) throws ObjectBuildException {
        return new BridgeRoomProperty();
    }
}