package com.github.lkoskela.midget.end2end.portlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class PortletModeIndicatingPortlet extends GenericPortlet {

	@Override
	protected void doHelp(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		printWhatPortletModeWeAreRunningIn(request, response);
	}

	@Override
	protected void doView(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		printWhatPortletModeWeAreRunningIn(request, response);
	}

	@Override
	protected void doEdit(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		printWhatPortletModeWeAreRunningIn(request, response);
	}

	private void printWhatPortletModeWeAreRunningIn(RenderRequest request,
			RenderResponse response) throws IOException {
		PrintWriter writer = new PrintWriter(response.getWriter());
		writer.print("in " + request.getPortletMode() + " mode");
	}
}
