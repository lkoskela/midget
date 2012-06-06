package com.github.lkoskela.midget.impl.responses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletEventResponseContext;
import org.apache.pluto.container.PortletWindow;

public class MidgetPortletEventResponseContext extends AbstractMidgetPortletResponseContext
		implements PortletEventResponseContext {

	public MidgetPortletEventResponseContext(PortletContainer container,
			HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		super(container, containerRequest, containerResponse, window);
	}
}
