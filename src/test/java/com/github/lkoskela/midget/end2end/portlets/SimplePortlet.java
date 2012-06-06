package com.github.lkoskela.midget.end2end.portlets;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * Simple Portlet implementation to be extended in test code, selectively
 * overriding methods as appropriate.
 */
public class SimplePortlet implements Portlet {
	protected PortletConfig config;

	public void init(PortletConfig config) throws PortletException {
		this.config = config;
	}

	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException, IOException {
	}

	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
	}

	public void destroy() {
	}

	protected PortletContext getPortletContext() {
		return config.getPortletContext();
	}
}