package com.github.lkoskela.midget.impl.requests;

import static com.github.lkoskela.midget.util.ID.idWithPrefix;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletResourceRequestContext;
import org.apache.pluto.container.PortletWindow;

public class MidgetPortletResourceRequestContext extends
		MidgetPortletRequestContext implements PortletResourceRequestContext {

	private final String resourceId = idWithPrefix("Resource-");

	public MidgetPortletResourceRequestContext(PortletContainer container,
			HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		super(container, containerRequest, containerResponse, window);
	}

	public String getResourceID() {
		return resourceId;
	}

	public String getCacheability() {
		return ResourceURL.PORTLET;
	}

	public Map<String, String[]> getPrivateRenderParameterMap() {
		return new HashMap<String, String[]>();
	}
}
