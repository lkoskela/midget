package com.github.lkoskela.midget.end2end;

import static javax.portlet.PortletSession.APPLICATION_SCOPE;
import static javax.portlet.PortletSession.PORTLET_SCOPE;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.lkoskela.midget.DeployedPortlet;
import com.github.lkoskela.midget.MidgetContainer;
import com.github.lkoskela.midget.end2end.portlets.EchoPortlet;

public class TestAttributesAndParameters {

	private MidgetContainer container;
	public DeployedPortlet portlet;

	@Before
	public void setUp() throws Exception {
		container = new MidgetContainer();
		portlet = container.deploy(new EchoPortlet());
	}

	@After
	public void tearDown() throws Exception {
		container.destroy();
	}

	@Test
	public void portletSeesRequestParameters() throws Exception {
		portlet.addRequestParameter("p1", "p1value1");
		portlet.addRequestParameter("p1", "p1value2");
		portlet.addRequestParameter("p2", "p2value");
		portlet.render();
		String output = portlet.renderedContentAsString();
		assertThat(output, containsString("p1=[p1value1,p1value2]"));
		assertThat(output, containsString("p2=[p2value]"));
	}

	@Test
	public void portletSeesRequestAttributes() throws Exception {
		portlet.setRequestAttribute("a", "1");
		portlet.setRequestAttribute("b", "2");
		portlet.render();
		String output = portlet.renderedContentAsString();
		assertThat(output, containsString("a=1"));
		assertThat(output, containsString("b=2"));
	}

	@Test
	public void portletSeesSessionAttributes() throws Exception {
		portlet.setSessionAttribute("s1", "one");
		portlet.setSessionAttribute("s2", 2);
		portlet.setSessionAttribute("portletScope", "ps", PORTLET_SCOPE);
		portlet.setSessionAttribute("applicationScope", "as", APPLICATION_SCOPE);
		portlet.render();
		String output = portlet.renderedContentAsString();
		assertThat(output, containsString("s1=one"));
		assertThat(output, containsString("s2=2"));
		assertThat(output, containsString("portletScope=ps (portlet scope)"));
		assertThat(output,
				containsString("applicationScope=as (application scope)"));
	}
}
