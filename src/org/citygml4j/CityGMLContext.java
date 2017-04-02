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
package org.citygml4j;

import java.util.ArrayList;
import java.util.List;

import org.citygml4j.builder.CityGMLBuilder;
import org.citygml4j.builder.CityGMLBuilderException;
import org.citygml4j.builder.jaxb.JAXBBuilder;
import org.citygml4j.builder.jaxb.JAXBBuilderFactory;
import org.citygml4j.model.citygml.ade.ADEException;
import org.citygml4j.model.citygml.ade.binding.ADEContext;
import org.citygml4j.model.module.Modules;
import org.citygml4j.model.module.ade.ADEModule;

public class CityGMLContext {
	private JAXBBuilder builder;
	private List<ADEContext> adeContexts;
	
	public CityGMLContext() {
		adeContexts = new ArrayList<>();
	}
	
	public void registerADEContext(ADEContext adeContext) throws ADEException {
		if (adeContext == null)
			throw new ADEException("ADE context must not be null.");
		
		if (builder != null)
			throw new ADEException("An ADE context cannot be registered after the CityGML builder has been created.");
		
		ADEModule adeModule = adeContext.getADEModule();
		if (adeModule == null)
			throw new ADEException("No ADE module defined for the ADE context.");
		
		if (adeModule.getNamespaceURI() == null || adeModule.getNamespaceURI().isEmpty())
			throw new ADEException("The namespace URI of the ADE module must not be null.");

		if (adeContext.getJAXBPackageNames() == null || adeContext.getJAXBPackageNames().isEmpty())
			throw new ADEException("No JAXP package names defined for the ADE context.");
		
		if (adeContext.getADEMarshaller() == null)
			throw new ADEException("No marshaller defined for the ADE context.");
		
		if (adeContext.getADEUnmarshaller() == null)
			throw new ADEException("No unmarshaller defined for the ADE context.");
		
		for (ADEContext tmp : adeContexts) {
			if (tmp.getADEModule().getNamespaceURI().equals(adeContext.getADEModule().getNamespaceURI()))
				throw new ADEException("An ADE context has already been registered for '" + tmp.getADEModule().getNamespaceURI() + "'.");
		}
		
		Modules.registerADEModule(adeContext.getADEModule());
		adeContexts.add(adeContext);
	}
	
	public void unregisterADEContext(ADEContext adeContext) {
		Modules.unregisterADEModule(adeContext.getADEModule());
		adeContexts.remove(adeContext);
	}
	
	public boolean isSetADEContexts() {
		return !adeContexts.isEmpty();
	}
	
	public List<ADEContext> getADEContexts() {
		return new ArrayList<>(adeContexts);
	}
	
	public ADEContext getADEContext(String namespaceURI) {
		for (ADEContext adeContext : adeContexts) {
			if (adeContext.getADEModule().getNamespaceURI().equals(namespaceURI))
				return adeContext;
		}
		
		return null;
	}
	
	public CityGMLBuilder createCityGMLBuilder() throws CityGMLBuilderException {
		return createJAXBBuilder();
	}
	
	public CityGMLBuilder createCityGMLBuilder(ClassLoader classLoader) throws CityGMLBuilderException {
		return createJAXBBuilder(classLoader);
	}
	
	public synchronized JAXBBuilder createJAXBBuilder() throws CityGMLBuilderException {
		if (builder == null)
			builder = JAXBBuilderFactory.defaults().withADEContexts(adeContexts).build();
		
		return builder;
	}
	
	public synchronized JAXBBuilder createJAXBBuilder(ClassLoader classLoader) throws CityGMLBuilderException {
		if (builder == null)
			builder = JAXBBuilderFactory.defaults().withADEContexts(adeContexts).withClassLoader(classLoader).build();
		
		return builder;
	}

}
