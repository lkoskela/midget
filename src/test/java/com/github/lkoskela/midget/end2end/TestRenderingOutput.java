package com.github.lkoskela.midget.end2end;

import static javax.portlet.PortletMode.EDIT;
import static javax.portlet.PortletMode.HELP;
import static javax.portlet.PortletMode.VIEW;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.lkoskela.midget.DeployedPortlet;
import com.github.lkoskela.midget.MidgetContainer;
import com.github.lkoskela.midget.end2end.portlets.PortletModeIndicatingPortlet;

public class TestRenderingOutput {

	private MidgetContainer container;
	private DeployedPortlet portlet;

	@Before
	public void setUp() throws Exception {
		container = new MidgetContainer();
		portlet = container.deploy(new PortletModeIndicatingPortlet());
	}

	@After
	public void tearDown() throws Exception {
		container.destroy();
	}

	@Test
	public void renderRequestCanProduceContentInEditMode() throws Exception {
		portlet.setPortletMode(EDIT);
		portlet.render();
		assertThat(portlet.renderedContentAsString(),
				containsString("in edit mode"));
	}

	@Test
	public void renderRequestCanProduceContentInViewMode() throws Exception {
		portlet.setPortletMode(VIEW);
		portlet.render();
		assertThat(portlet.renderedContentAsString(),
				containsString("in view mode"));
	}

	@Test
	public void renderRequestCanProduceContentInHelpMode() throws Exception {
		portlet.setPortletMode(HELP);
		portlet.render();
		assertThat(portlet.renderedContentAsString(),
				containsString("in help mode"));
	}
}
