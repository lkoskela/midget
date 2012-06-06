package com.github.lkoskela.midget.end2end;

import static java.util.Arrays.asList;
import static java.util.Collections.list;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.lkoskela.midget.DeployedPortlet;
import com.github.lkoskela.midget.MidgetContainer;
import com.github.lkoskela.midget.end2end.portlets.SimplePortlet;

public class TestTransitionBetweenActionAndRenderPhase {

	private final class StatePassingPortlet extends SimplePortlet {
		@Override
		public void processAction(ActionRequest request, ActionResponse response)
				throws PortletException, IOException {
			requests.add(request);
			response.setRenderParameter("render_parameter.single", "one");
			response.setRenderParameter("render_parameter.multiple",
					new String[] { "1", "2", "3" });
			request.setAttribute("action.request_attribute",
					"should be visible in render phase");
			request.getPortletSession().setAttribute(
					"action.session_attribute",
					"should be visible in render phase");
		}

		@Override
		public void render(RenderRequest request, RenderResponse response)
				throws PortletException, IOException {
			requests.add(request);
			captureRequestParameters(request);
			captureRequestAttributes(request);
			captureSessionAttributes(request.getPortletSession());
		}

		private void captureSessionAttributes(PortletSession session) {
			ArrayList<String> names = list(session.getAttributeNames());
			for (String name : names) {
				attributeMapInRenderPhase.put(name, session.getAttribute(name));
			}
		}

		private void captureRequestAttributes(RenderRequest request) {
			ArrayList<String> names = list(request.getAttributeNames());
			for (String name : names) {
				attributeMapInRenderPhase.put(name, request.getAttribute(name));
			}
		}

		private void captureRequestParameters(RenderRequest request) {
			parameterMapInRenderPhase.putAll(request.getParameterMap());
		}
	}

	private MidgetContainer container;
	private DeployedPortlet portlet;
	private List<PortletRequest> requests;
	private Map<String, String[]> parameterMapInRenderPhase = new HashMap<String, String[]>();
	private Map<String, Object> attributeMapInRenderPhase = new HashMap<String, Object>();

	@Before
	public void setUp() throws Exception {
		container = new MidgetContainer();
		requests = new ArrayList<PortletRequest>();
		portlet = container.deploy(new StatePassingPortlet());
	}

	@After
	public void tearDown() throws Exception {
		container.destroy();
	}

	@Test
	public void simulatingAnActionRequestTriggersTheRenderPhaseAsWell()
			throws Exception {
		portlet.action();
		assertThat(requests.get(0).getClass(),
				is(typeCompatibleWith(ActionRequest.class)));
		assertThat(requests.get(1).getClass(),
				is(typeCompatibleWith(RenderRequest.class)));
	}

	@Test
	public void testsCanAccessTheLastActionResponseAndTheLastRenderResponse()
			throws Exception {
		portlet.action();
		assertNotNull(portlet.lastActionResponse());
		assertNotNull(portlet.lastRenderResponse());
	}

	@Test
	public void renderParametersSetInActionPhaseAreVisibleInRenderPhase()
			throws Exception {
		portlet.action();
		assertThat(
				parameterMapInRenderPhase.keySet(),
				contains("render_parameter.single", "render_parameter.multiple"));
		assertEquals(
				asList("one"),
				asList(parameterMapInRenderPhase.get("render_parameter.single")));
		assertEquals(asList("1", "2", "3"),
				asList(parameterMapInRenderPhase
						.get("render_parameter.multiple")));
	}

	@Test
	public void actionPhaseRequestAttributesAreVisibleInRenderPhase()
			throws Exception {
		portlet.action();
		assertNotNull(attributeMapInRenderPhase.get("action.request_attribute"));
	}

	@Test
	public void actionPhaseSessionAttributesAreVisibleInRenderPhase()
			throws Exception {
		portlet.action();
		assertNotNull(attributeMapInRenderPhase.get("action.session_attribute"));
	}
}
