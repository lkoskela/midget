package com.github.lkoskela.midget.end2end;

import static javax.portlet.WindowState.MAXIMIZED;
import static javax.portlet.WindowState.MINIMIZED;
import static javax.portlet.WindowState.NORMAL;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.lkoskela.midget.DeployedPortlet;
import com.github.lkoskela.midget.MidgetContainer;

public class TestWindowState {

	private MidgetContainer container;
	private DeployedPortlet portlet;
	protected WindowState dispatchedWindowState;

	@Before
	public void setUp() throws Exception {
		container = new MidgetContainer();
		portlet = container.deploy(new GenericPortlet() {
			@Override
			protected void doDispatch(RenderRequest request,
					RenderResponse response) throws PortletException,
					IOException {
				dispatchedWindowState = request.getWindowState();
			}
		});
	}

	@After
	public void tearDown() throws Exception {
		container.destroy();
	}

	@Test
	public void windowStateIsNormalByDefault() throws Exception {
		portlet.render();
		assertEquals(NORMAL, dispatchedWindowState);
	}

	@Test
	public void windowStateCanBeSetAndReset() throws Exception {
		portlet.setWindowState(MAXIMIZED);
		portlet.render();
		assertEquals(MAXIMIZED, dispatchedWindowState);
		portlet.setWindowState(MINIMIZED);
		portlet.render();
		assertEquals(MINIMIZED, dispatchedWindowState);
		portlet.setWindowState(NORMAL);
		portlet.render();
		assertEquals(NORMAL, dispatchedWindowState);
	}
}
