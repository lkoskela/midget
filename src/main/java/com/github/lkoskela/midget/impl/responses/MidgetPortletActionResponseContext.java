package com.github.lkoskela.midget.impl.responses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pluto.container.PortletActionResponseContext;
import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletWindow;

public class MidgetPortletActionResponseContext extends
		AbstractMidgetPortletResponseContext implements
		PortletActionResponseContext {

	public MidgetPortletActionResponseContext(PortletContainer container,
			HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		super(container, containerRequest, containerResponse, window);
	}
}
