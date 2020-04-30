/*
 * citygml4j - The Open Source Java API for CityGML
 * https://github.com/citygml4j
 *
 * Copyright 2013-2020 Claus Nagel <claus.nagel@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package generic_ade_handling;

import helpers.Logger;
import helpers.Util;
import implementing_ades.module.TestADEModule;
import org.citygml4j.CityGMLContext;
import org.citygml4j.model.CityGMLVersion;
import org.citygml4j.model.ade.generic.GenericADEPropertyOfAbstractBuilding;
import org.citygml4j.model.building.Building;
import org.citygml4j.model.core.AbstractFeature;
import org.citygml4j.xml.module.citygml.CityGMLModules;
import org.citygml4j.xml.module.citygml.CoreModule;
import org.citygml4j.xml.reader.ChunkMode;
import org.citygml4j.xml.reader.CityGMLInputFactory;
import org.citygml4j.xml.reader.CityGMLReader;
import org.citygml4j.xml.writer.CityGMLChunkWriter;
import org.citygml4j.xml.writer.CityGMLOutputFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xmlobjects.XMLObjects;
import org.xmlobjects.gml.model.GMLObject;
import org.xmlobjects.gml.model.geometry.DirectPositionList;
import org.xmlobjects.gml.model.geometry.aggregates.MultiSurface;
import org.xmlobjects.gml.model.geometry.primitives.LinearRing;
import org.xmlobjects.gml.model.geometry.primitives.Polygon;
import org.xmlobjects.gml.model.geometry.primitives.SurfaceProperty;
import org.xmlobjects.serializer.ObjectSerializeException;
import org.xmlobjects.stream.XMLWriteException;
import org.xmlobjects.stream.XMLWriter;
import org.xmlobjects.stream.XMLWriterFactory;
import org.xmlobjects.xml.Namespaces;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMResult;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class WritingGenericADE {

    public static void main(String[] args) throws Exception {
        Logger log = Logger.start(WritingGenericADE.class);

        CityGMLContext context = CityGMLContext.newInstance();

        CityGMLInputFactory in = context.createCityGMLInputFactory()
                .useChunkMode(ChunkMode.CHUNK_BY_CITY_MODEL_MEMBERS);

        Path file = Util.SAMPLE_DATA_DIR.resolve("lod2_buildings_v3.gml");
        log.print("Reading the first building from the file " + file);

        Building building = null;
        try (CityGMLReader reader = in.createCityGMLReader(file)) {
            while (reader.hasNext()) {
                AbstractFeature feature = reader.next();
                if (feature instanceof Building) {
                    building = (Building) feature;
                    log.print("Found " + reader.getName().getLocalPart() + " with gml:id " + feature.getId());
                    break;
                }
            }

            if (building == null)
                throw new Exception("Failed to read a building from file " + file);
        }

        log.print("Enriching the building with TestADE properties and features");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document document = factory.newDocumentBuilder().newDocument();

        log.print("Adding an owner name");
        Element ownerName = document.createElementNS(TestADEModule.NAMESPACE_1_0, "ownerName");
        ownerName.setTextContent("Smith");
        building.getADEPropertiesOfAbstractBuilding().add(GenericADEPropertyOfAbstractBuilding.of(ownerName));

        log.print("Adding an energy performance certification");
        Element certificationProperty = document.createElementNS(TestADEModule.NAMESPACE_1_0, "energyPerformanceCertification");
        Node certification = certificationProperty.appendChild(document.createElementNS(TestADEModule.NAMESPACE_1_0, "EnergyPerformanceCertification"));
        certification.appendChild(document.createElementNS(TestADEModule.NAMESPACE_1_0, "certificationName")).setTextContent("certName");
        certification.appendChild(document.createElementNS(TestADEModule.NAMESPACE_1_0, "certificationid")).setTextContent("certId");
        building.getADEPropertiesOfAbstractBuilding().add(GenericADEPropertyOfAbstractBuilding.of(certificationProperty));

        log.print("Adding a building unit with LoD2 geometry and lighting facility");
        Element buildingUnitProperty = document.createElementNS(TestADEModule.NAMESPACE_1_0, "buildingUnit");
        Node buildingUnit = buildingUnitProperty.appendChild(document.createElementNS(TestADEModule.NAMESPACE_1_0, "BuildingUnit"));

        Node lod2MultiSurfaceProperty = buildingUnit.appendChild(document.createElementNS(TestADEModule.NAMESPACE_1_0, "lod2MultiSurface"));
        DirectPositionList posList = new DirectPositionList(6.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 8.0, 0.0, 6.0, 8.0, 0.0, 6.0, 0.0, 0.0);
        posList.setSrsDimension(3);
        LinearRing linearRing = new LinearRing(posList);
        Polygon polygon = new Polygon(linearRing);
        MultiSurface multiSurface = new MultiSurface();
        multiSurface.getSurfaceMember().add(new SurfaceProperty(polygon));
        appendChild(lod2MultiSurfaceProperty, multiSurface, CityGMLVersion.v2_0, context.getXMLObjects());

        Node equippedWith = buildingUnit.appendChild(document.createElementNS(TestADEModule.NAMESPACE_1_0, "equippedWith"));
        Node lightingFacilities = equippedWith.appendChild(document.createElementNS(TestADEModule.NAMESPACE_1_0, "LightingFacilities"));
        Element totalValue = document.createElementNS(TestADEModule.NAMESPACE_1_0, "totalValue");
        totalValue.setTextContent("4000.0");
        totalValue.setAttribute("uom", "W");
        lightingFacilities.appendChild(totalValue);
        buildingUnit.appendChild(equippedWith);

        building.getADEPropertiesOfAbstractBuilding().add(GenericADEPropertyOfAbstractBuilding.of(buildingUnitProperty));

        CityGMLVersion version = CityGMLVersion.v2_0;
        CityGMLOutputFactory out = context.createCityGMLOutputFactory(version);

        Path output = Util.getOutputFile();
        log.print("Writing the ADE-enriched building as CityGML " + version + " file " + output);

        try (CityGMLChunkWriter writer = out.createCityGMLChunkWriter(output, StandardCharsets.UTF_8.name())) {
            writer.withIndentString("  ")
                    .withSchemaLocation(TestADEModule.NAMESPACE_1_0, Util.OUTPUT_DIR.relativize(
                            Util.SCHEMAS_DIR.resolve("test-ade.xsd")).toString())
                    .withDefaultPrefixes()
                    .withPrefix("test", TestADEModule.NAMESPACE_1_0)
                    .withDefaultNamespace(CoreModule.of(version).getNamespaceURI())
                    .writeMember(building);
        }

        log.finish();
    }

    static void appendChild(Node parent, GMLObject child, CityGMLVersion version, XMLObjects xmlObjects) throws TransformerConfigurationException, XMLWriteException, ObjectSerializeException {
        DOMResult result = new DOMResult(parent);
        XMLWriterFactory xmlWriterFactory = XMLWriterFactory.newInstance(xmlObjects);
        XMLWriter writer = xmlWriterFactory.createWriter(result);
        xmlObjects.toXML(writer, child, Namespaces.of(CityGMLModules.of(version).getNamespaces()));
    }
}
