package com.github.lkoskela.midget.impl;

import java.io.IOException;

import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.github.lkoskela.midget.MidgetExecutionContext;

public class MidgetPortletRequestDispatcher implements PortletRequestDispatcher {

	private final MidgetExecutionContext executionContext;
	private final String dispatchedPath;

	public MidgetPortletRequestDispatcher(
			MidgetExecutionContext executionContext, String path) {
		this.executionContext = executionContext;
		this.dispatchedPath = path;
	}

	public void include(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		includeStubbedContent(request, response);
	}

	public void include(PortletRequest request, PortletResponse response)
			throws PortletException, IOException {
		includeStubbedContent(request, (MimeResponse) response);
	}

	public void forward(PortletRequest request, PortletResponse response)
			throws PortletException, IOException {
		executionContext.registerForwardedRequest(request, dispatchedPath);
	}

	private void includeStubbedContent(PortletRequest request,
			MimeResponse response) throws IOException {
		String content = executionContext.getStubbedResponse(request,
				dispatchedPath);
		if (content != null) {
			response.getWriter().write(content);
		}
	}
}
