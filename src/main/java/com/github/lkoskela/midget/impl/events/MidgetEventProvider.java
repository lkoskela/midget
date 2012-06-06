package com.github.lkoskela.midget.impl.events;

import java.io.Serializable;

import javax.portlet.Event;
import javax.xml.namespace.QName;

import org.apache.pluto.container.EventProvider;
import org.springframework.mock.web.portlet.MockEvent;

public class MidgetEventProvider implements EventProvider {

	public Event createEvent(QName name, Serializable value)
			throws IllegalArgumentException {
		return new MockEvent(name, value);
	}
}
