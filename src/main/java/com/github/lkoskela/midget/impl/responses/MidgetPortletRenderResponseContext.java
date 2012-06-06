package com.github.lkoskela.midget.impl.responses;

import java.util.Collection;

import javax.portlet.PortletMode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletRenderResponseContext;
import org.apache.pluto.container.PortletWindow;

public class MidgetPortletRenderResponseContext extends
		AbstractMidgetPortletMimeResponseContext implements
		PortletRenderResponseContext {

	public MidgetPortletRenderResponseContext(PortletContainer container,
			HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		super(container, containerRequest, containerResponse, window);
	}

	public void setTitle(String title) {
	}

	public void setNextPossiblePortletModes(Collection<PortletMode> portletModes) {
	}
}
