package com.github.lkoskela.midget.end2end.portlets;

import static com.github.lkoskela.midget.util.CollectionUtils.iterate;
import static com.github.lkoskela.midget.util.CollectionUtils.joinWith;
import static java.lang.String.valueOf;
import static javax.portlet.PortletSession.APPLICATION_SCOPE;
import static javax.portlet.PortletSession.PORTLET_SCOPE;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class EchoPortlet extends GenericPortlet {

	@Override
	protected void doDispatch(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		PrintWriter writer = response.getWriter();
		writeParameters(writer, request);
		writeRequestAttributes(writer, request);
		writeSessionAttributes(writer, request);
	}

	private void writeSessionAttributes(PrintWriter out, RenderRequest req) {
		PortletSession session = req.getPortletSession();
		for (String name : iterate(session.getAttributeNames(APPLICATION_SCOPE))) {
			Object value = session.getAttribute(name, APPLICATION_SCOPE);
			writeAttribute(out, name, value, " (application scope)");
		}
		for (String name : iterate(session.getAttributeNames(PORTLET_SCOPE))) {
			Object value = session.getAttribute(name, PORTLET_SCOPE);
			writeAttribute(out, name, value, " (portlet scope)");
		}
	}

	private void writeParameters(PrintWriter out, RenderRequest req) {
		for (String name : iterate(req.getParameterNames())) {
			String[] values = req.getParameterValues(name);
			writeParameter(out, name, values);
		}
	}

	private void writeParameter(PrintWriter out, String name, String[] values) {
		out.print(name);
		out.print("=[");
		out.print(joinWith(",", values));
		out.println("]");
	}

	private void writeRequestAttributes(PrintWriter out, RenderRequest req) {
		for (String name : iterate(req.getAttributeNames())) {
			Object value = req.getAttribute(name);
			writeAttribute(out, name, value);
		}
	}

	private void writeAttribute(PrintWriter out, String name, Object value) {
		writeAttribute(out, name, value, "");
	}

	private void writeAttribute(PrintWriter out, String name, Object value,
			String suffix) {
		out.print(name);
		out.print("=");
		out.print(valueOf(value));
		out.println(suffix);
	}
}
