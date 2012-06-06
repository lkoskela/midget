package com.github.lkoskela.midget.impl.responses;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.portlet.CacheControl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pluto.container.PortletContainer;
import org.apache.pluto.container.PortletMimeResponseContext;
import org.apache.pluto.container.PortletURLProvider;
import org.apache.pluto.container.PortletURLProvider.TYPE;
import org.apache.pluto.container.PortletWindow;
import org.springframework.mock.web.portlet.MockCacheControl;

import com.github.lkoskela.midget.impl.url.MidgetPortletURLProvider;

public abstract class AbstractMidgetPortletMimeResponseContext extends
		AbstractMidgetPortletResponseContext implements
		PortletMimeResponseContext {

	public AbstractMidgetPortletMimeResponseContext(PortletContainer container,
			HttpServletRequest containerRequest,
			HttpServletResponse containerResponse, PortletWindow window) {
		super(container, containerRequest, containerResponse, window);
	}

	public CacheControl getCacheControl() {
		return new MockCacheControl();
	}

	public PortletURLProvider getPortletURLProvider(TYPE type) {
		return new MidgetPortletURLProvider(type);
	}

	public Locale getLocale() {
		return getServletResponse().getLocale();
	}

	public String getContentType() {
		return getServletResponse().getContentType();
	}

	public void setContentType(String contentType) {
		getServletResponse().setContentType(contentType);
	}

	public String getCharacterEncoding() {
		return getServletResponse().getCharacterEncoding();
	}

	public OutputStream getOutputStream() throws IOException,
			IllegalStateException {
		return getServletResponse().getOutputStream();
	}

	public PrintWriter getWriter() throws IOException, IllegalStateException {
		return getServletResponse().getWriter();
	}

	public int getBufferSize() {
		return getServletResponse().getBufferSize();
	}

	public void setBufferSize(int size) {
		getServletResponse().setBufferSize(size);
	}

	public void reset() {
		getServletResponse().reset();
	}

	public void resetBuffer() {
		getServletResponse().resetBuffer();
	}

	public void flushBuffer() throws IOException {
		getServletResponse().flushBuffer();
	}

	public boolean isCommitted() {
		return getServletResponse().isCommitted();
	}
}
