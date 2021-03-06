Change Log
==========

### 2.11.0 - 2020-07-14

##### Additions
* The encoding of a CityJSON input file can now be set when creating a CityJSON reader. 

##### Fixes
* Fixed memory leak in CityJSON readers and writers. [citygml-tools #14](https://github.com/citygml4j/citygml-tools/issues/14)
* Fixed `ADEBoundingBoxHelper` to also consider spatial properties injected into a CityGML feature type by an ADE. 

##### Breaking changes
* To fix the memory leak in CityJSON readers and writers, CityJSON helper objects such as `VerticesBuilder` must now
be registered directly with the reader or writer they shall be used with but not with the `CityJSONInputFactory`
or `CityJSONOutputFactory` anymore.
  * Please check the sample programs in the folders `citygml2cityjson` and `cityjson2citygml` for examples.

### 2.10.5 - 2020-02-14

##### Additions
* Added support for `gml:PolygonPatch`. [#24](https://github.com/citygml4j/citygml4j/issues/24)
* `ObjectCopier` can now be used to only copy the properties of a common super class from the source to the target object.

##### Fixes
* Fixed `toList3d()` for curve geometries. If a curve geometry consists of multiple curve members or segments, then interior
start and end points of these segments are now removed from the result of the `toList3d()` method.
* Fixed `SchemaHandler` to correctly consider already parsed schemas.
* Fixed `LodRepresentationBuilder` to include inherited spatial properties when building the LoD representation for a given object.
* Fixed bounding box calculation for `gml:RectifiedGrid`.

### 2.10.4 - 2019-11-01

##### Fixes
* Hotfix: Updated to citygson 1.1.2 to avoid unmarshalling of empty `"attributes"` properties in CityJSON. Only a small
fix and not really an error wrt. the CityJSON schema, but still I thought it's worth publishing a new release shortly
after 2.10.3.

### 2.10.3 - 2019-10-30

##### Additions
* Updated to citygson version 1.1.1.
* Added option to remove duplicate child geometries when mapping to CityJSON. [citygml-tools #7](https://github.com/citygml4j/citygml-tools/issues/7)
* Data types of generic attributes can be added as additional metadata property for CityJSON files.

##### Fixes
* Fixed possible endless loop in CityJSON semantics builder. [citygml-tools #8](https://github.com/citygml4j/citygml-tools/issues/8)
* Reworked creation of geometries for CityJSON city objects with nested boundary surfaces. [citygml-tools #7](https://github.com/citygml4j/citygml-tools/issues/7)
* Added missing attributes to CityJSON city objects and semantic surfaces.
* Fixed bug in `DeepCopyBuilder` when copying maps.

### 2.10.2 - 2019-08-11

##### Fixes
* Fixed `IndexOutOfBoundsException` if `gml:Envelope` is declared but empty. [citygml-tools #5](https://github.com/citygml4j/citygml-tools/issues/5)
* Fixed converting `gen:measureAttribute` to CityJSON. [#22](https://github.com/citygml4j/citygml4j/issues/22)
* Added missing `Serializable` interface to model classes and interfaces.

### 2.10.1 - 2019-07-08

##### Additions
* Updated to citygson version 1.1.0.
  * This version reduces the memory footprint when reading CityJSON files.
* CityJSON content is released from memory while mapping to citygml4j objects.
* Added CityGML name filter to CityJSON reader.

##### Fixes
* Fixed concurrency issues in `SchemaHandler`.
* Fixed unclosed texture coordinate rings in CityJSON to CityGML converter.

### 2.10.0 - 2019-04-29

##### Additions
* Added support for CityJSON 1.0 by updating to [citygson 1.0](https://github.com/citygml4j/citygson). Note that previous versions of CityJSON are no longer supported.

##### Breaking changes
* Changed `CityJSONExtensionModule` interface to account for the version number of CityJSON Extensions as introduced in CityJSON 1.0 (see [change log](https://github.com/tudelft3d/cityjson/blob/master/changelog.md#100---2019-04-26)).

### 2.9.2 - 2019-04-18

##### Additions
* Added the generic `unsetProperty(ModelObject object, Object value)` utility method to the class `ModelObjects`. This utility method is useful in cases where you have an instance of a citygml4j model class and an instance of one of its properties, but you don't know
which `unsetter` method to invoke on the model object to remove the property.
* Added the `unsetLod(int lod)` method to `AbstractCityObject`, which lets you easily remove all spatial properties from a city object that are associated with a given LoD level.
* The `LodRepresentation` class now also contains spatial properties of city objects that are independent of a specific LoD level. It therefore provides you easy access to all spatial properties of a city object.
  * For features that are not derived from `AbstractCityObject` but only from `AbstractFeature`, the class `SpatialRepresentation` has been introduced, which serves the same purpose. 
* Added a flag to CityGML readers to skip unknown ADE content rather than mapping it to `ADEGenericElement` objects.
* Moved the CityJSON 0.9 Gson binding from the citygml4j source code to its own `citygson` JAR library, which is now managed and maintained in a separate [GitHub repository](https://github.com/citygml4j/citygson). The Gson binding is useful in its own right to parse and write CityJSON data independent of the citygml4j library. So, simply add the new `citygson` lib to your Java project if you just want to consume CityJSON data.

##### Fixes
* Fixed bug in `SAXWriter` that led to missing namespace declarations in rare situations.

### 2.9.1 - 2019-02-13

##### Additions
* The encoding to be used for CityJSON files can now be defined when creating a CityJSON writer through a `CityJSONOutputFactory`.

### 2.9.0 - 2019-02-06

##### Breaking changes
* Changed `ADEContext` API.
  * `getJAXBPackageNames()` has been moved to `ADEModule` to be able to associate JAXB classes with CityGML versions.
  * `getADEMarshaller()` and `getADEUnmarshaller()` have been renamed to `createADEMarshaller()` and `createADEUnmarshaller()`
* Added `Module` information to `AbstractFeature` which is set automatically during unmarshalling.
  * This allows for easily querying the CityGML version of every feature (both CityGML and ADE) in a dataset.
  * Breaking: `getCityGMLModule()` has been removed from `CityGMLModuleComponent` for this reason.

##### Additions
* Added support for CityJSON version 0.9.
  * This required reworking the Gson binding classes for CityJSON in `org.citygml4j.binding.cityjson` and a lof of changes
   to the CityJSON marshalling and unmarshalling process in `org.citygml4j.builder.cityjson`.
  * Previous versions of CityJSON are no longer supported.
* Added support for CityJSON extensions.
  * An `ADEContext` can now additionally implement the `CityJSONExtension` interface to facilitate a full mapping 
  between CityGML ADE content and CityJSON extensions.
  * The [NoiseADE citygml4j module](https://github.com/citygml4j/noise-ade-citygml4j) version 2.4 is a first implementation
  of the `CityJSONExtension` interface.
  * Unknown CityJSON extensions can be mapped to CityGML generic city objects and attributes if no `CityJSONExtension`
  is available.
  * Added examples in `cityjson/handling_extensions` to demonstrate the new capabilities.
* Added support for LoD0 curves for CityJSON transportation objects.
* Improved mapping of CityJSON attributes to and from generic attributes.

##### Fixes
* Removed temporary information from citygml4j objects after CityJSON marshalling.
* Fixed unmarshalling ADE properties of `AbstractTextureParameterization`.
* Fixed CityJSON `"date-time"` adapter.
* Avoid invalid `gml:id` prefixes in `DefaultGMLIdManager`.
* Fixed Gson exception when writing empty JSON documents.

##### Miscellaneous
* Switched to JAXB 2.3.2.
* Removed `SMIL` from generated JAXB sources (never have seen a GML dataset using it...).
* Replaced `com.sun.xsom:xsom` library with `org.glassfish.jaxb:xsom`.

### 2.8.1 - 2018-11-13

##### Miscellaneous
* Switched to JAXB 2.3.1 to avoid the following warning when running on Java 11: `WARNING: Illegal reflective access by com.sun.xml.bind.v2.runtime.reflect.opt.Injector`.

##### Fixes
* Fixed missing namespace declarations in `SAXWriter`.

### 2.8.0 - 2018-10-28

##### Breaking changes
* Removed method `getADEModule()` from the `ADEModelObject` interface.
  * This method caused every `ADEModelObject` to be associated with one `ADEModule` and consequently with only one CityGML version. 
  * With this change, an `ADEModelObject` can now be used for both CityGML 2.0 and 1.0.

##### Additions
* Added `java.io.Serializable` interface to model classes. This allows for serializing and deserializing the state of citygml4j object trees without the need for writing and reading XML datasets. [#18](https://github.com/citygml4j/citygml4j/issues/18)
  * See the sample program `serializing_model_classes/Serialization.java` for an example.

##### Fixes
* Fixed NPE in CityJSON appearance resolver.
* Fixed LoD bug in CityJSON BuildingUnmarshaller.
