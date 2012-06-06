package com.github.lkoskela.midget.end2end;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.lkoskela.midget.DeployedPortlet;
import com.github.lkoskela.midget.MidgetContainer;
import com.github.lkoskela.midget.MidgetException;
import com.github.lkoskela.midget.end2end.portlets.SimplePortlet;

public class TestRequestDispatching {

	private MidgetContainer container;
	private DeployedPortlet forwardingPortlet;
	private DeployedPortlet noOpPortlet;
	private DeployedPortlet includingPortlet;

	@Before
	public void setUp() throws Exception {
		container = new MidgetContainer();
		noOpPortlet = container.deploy(new SimplePortlet());
		forwardingPortlet = container.deploy(new SimplePortlet() {
			@Override
			public void processAction(ActionRequest request,
					ActionResponse response) throws PortletException,
					IOException {
				response.sendRedirect("/redirected/action");
			}

			@Override
			public void render(RenderRequest request, RenderResponse response)
					throws PortletException, IOException {
				forwardTo("/forwarded/render", request, response);
			}

			private void forwardTo(String path, PortletRequest request,
					PortletResponse response) throws PortletException,
					IOException {
				PortletContext pc = getPortletContext();
				PortletRequestDispatcher rd = pc.getRequestDispatcher(path);
				rd.forward(request, response);
			}
		});
		includingPortlet = container.deploy(new GenericPortlet() {
			@Override
			protected void doView(RenderRequest request, RenderResponse response)
					throws PortletException, IOException {
				PrintWriter out = response.getWriter();
				out.write("before-");
				PortletRequestDispatcher rd = getPortletContext()
						.getRequestDispatcher("/included/resource");
				rd.include(request, response);
				out.write("-after");
				out.close();
			}
		});
	}

	@After
	public void tearDown() throws Exception {
		container.destroy();
	}

	@Test
	public void forwardedPathIsAvailableWhenRequestHasBeenForwarded()
			throws Exception {
		noOpPortlet.render();
		assertNull(noOpPortlet.lastForwardedPath());
		forwardingPortlet.render();
		assertNotNull(forwardingPortlet.lastForwardedPath());

	}

	@Test
	public void renderRequestCanBeForwarded() throws Exception {
		forwardingPortlet.render();
		assertThat(forwardingPortlet.lastForwardedPath(),
				is("/forwarded/render"));
	}

	@Test
	public void actionRequestCanRedirect() throws Exception {
		forwardingPortlet.action();
		assertThat(forwardingPortlet.lastRedirectedPath(),
				is("/redirected/action"));
	}

	@Test
	public void requestCanIncludeOtherResources() throws Exception {
		includingPortlet.stubResource("/included/resource", "included");
		includingPortlet.render();
		assertThat(includingPortlet.renderedContentAsString(),
				containsString("before-included-after"));
	}

	@Test
	public void includedResourcesMustBeExplicitlyStubbed() throws Exception {
		try {
			includingPortlet.render();
			fail("Portlet including a resource that wasn't stubbed should raise an error.");
		} catch (MidgetException expected) {
		}
	}
}
