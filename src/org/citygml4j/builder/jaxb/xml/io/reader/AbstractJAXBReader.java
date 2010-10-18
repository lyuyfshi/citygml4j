/*
 * This file is part of citygml4j.
 * Copyright (c) 2007 - 2010
 * Institute for Geodesy and Geoinformation Science
 * Technische Universitšt Berlin, Germany
 * http://www.igg.tu-berlin.de/
 *
 * The citygml4j library is free software:
 * you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see 
 * <http://www.gnu.org/licenses/>.
 */
package org.citygml4j.builder.jaxb.xml.io.reader;

import java.net.URI;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.citygml4j.builder.jaxb.unmarshal.JAXBUnmarshaller;
import org.citygml4j.builder.jaxb.xml.validation.ValidationSchemaHandler;
import org.citygml4j.xml.io.CityGMLInputFactory;
import org.citygml4j.xml.io.reader.CityGMLReadException;
import org.citygml4j.xml.io.reader.CityGMLReader;
import org.citygml4j.xml.schema.SchemaHandler;
import org.xml.sax.SAXException;

public abstract class AbstractJAXBReader implements CityGMLReader {
	final XMLStreamReader reader;

	JAXBInputFactory factory;
	SchemaHandler schemaHandler;
	JAXBUnmarshaller jaxbUnmarshaller;
	XMLUtil util;
	
	boolean useValidation;
	ValidationSchemaHandler validationSchemaHandler;
	ValidationEventHandler validationEventHandler;
	
	boolean parseSchema;
	URI baseURI;
	
	public AbstractJAXBReader(XMLStreamReader reader, JAXBInputFactory factory, URI baseURI) throws CityGMLReadException {
		this.reader = reader;
		this.factory = factory;
		this.baseURI = baseURI;
		
		parseSchema = (Boolean)factory.getProperty(CityGMLInputFactory.PARSE_SCHEMA);
		useValidation = (Boolean)factory.getProperty(CityGMLInputFactory.USE_VALIDATION);

		util = XMLUtil.getInstance();
		schemaHandler = factory.getSchemaHandler();
		
		jaxbUnmarshaller = factory.builder.createJAXBUnmarshaller(schemaHandler);

		if (useValidation) {
			validationSchemaHandler = new ValidationSchemaHandler(schemaHandler);
			validationEventHandler = factory.getValidationEventHandler();
		}
	}
	
	public void close() throws CityGMLReadException {
		try {
			factory = null;
			schemaHandler = null;
			jaxbUnmarshaller = null;
			util = null;	

			validationSchemaHandler = null;
			validationEventHandler = null;	

			if (reader != null)
				reader.close();
		} catch (XMLStreamException e) {
			throw new CityGMLReadException("Caused by: ", e);
		}
	}
	
	void parseSchema(String schemaLocation) throws SAXException {
		String[] split = schemaLocation.trim().split("\\s+");
		if (split.length % 2 == 0)	
			for (int i = 0; i < split.length; i += 2)
				schemaHandler.parseSchema(split[i], baseURI.resolve(split[i+1]).toString());
	}

	public String getBaseURI() {
		return baseURI.toString();
	}
	
}
