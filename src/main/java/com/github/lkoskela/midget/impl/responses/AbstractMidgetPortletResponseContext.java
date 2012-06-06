package com.github.lkoskela.midget.impl.responses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.Event;
import javax.portlet.PortletMode;
import javax.portlet.WindowState;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.pluto.container.EventProvider;
import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletResponseContext;
import org.apache.pluto.container.PortletWindow;
import org.apache.pluto.container.ResourceURLProvider;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.github.lkoskela.midget.impl.events.MidgetEventProvider;
import com.github.lkoskela.midget.impl.url.MidgetResourceURLProvider;

public abstract class AbstractMidgetPortletResponseContext implements
		PortletResponseContext {

	private final PortletContainer container;
	private final PortletWindow window;
	private PortletMode portletMode;
	private WindowState windowState;
	private HttpServletRequest servletRequest;
	private HttpServletResponse servletResponse;
	private Document document;
	private String isRedirectedTo;

	public AbstractMidgetPortletResponseContext(PortletContainer container,
			HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		this.container = container;
		this.window = window;
		init(containerRequest, containerResponse);
	}

	public void init(HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		this.servletRequest = servletRequest;
		this.servletResponse = servletResponse;
		this.document = initializeDocument();
	}

	private Document initializeDocument() {
		try {
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = f.newDocumentBuilder();
			return builder.newDocument();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public PortletMode getPortletMode() {
		return portletMode;
	}

	public void setPortletMode(PortletMode portletMode) {
		this.portletMode = portletMode;
	}

	public WindowState getWindowState() {
		return windowState;
	}

	public void setWindowState(WindowState windowState) {
		this.windowState = windowState;
	}

	public Map<String, String[]> getRenderParameters() {
		return new HashMap<String, String[]>();
	}

	public Map<String, String[]> getPublicRenderParameters() {
		return new HashMap<String, String[]>();
	}

	public EventProvider getEventProvider() {
		return new MidgetEventProvider();
	}

	public List<Event> getEvents() {
		return new ArrayList<Event>();
	}

	public PortletContainer getContainer() {
		return container;
	}

	public HttpServletRequest getContainerRequest() {
		return servletRequest;
	}

	public HttpServletResponse getContainerResponse() {
		return servletResponse;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}

	public PortletWindow getPortletWindow() {
		return window;
	}

	public ResourceURLProvider getResourceURLProvider() {
		return new MidgetResourceURLProvider();
	}

	public void addProperty(Cookie cookie) {
	}

	public void addProperty(String key, Element element) {
	}

	public void addProperty(String key, String value) {
	}

	public void setProperty(String key, String value) {
	}

	public Element createElement(String tagName) throws DOMException {
		return document.createElement(tagName);
	}

	public void close() {
	}

	public void release() {
		document = null;
	}

	public void setRedirect(String location) {
		isRedirectedTo = location;
	}

	public void setRedirect(String location, String renderURLParamName) {
		isRedirectedTo = location;
	}

	public boolean isRedirect() {
		return isRedirectedTo != null;
	}

	public String getResponseURL() {
		return "http://server/response/url";
	}
}
