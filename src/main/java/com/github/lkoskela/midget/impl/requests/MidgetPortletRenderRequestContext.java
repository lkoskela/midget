package com.github.lkoskela.midget.impl.requests;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletRequestContext;
import org.apache.pluto.container.PortletWindow;

public class MidgetPortletRenderRequestContext extends MidgetPortletRequestContext
		implements PortletRequestContext {

	public MidgetPortletRenderRequestContext(PortletContainer container,
			HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		super(container, containerRequest, containerResponse, window);
	}
}
