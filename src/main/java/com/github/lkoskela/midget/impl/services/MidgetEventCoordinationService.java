package com.github.lkoskela.midget.impl.services;

import java.util.List;

import javax.portlet.Event;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pluto.container.EventCoordinationService;
import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletWindow;

public class MidgetEventCoordinationService implements EventCoordinationService {

	public void processEvents(PortletContainer container,
			PortletWindow portletWindow, HttpServletRequest request,
			HttpServletResponse response, List<Event> events) {
	}

}
