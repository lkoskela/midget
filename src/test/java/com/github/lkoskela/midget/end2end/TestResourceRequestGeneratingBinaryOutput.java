package com.github.lkoskela.midget.end2end;

import static com.github.lkoskela.midget.end2end.portlets.BinaryGeneratingPortlet.asHex;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.lkoskela.midget.DeployedPortlet;
import com.github.lkoskela.midget.MidgetContainer;
import com.github.lkoskela.midget.end2end.portlets.BinaryGeneratingPortlet;

public class TestResourceRequestGeneratingBinaryOutput {

	private MidgetContainer container;
	private DeployedPortlet portlet;
	private BinaryGeneratingPortlet portletInstance;

	@Before
	public void setUp() throws Exception {
		container = new MidgetContainer();
		portletInstance = new BinaryGeneratingPortlet();
		portlet = container.deploy(portletInstance);
	}

	@After
	public void tearDown() throws Exception {
		container.destroy();
	}

	@Test
	public void resourceRequestGetsContentAsItWasWritten() throws Exception {
		portlet.resource();
		assertEquals(asHex(portletInstance.getContent()),
				asHex(portlet.renderedContentAsBytes()));
	}
}
