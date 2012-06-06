package com.github.lkoskela.midget.impl.services;

import static com.github.lkoskela.midget.util.CollectionUtils.iterateAsStrings;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortalContext;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.pluto.container.PortletActionResponseContext;
import org.apache.pluto.container.PortletRenderResponseContext;
import org.apache.pluto.container.PortletRequestContext;
import org.apache.pluto.container.PortletResourceRequestContext;
import org.apache.pluto.container.PortletResourceResponseContext;
import org.apache.pluto.container.PortletWindow;
import org.apache.pluto.container.impl.PortletEnvironmentServiceImpl;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockPortletRequest;
import org.springframework.mock.web.portlet.MockPortletSession;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;

import com.github.lkoskela.midget.MidgetExecutionContext;

public class MidgetPortletEnvironmentService extends
		PortletEnvironmentServiceImpl {

	private final Map<PortletRenderResponseContext, RenderRequest> renderResponseToRequestMap;
	private final Map<PortletContext, MockPortletSession> portletSessions;
	private final PortalContext portalContext;
	private final MidgetExecutionContext executionContext;

	public MidgetPortletEnvironmentService(PortalContext ctx,
			MidgetExecutionContext executionContext) {
		this.portalContext = ctx;
		this.executionContext = executionContext;
		this.portletSessions = new HashMap<PortletContext, MockPortletSession>();
		this.renderResponseToRequestMap = new HashMap<PortletRenderResponseContext, RenderRequest>();
	}

	@Override
	public RenderRequest createRenderRequest(
			PortletRequestContext requestContext,
			PortletRenderResponseContext responseContext) {
		PortletContext pc = getPortletContextOf(requestContext);
		MockRenderRequest req = new MockRenderRequest(portalContext, pc);
		associatePortletSessionWith(req, requestContext, pc);
		copyState(requestContext, req);
		renderResponseToRequestMap.put(responseContext, req);
		return req;
	}

	@Override
	public RenderResponse createRenderResponse(
			PortletRenderResponseContext responseContext) {
		RenderRequest request = renderResponseToRequestMap.get(responseContext);
		return new MockRenderResponse(portalContext, request);
	}

	@Override
	public ActionRequest createActionRequest(
			PortletRequestContext requestContext,
			PortletActionResponseContext responseContext) {
		PortletContext pc = getPortletContextOf(requestContext);
		MockActionRequest req = new MockActionRequest(portalContext, pc);
		associatePortletSessionWith(req, requestContext, pc);
		copyState(requestContext, req);
		return req;
	}

	@Override
	public ActionResponse createActionResponse(
			final PortletActionResponseContext responseContext) {
		return new MockActionResponse(portalContext) {
			@Override
			public void sendRedirect(String location) throws IOException {
				executionContext.registerRedirectedRequest(responseContext
						.getPortletWindow().getPortletDefinition()
						.getPortletName(), location);
			}

			@Override
			public void sendRedirect(String location, String renderUrlParamName)
					throws IOException {
				executionContext.registerRedirectedRequest(responseContext
						.getPortletWindow().getPortletDefinition()
						.getPortletName(), location);
			}
		};
	}

	@Override
	public ResourceRequest createResourceRequest(
			PortletResourceRequestContext requestContext,
			PortletResourceResponseContext responseContext) {
		PortletContext pc = getPortletContextOf(requestContext);
		MockResourceRequest req = new MockResourceRequest(portalContext, pc);
		associatePortletSessionWith(req, requestContext, pc);
		copyState(requestContext, req);
		return req;
	}

	@Override
	public ResourceResponse createResourceResponse(
			PortletResourceResponseContext responseContext,
			String requestCacheLevel) {
		return new MockResourceResponse();
	}

	public PortletSession createPortletSession(PortletContext ctx,
			PortletWindow window, HttpSession session) {
		MockPortletSession portletSession = portletSessions.get(ctx);
		if (portletSession == null) {
			portletSession = new MockPortletSession(ctx);
			portletSession.setMaxInactiveInterval(-1);
			portletSession.setNew(true);
			portletSessions.put(ctx, portletSession);
		}
		return portletSession;
	}

	private PortletContext getPortletContextOf(
			PortletRequestContext requestContext) {
		PortletConfig portletConfig = requestContext.getPortletConfig();
		PortletContext portletContext = portletConfig.getPortletContext();
		return portletContext;
	}

	private void copyState(PortletRequestContext ctx, MockPortletRequest req) {
		req.setPortletMode(ctx.getPortletWindow().getPortletMode());
		req.setWindowState(ctx.getPortletWindow().getWindowState());
		HttpServletRequest servletRequest = ctx.getContainerRequest();
		copyParameters(servletRequest, req);
		copyRequestAttributes(servletRequest, req);
		copySessionAttributes(servletRequest.getSession(), req);
	}

	private void copySessionAttributes(HttpSession from, MockPortletRequest to) {
		PortletSession portletSession = to.getPortletSession();
		for (String name : iterateAsStrings(from.getAttributeNames())) {
			Object value = from.getAttribute(name);
			portletSession.setAttribute(name, value);
		}
	}

	private void copyRequestAttributes(HttpServletRequest from,
			MockPortletRequest to) {
		for (String name : iterateAsStrings(from.getAttributeNames())) {
			Object value = from.getAttribute(name);
			to.setAttribute(name, value);
		}
	}

	@SuppressWarnings("unchecked")
	private void copyParameters(HttpServletRequest from, MockPortletRequest to) {
		to.setParameters(from.getParameterMap());
	}

	private void associatePortletSessionWith(MockPortletRequest request,
			PortletRequestContext requestContext, PortletContext pc) {
		PortletWindow portletWindow = requestContext.getPortletWindow();
		HttpSession session = requestContext.getContainerRequest().getSession();
		request.setSession(createPortletSession(pc, portletWindow, session));
	}
}
