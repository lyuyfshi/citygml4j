/*
 * citygml4j - The Open Source Java API for CityGML
 * https://github.com/citygml4j
 * 
 * Copyright 2013-2017 Claus Nagel <claus.nagel@gmail.com>
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
package org.citygml4j.builder.jaxb.unmarshal.citygml.ade;

import java.util.HashMap;

import javax.xml.bind.JAXBElement;

import org.citygml4j.builder.jaxb.unmarshal.JAXBUnmarshaller;
import org.citygml4j.model.citygml.ade.binding.ADEContext;
import org.citygml4j.model.citygml.ade.binding.ADEModelObject;
import org.citygml4j.model.citygml.ade.generic.ADEGenericElement;
import org.citygml4j.xml.io.reader.MissingADESchemaException;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ADEUnmarshaller {
	private final JAXBUnmarshaller jaxb;
	private HashMap<String, ADEContext> adeContexts;
	private ADEUnmarshallerHelper helper;

	public ADEUnmarshaller(JAXBUnmarshaller jaxb, HashMap<String, ADEContext> adeContexts) {
		this.jaxb = jaxb;

		if (adeContexts != null && !adeContexts.isEmpty()) {
			this.adeContexts = adeContexts;
			helper = new ADEUnmarshallerHelper(jaxb);
		}
	}

	public ADEGenericElement unmarshal(Element element) throws MissingADESchemaException {
		if (jaxb.isParseSchema()) {
			try {
				jaxb.getSchemaHandler().parseSchema(element);
				jaxb.getSchemaHandler().parseSchema(element.getNamespaceURI(), null);
			} catch (SAXException e) {
				// 
			}
		}

		try {
			jaxb.getSchemaHandler().resolveAndParseSchema(element.getNamespaceURI());
		} catch (SAXException e) {
			// 
		} catch (MissingADESchemaException e) {
			if (jaxb.isThrowMissingADESchema())
				throw e;
		}

		return new ADEGenericElement(element);
	}

	public ADEModelObject unmarshal(JAXBElement<?> src) throws MissingADESchemaException {
		if (adeContexts != null) {
			ADEContext adeContext = adeContexts.get(src.getName().getNamespaceURI());
			if (adeContext != null)
				return adeContext.getADEUnmarshaller().unmarshal(src, helper);
		}

		return null;
	}

	public ADEModelObject unmarshal(Object src) throws MissingADESchemaException {
		if (adeContexts != null) {
			for (ADEContext adeContext : adeContexts.values()) {
				ADEModelObject ade = adeContext.getADEUnmarshaller().unmarshal(src, helper);
				if (ade != null)
					return ade;
			}
		}

		return null;
	}

}
