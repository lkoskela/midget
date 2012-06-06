package com.github.lkoskela.midget.impl.services;

import javax.portlet.PortletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pluto.container.PortletActionResponseContext;
import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletEventResponseContext;
import org.apache.pluto.container.PortletRenderResponseContext;
import org.apache.pluto.container.PortletRequestContext;
import org.apache.pluto.container.PortletRequestContextService;
import org.apache.pluto.container.PortletResourceRequestContext;
import org.apache.pluto.container.PortletResourceResponseContext;
import org.apache.pluto.container.PortletWindow;

import com.github.lkoskela.midget.MidgetExecutionContext;
import com.github.lkoskela.midget.impl.requests.MidgetPortletRenderRequestContext;
import com.github.lkoskela.midget.impl.requests.MidgetPortletRequestContext;
import com.github.lkoskela.midget.impl.requests.MidgetPortletResourceRequestContext;
import com.github.lkoskela.midget.impl.responses.MidgetPortletActionResponseContext;
import com.github.lkoskela.midget.impl.responses.MidgetPortletEventResponseContext;
import com.github.lkoskela.midget.impl.responses.MidgetPortletRenderResponseContext;
import com.github.lkoskela.midget.impl.responses.MidgetPortletResourceResponseContext;

public class MidgetPortletRequestContextService implements
		PortletRequestContextService {

	private final MidgetExecutionContext executionContext;

	public MidgetPortletRequestContextService(MidgetExecutionContext ctx) {
		this.executionContext = ctx;
	}

	public PortletRequestContext getPortletActionRequestContext(
			PortletContainer container, HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		return init(new MidgetPortletRequestContext(container,
				containerRequest, containerResponse, window));
	}

	public PortletRequestContext getPortletEventRequestContext(
			PortletContainer container, HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		return init(new MidgetPortletRequestContext(container,
				containerRequest, containerResponse, window));
	}

	public PortletResourceRequestContext getPortletResourceRequestContext(
			PortletContainer container, HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		return init(new MidgetPortletResourceRequestContext(container,
				containerRequest, containerResponse, window));
	}

	public PortletRequestContext getPortletRenderRequestContext(
			PortletContainer container, HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		return init(new MidgetPortletRenderRequestContext(container,
				containerRequest, containerResponse, window));
	}

	public PortletActionResponseContext getPortletActionResponseContext(
			PortletContainer container, HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		return new MidgetPortletActionResponseContext(container,
				containerRequest, containerResponse, window);
	}

	public PortletEventResponseContext getPortletEventResponseContext(
			PortletContainer container, HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		return new MidgetPortletEventResponseContext(container,
				containerRequest, containerResponse, window);
	}

	public PortletResourceResponseContext getPortletResourceResponseContext(
			PortletContainer container, HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		return new MidgetPortletResourceResponseContext(container,
				containerRequest, containerResponse, window);
	}

	public PortletRenderResponseContext getPortletRenderResponseContext(
			PortletContainer container, HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		return new MidgetPortletRenderResponseContext(container,
				containerRequest, containerResponse, window);
	}

	private PortletRequestContext init(MidgetPortletRequestContext ctx) {
		PortletConfig portletConfig = executionContext.resolveConfig(ctx);
		ServletContext servletContext = executionContext.getServletContext(ctx);
		HttpServletRequest request = ctx.getContainerRequest();
		HttpServletResponse response = ctx.getContainerResponse();
		ctx.init(portletConfig, servletContext, request, response);
		return ctx;
	}

	private PortletResourceRequestContext init(
			MidgetPortletResourceRequestContext ctx) {
		init((MidgetPortletRequestContext) ctx);
		return ctx;
	}
}
