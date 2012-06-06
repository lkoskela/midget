package com.github.lkoskela.midget.impl.services;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventPortlet;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceServingPortlet;

import org.apache.pluto.container.FilterManager;
import org.apache.pluto.container.PortletContainerException;
import org.apache.pluto.container.PortletInvokerService;
import org.apache.pluto.container.PortletRequestContext;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.mock.web.portlet.MockActionResponse;
import org.springframework.mock.web.portlet.MockRenderRequest;
import org.springframework.mock.web.portlet.MockRenderResponse;
import org.springframework.mock.web.portlet.MockResourceRequest;
import org.springframework.mock.web.portlet.MockResourceResponse;

import com.github.lkoskela.midget.MidgetExecutionContext;

public class MidgetPortletInvokerService implements PortletInvokerService {

	private final MidgetExecutionContext executionContext;

	public MidgetPortletInvokerService(MidgetExecutionContext portletResolver) {
		this.executionContext = portletResolver;
	}

	public void action(PortletRequestContext ctx, ActionRequest req,
			ActionResponse res, FilterManager filterManager)
			throws IOException, PortletException, PortletContainerException {
		executionContext.registerRequest(ctx, (MockActionRequest) req);
		executionContext.getPortlet(ctx).processAction(req, res);
		executionContext.registerResponse(ctx, (MockActionResponse) res);
	}

	public void render(PortletRequestContext ctx, RenderRequest req,
			RenderResponse res, FilterManager filterManager)
			throws IOException, PortletException, PortletContainerException {
		executionContext.registerRequest(ctx, (MockRenderRequest) req);
		executionContext.getPortlet(ctx).render(req, res);
		executionContext.registerResponse(ctx, (MockRenderResponse) res);
	}

	public void event(PortletRequestContext ctx, EventRequest req,
			EventResponse res, FilterManager filterManager) throws IOException,
			PortletException, PortletContainerException {
		Portlet portlet = executionContext.getPortlet(ctx);
		if (EventPortlet.class.isAssignableFrom(portlet.getClass())) {
			((EventPortlet) portlet).processEvent(req, res);
		}
	}

	public void serveResource(PortletRequestContext ctx, ResourceRequest req,
			ResourceResponse res, FilterManager filterManager)
			throws IOException, PortletException, PortletContainerException {
		Portlet portlet = executionContext.getPortlet(ctx);
		if (ResourceServingPortlet.class.isAssignableFrom(portlet.getClass())) {
			executionContext.registerRequest(ctx, (MockResourceRequest) req);
			((ResourceServingPortlet) portlet).serveResource(req, res);
			executionContext.registerResponse(ctx, (MockResourceResponse) res);
		}
	}

	public void load(PortletRequestContext ctx, PortletRequest req,
			PortletResponse res) throws IOException, PortletException,
			PortletContainerException {
		log(ctx, "'load' portlet requests are not supported at this time.");
	}

	public void admin(PortletRequestContext ctx, PortletRequest req,
			PortletResponse res) throws IOException, PortletException,
			PortletContainerException {
		log(ctx, "'admin' portlet requests are not supported at this time.");
	}

	private void log(PortletRequestContext ctx, String message) {
		ctx.getServletContext().log(message);
	}
}
