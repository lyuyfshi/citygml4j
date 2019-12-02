package org.citygml4j.xml.adapter.tunnel;

import org.citygml4j.model.ade.generic.GenericADEPropertyOfTunnel;
import org.citygml4j.model.tunnel.ADEPropertyOfTunnel;
import org.citygml4j.model.tunnel.Tunnel;
import org.citygml4j.model.tunnel.TunnelPartProperty;
import org.citygml4j.util.CityGMLConstants;
import org.citygml4j.xml.adapter.CityGMLBuilderHelper;
import org.citygml4j.xml.adapter.CityGMLSerializerHelper;
import org.xmlobjects.annotation.XMLElement;
import org.xmlobjects.builder.ObjectBuildException;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLReadException;
import org.xmlobjects.stream.XMLReader;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.xml.Attributes;
import org.xmlobjects.xml.Element;
import org.xmlobjects.xml.Namespaces;

import javax.xml.namespace.QName;

@XMLElement(name = "Tunnel", namespaceURI = CityGMLConstants.CITYGML_3_0_TUNNEL_NAMESPACE)
public class TunnelAdapter extends AbstractTunnelAdapter<Tunnel> {
    private final QName substitutionGroup = new QName(CityGMLConstants.CITYGML_3_0_TUNNEL_NAMESPACE, "AbstractGenericApplicationPropertyOfTunnel");

    @Override
    public Tunnel createObject(QName name) throws ObjectBuildException {
        return new Tunnel();
    }

    @Override
    public void buildChildObject(Tunnel object, QName name, Attributes attributes, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (CityGMLConstants.CITYGML_3_0_TUNNEL_NAMESPACE.equals(name.getNamespaceURI()) && "tunnelPart".equals(name.getLocalPart())) {
            object.getTunnelParts().add(reader.getObjectUsingBuilder(TunnelPartPropertyAdapter.class));
            return;
        } else if (CityGMLBuilderHelper.isADENamespace(name.getNamespaceURI())) {
            buildADEProperty(object, name, reader);
            return;
        }

        super.buildChildObject(object, name, attributes, reader);
    }

    @Override
    public void buildADEProperty(Tunnel object, QName name, XMLReader reader) throws ObjectBuildException, XMLReadException {
        if (!CityGMLBuilderHelper.addADEProperty(name, ADEPropertyOfTunnel.class, object.getADEPropertiesOfTunnel(),
                GenericADEPropertyOfTunnel::of, reader, substitutionGroup))
            super.buildADEProperty(object, name, reader);
    }

    @Override
    public Element createElement(Tunnel object, Namespaces namespaces) throws ObjectSerializeException {
        return Element.of(CityGMLConstants.CITYGML_3_0_TUNNEL_NAMESPACE, "Tunnel");
    }

    @Override
    public void writeChildElements(Tunnel object, Namespaces namespaces, XMLWriter writer) throws ObjectSerializeException, XMLWriteException {
        super.writeChildElements(object, namespaces, writer);

        for (TunnelPartProperty property : object.getTunnelParts())
            writer.writeElementUsingSerializer(Element.of(CityGMLConstants.CITYGML_3_0_TUNNEL_NAMESPACE, "tunnelPart"), property, TunnelPartPropertyAdapter.class, namespaces);

        for (ADEPropertyOfTunnel<?> property : object.getADEPropertiesOfTunnel())
            CityGMLSerializerHelper.serializeADEProperty(property, namespaces, writer);
    }
}
