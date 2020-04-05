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

package reading_citygml;

import org.citygml4j.CityGMLContext;
import org.citygml4j.model.core.AbstractFeature;
import org.citygml4j.xml.module.citygml.CityGMLModules;
import org.citygml4j.xml.reader.ChunkMode;
import org.citygml4j.xml.reader.CityGMLInputFactory;
import org.citygml4j.xml.reader.CityGMLInputFilter;
import org.citygml4j.xml.reader.CityGMLReader;
import util.Logger;
import util.Util;

import java.nio.file.Path;

public class FilteredReader {

    public static void main(String[] args) throws Exception {
        Logger log = Logger.start(FilteredReader.class);

        CityGMLContext context = CityGMLContext.newInstance();

        CityGMLInputFactory in = context.createCityGMLInputFactory()
                .useChunkMode(ChunkMode.CHUNK_BY_CITY_MODEL_MEMBERS);

        Path file = Util.SAMPLE_DATA_DIR.resolve("lod3_railway_v3.gml");
        log.print("Reading only bridges from the file " + file.getFileName() + " by using a filter");

        CityGMLInputFilter filter = name -> name.getLocalPart().equals("Bridge")
                && CityGMLModules.isCityGMLNamespace(name.getNamespaceURI());

        try (CityGMLReader reader = in.createFilteredCityGMLReader(in.createCityGMLReader(file), filter)) {
            while (reader.hasNext()) {
                AbstractFeature feature = reader.next();
                log.print("Found " + reader.getName().getLocalPart() + " with gml:id " + feature.getId());
            }
        }

        log.finish();
    }
}