package com.github.lkoskela.midget.impl.requests;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletRequestContext;
import org.apache.pluto.container.PortletWindow;

public class MidgetPortletRequestContext implements PortletRequestContext {
	private final PortletContainer container;
	private PortletConfig portletConfig;
	private ServletContext servletContext;
	private HttpServletRequest servletRequest;
	private HttpServletResponse servletResponse;
	private HttpServletRequest containerRequest;
	private HttpServletResponse containerResponse;
	private final PortletWindow window;

	public MidgetPortletRequestContext(PortletContainer container,
			HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		this.container = container;
		this.window = window;
		setRequestAndResponse(containerRequest, containerResponse);
	}

	public void init(PortletConfig config, ServletContext ctx,
			HttpServletRequest request, HttpServletResponse response) {
		this.portletConfig = config;
		this.servletContext = ctx;
		setRequestAndResponse(request, response);
	}

	private void setRequestAndResponse(HttpServletRequest request,
			HttpServletResponse response) {
		this.servletRequest = request;
		this.servletResponse = response;
		this.containerRequest = request;
		this.containerResponse = response;
	}

	public PortletContainer getContainer() {
		return container;
	}

	public PortletConfig getPortletConfig() {
		return portletConfig;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public HttpServletRequest getContainerRequest() {
		return containerRequest;
	}

	public HttpServletResponse getContainerResponse() {
		return containerResponse;
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

	@SuppressWarnings("unchecked")
	public Enumeration<String> getAttributeNames() {
		return servletRequest.getAttributeNames();
	}

	public Object getAttribute(String name) {
		return servletRequest.getAttribute(name);
	}

	public void setAttribute(String name, Object value) {
		servletRequest.setAttribute(name, value);
	}

	public Locale getPreferredLocale() {
		return Locale.getDefault();
	}

	public Cookie[] getCookies() {
		return servletRequest.getCookies();
	}

	public Map<String, String[]> getProperties() {
		return new HashMap<String, String[]>();
	}

	@SuppressWarnings("unchecked")
	public Map<String, String[]> getPrivateParameterMap() {
		return servletRequest.getParameterMap();
	}

	public Map<String, String[]> getPublicParameterMap() {
		return new HashMap<String, String[]>();
	}
}
