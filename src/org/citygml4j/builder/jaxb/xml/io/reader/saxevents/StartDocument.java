package org.citygml4j.builder.jaxb.xml.io.reader.saxevents;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.LocatorImpl;

public final class StartDocument extends SAXEvent {

	public StartDocument() {
		super(EventType.START_DOCUMENT);
	}

	@Override
	public StartDocument shallowCopy() {
		return new StartDocument();
	}

	@Override
	public void send(ContentHandler contentHandler) throws SAXException {
		contentHandler.startDocument();
	}
	
	@Override
	public void send(ContentHandler contentHandler, LocatorImpl locator) throws SAXException {
		send(contentHandler);
	}

}