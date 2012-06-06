package com.github.lkoskela.midget.impl.responses;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletResourceResponseContext;
import org.apache.pluto.container.PortletWindow;

public class MidgetPortletResourceResponseContext extends
		AbstractMidgetPortletMimeResponseContext implements
		PortletResourceResponseContext {

	public MidgetPortletResourceResponseContext(PortletContainer container,
			HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		super(container, containerRequest, containerResponse, window);
	}

	public void setLocale(Locale locale) {
		getServletResponse().setLocale(locale);
	}

	public void setCharacterEncoding(String charset) {
		getServletResponse().setCharacterEncoding(charset);
	}

	public void setContentLength(int lengthInBytes) {
		getServletResponse().setContentLength(lengthInBytes);
	}
}
