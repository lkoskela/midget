package com.github.lkoskela.midget;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.servlet.ServletContext;

import org.apache.pluto.container.PortletRequestContext;
import org.apache.pluto.container.PortletWindow;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;

public class MidgetExecutionContext {
	private Map<String, DeployedPortlet> portlets;
	private Map<String, PortletConfig> configs;
	private Map<String, ServletContext> servletContexts;
	private Map<String, MockRenderRequest> renderRequests;
	private Map<String, MockRenderResponse> renderResponses;
	private Map<String, MockActionRequest> actionRequests;
	private Map<String, MockActionResponse> actionResponses;
	private Map<String, MockResourceRequest> resourceRequests;
	private Map<String, MockResourceResponse> resourceResponses;
	private Map<String, String> portletNamesByWindowID;
	private Map<String, Map<String, String>> stubbedResources;
	private Map<String, String> forwardedRequests;
	private Map<String, String> redirectedRequests;

	public MidgetExecutionContext() {
		this.portlets = new HashMap<String, DeployedPortlet>();
		this.configs = new HashMap<String, PortletConfig>();
		this.portletNamesByWindowID = new HashMap<String, String>();
		this.servletContexts = new HashMap<String, ServletContext>();
		this.forwardedRequests = new HashMap<String, String>();
		this.redirectedRequests = new HashMap<String, String>();
		this.stubbedResources = new HashMap<String, Map<String, String>>();
		this.renderRequests = new HashMap<String, MockRenderRequest>();
		this.renderResponses = new HashMap<String, MockRenderResponse>();
		this.actionRequests = new HashMap<String, MockActionRequest>();
		this.actionResponses = new HashMap<String, MockActionResponse>();
		this.resourceRequests = new HashMap<String, MockResourceRequest>();
		this.resourceResponses = new HashMap<String, MockResourceResponse>();
	}

	public void add(PortletConfig config, ServletContext ctx,
			DeployedPortlet portlet) {
		String portletName = config.getPortletName();
		portlets.put(portletName, portlet);
		configs.put(portletName, config);
		servletContexts.put(portletName, ctx);
	}

	public Portlet getPortlet(PortletRequestContext ctx) {
		String portletName = getPortletName(ctx);
		DeployedPortlet deployedPortlet = portlets.get(portletName);
		if (deployedPortlet == null) {
			raisePortletNotDeployedError(portletName);
		}
		return deployedPortlet.getPortlet();
	}

	public PortletConfig resolveConfig(PortletRequestContext ctx) {
		String portletName = getPortletName(ctx);
		PortletConfig config = configs.get(portletName);
		if (config == null) {
			raisePortletNotDeployedError(portletName);
		}
		return config;
	}

	public ServletContext getServletContext(PortletRequestContext ctx) {
		String portletName = getPortletName(ctx);
		ServletContext sc = servletContexts.get(portletName);
		if (sc == null) {
			raisePortletNotDeployedError(portletName);
		}
		return sc;
	}

	public MockRenderResponse getRenderResponse(String portletName) {
		return renderResponses.get(portletName);
	}

	public MockActionResponse getActionResponse(String portletName) {
		return actionResponses.get(portletName);
	}

	public MockActionRequest getActionRequest(String portletName) {
		return actionRequests.get(portletName);
	}

	public MockResourceResponse getResourceResponse(String portletName) {
		return resourceResponses.get(portletName);
	}

	public void registerRequest(PortletRequestContext ctx, MockRenderRequest req) {
		String portletName = getPortletName(ctx);
		renderRequests.put(portletName, req);
		portletNamesByWindowID.put(req.getWindowID(), portletName);
	}

	public void registerResponse(PortletRequestContext ctx,
			MockRenderResponse res) {
		String portletName = getPortletName(ctx);
		renderResponses.put(portletName, res);
	}

	public void registerRequest(PortletRequestContext ctx, MockActionRequest req) {
		String portletName = getPortletName(ctx);
		actionRequests.put(portletName, req);
		portletNamesByWindowID.put(req.getWindowID(), portletName);
	}

	public void registerResponse(PortletRequestContext ctx,
			MockActionResponse res) {
		String portletName = getPortletName(ctx);
		actionResponses.put(portletName, res);
	}

	public void registerRequest(PortletRequestContext ctx,
			MockResourceRequest req) {
		String portletName = getPortletName(ctx);
		resourceRequests.put(portletName, req);
		portletNamesByWindowID.put(req.getWindowID(), portletName);
	}

	public void registerResponse(PortletRequestContext ctx,
			MockResourceResponse res) {
		String portletName = getPortletName(ctx);
		resourceResponses.put(portletName, res);
	}

	public void stubResource(String portletName, String path,
			String stubbedResponseContent) {
		if (!stubbedResources.containsKey(portletName)) {
			stubbedResources.put(portletName, new HashMap<String, String>());
		}
		Map<String, String> map = stubbedResources.get(portletName);
		map.put(path, stubbedResponseContent);
		stubbedResources.put(portletName, map);
	}

	public String getStubbedResponse(PortletRequest request, String path) {
		Map<String, String> map = stubbedResources.get(getPortletName(request));
		if (map == null || !map.containsKey(path)) {
			raiseResourceNotStubbedError(path);
		}
		return map.get(path);
	}

	public String getRedirectedPath(String portletName) {
		return redirectedRequests.get(portletName);
	}

	public void registerRedirectedRequest(String portletName, String url) {
		redirectedRequests.put(portletName, url);
	}

	public String getForwardedPath(String portletName) {
		return forwardedRequests.get(portletName);
	}

	public void registerForwardedRequest(PortletRequest request,
			String dispatchedPath) {
		forwardedRequests.put(getPortletName(request), dispatchedPath);
	}

	private String getPortletName(PortletRequestContext ctx) {
		PortletWindow window = ctx.getPortletWindow();
		return window.getPortletDefinition().getPortletName();
	}

	private String getPortletName(PortletRequest request) {
		return portletNamesByWindowID.get(request.getWindowID());
	}

	private void raisePortletNotDeployedError(String portletName) {
		String message = "Portlet '" + portletName + "' not deployed?";
		throw new MidgetException(message);
	}

	private void raiseResourceNotStubbedError(String path) {
		String message = "Missing stubbed response for resource: " + path;
		throw new MidgetException(message);
	}
}